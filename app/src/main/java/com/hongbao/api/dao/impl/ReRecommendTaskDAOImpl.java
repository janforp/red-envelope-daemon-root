package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReRecommendTaskDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReRecommendTask;

import com.hongbao.api.model.dto.GreatMissionInfo;
import com.hongbao.api.model.dto.ReTaskInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-28
 */
@Repository
public class ReRecommendTaskDAOImpl extends BaseSqlSessionDaoSupport
        implements ReRecommendTaskDAO {
    public int deleteByPrimaryKey(Long taskId) {
        ReRecommendTask _key = new ReRecommendTask();
        _key.setTaskId(taskId);
        return getSqlSession().delete("re_recommend_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendTask record) {
        getSqlSession().insert("re_recommend_task.insert", record);
    }

    public void insertSelective(ReRecommendTask record) {
        getSqlSession().insert("re_recommend_task.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_task.insertBatch", records);
    }

    public ReRecommendTask selectByPrimaryKey(Long taskId) {
        ReRecommendTask _key = new ReRecommendTask();
        _key.setTaskId(taskId);
        return getSqlSession().selectOne("re_recommend_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendTask record) {
        return getSqlSession().update("re_recommend_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendTask record) {
        return getSqlSession().update("re_recommend_task.updateByPrimaryKey", record);
    }

    /**
     * 我的任务
     * 5.0版本
     *
     * @param missionId
     * @param userId
     * @param platform
     * @param missionType
     * @return
     */
    public List<GreatMissionInfo> selectMyMissionList(Long missionId, Integer userId, int platform, Integer missionType) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("missionId", missionId);
        map.put("userId", userId);
        map.put("platform", platform);
        map.put("missionType", missionType);
        return getSqlSession().selectList("re_recommend_task.selectMyMissionList", map);
    }


    /**
     * 获取1条用户已完成的任务
     *
     * @param userId
     * @return
     */
    public ReRecommendTask selectOneByUserId(Integer userId) {
        return getSqlSession().selectOne("re_recommend_task.selectOneByUserId", userId);
    }


    /**
     * 获取用户已完成的任务
     *
     * @param userId
     * @return
     */
    public List<ReTaskInfo> selectByUserId(Integer userId) {
        return getSqlSession().selectList("re_recommend_task.selectByUserId", userId);
    }

    /**
     * 获取用户任务列表
     *
     * @param userId
     * @param taskStatus
     * @param taskId
     * @return
     */
    public List<ReTaskInfo> selectUserTask(Integer userId, Integer taskStatus, Long taskId) {

        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("taskStatus", taskStatus);
        map.put("taskId", taskId);

        return getSqlSession().selectList("re_recommend_task.selectUserTask", map);

    }

    /**
     * 查询任务
     *
     * @param taskId
     * @return
     */
    public ReRecommendTask selectByIdAndStatus(Long taskId) {

        return getSqlSession().selectOne("re_recommend_task.selectByIdAndStatus", taskId);

    }

}