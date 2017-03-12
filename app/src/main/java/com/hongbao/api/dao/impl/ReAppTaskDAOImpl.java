package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAppTaskDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAppTask;

import com.hongbao.api.model.dto.TaskInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
@Repository
public class ReAppTaskDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAppTaskDAO {
    public int deleteByPrimaryKey(Long taskId) {
        ReAppTask _key = new ReAppTask();
        _key.setTaskId(taskId);
        return getSqlSession().delete("re_app_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReAppTask record) {
        getSqlSession().insert("re_app_task.insert", record);
    }

    public void insertSelective(ReAppTask record) {
        getSqlSession().insert("re_app_task.insertSelective", record);
    }

    public void insertBatch(List<ReAppTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app_task.insertBatch", records);
    }

    public ReAppTask selectByPrimaryKey(Long taskId) {
        ReAppTask _key = new ReAppTask();
        _key.setTaskId(taskId);
        return getSqlSession().selectOne("re_app_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAppTask record) {
        return getSqlSession().update("re_app_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAppTask record) {
        return getSqlSession().update("re_app_task.updateByPrimaryKey", record);
    }

    /**
     * 查询1条用户完成的任务
     *
     * @param userId
     * @return
     */
    public ReAppTask selectFinishByUserId(Integer userId) {
        return getSqlSession().selectOne("re_app_task.selectFinishByUserId", userId);
    }

    /**
     * 查询用户完成的任务
     *
     * @param userId
     * @param limitSize
     * @return
     */
    public List<TaskInfo> selectFinishByUserIdAndSize(Integer userId, int limitSize) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("limitSize", limitSize);
        return getSqlSession().selectList("re_app_task.selectFinishByUserIdAndSize", map);
    }

    /**
     * 查询正在进行中的任务
     *
     * @param userId
     * @param deviceId
     * @return
     */
    public ReAppTask selectByUserIdAndDeviceId(Integer userId, String deviceId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("deviceId", deviceId);
        return getSqlSession().selectOne("re_app_task.selectByUserIdAndDeviceId", map);
    }

    /**
     * 查询正在进行中的任务
     *
     * @param userId
     * @return
     */
    public ReAppTask selectIngTaskByUserIdAndKeywordIdLock(Integer userId,Long keywordId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("keywordId", keywordId);
        return getSqlSession().selectOne("re_app_task.selectIngTaskByUserIdAndKeywordIdLock", map);
    }

    /**
     * 查询是否做过这个app的下载任务
     *
     * @param userId
     * @param appId
     * @return
     */
    public ReAppTask selectByUserIdAndAppId(Integer userId, Long appId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("appId", appId);
        return getSqlSession().selectOne("re_app_task.selectByUserIdAndAppId", map);
    }

    /**
     * 查询超时任务列表
     *
     * @return
     */
    public List<ReAppTask> selectOvertimeTask(Long createTime) {
        return getSqlSession().selectList("re_app_task.selectOvertimeTask", createTime);
    }


}