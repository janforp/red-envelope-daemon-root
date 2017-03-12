package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReIosMissionFinishDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReIosMissionFinish;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
@Repository
public class ReIosMissionFinishDAOImpl extends BaseSqlSessionDaoSupport
        implements ReIosMissionFinishDAO {
    public int deleteByPrimaryKey(Long missionId, Integer stepId, Integer userId) {
        ReIosMissionFinish _key = new ReIosMissionFinish();
        _key.setMissionId(missionId);
        _key.setStepId(stepId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_ios_mission_finish.deleteByPrimaryKey", _key);
    }

    public void insert(ReIosMissionFinish record) {
        getSqlSession().insert("re_ios_mission_finish.insert", record);
    }

    public void insertSelective(ReIosMissionFinish record) {
        getSqlSession().insert("re_ios_mission_finish.insertSelective", record);
    }

    public void insertBatch(List<ReIosMissionFinish> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_ios_mission_finish.insertBatch", records);
    }

    public ReIosMissionFinish selectByPrimaryKey(Long missionId, Integer stepId, Integer userId) {
        ReIosMissionFinish _key = new ReIosMissionFinish();
        _key.setMissionId(missionId);
        _key.setStepId(stepId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_ios_mission_finish.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIosMissionFinish record) {
        return getSqlSession().update("re_ios_mission_finish.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIosMissionFinish record) {
        return getSqlSession().update("re_ios_mission_finish.updateByPrimaryKey", record);
    }

    /**
     * 用户已做的任务id列表
     *
     * @param userId
     * @return
     */
    public List<Long> selectFinishMissionList(Integer userId) {
        return getSqlSession().selectList("re_ios_mission_finish.selectFinishMissionList", userId);
    }

    /**
     * 查询用户最新完成情况
     * @param missionId
     * @param userId
     * @return
     */
    public ReIosMissionFinish selectByMissionIdAndUserId(Long missionId, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("missionId", missionId);
        map.put("userId", userId);
        return getSqlSession().selectOne("re_ios_mission_finish.selectByMissionIdAndUserId", map);
    }

}