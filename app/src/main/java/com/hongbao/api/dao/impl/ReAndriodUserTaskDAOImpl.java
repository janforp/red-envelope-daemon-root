package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndriodUserTaskDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndriodUserTask;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-03
 */
@Repository
public class ReAndriodUserTaskDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndriodUserTaskDAO {
    public int deleteByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodUserTask _key = new ReAndriodUserTask();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_andriod_user_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndriodUserTask record) {
        getSqlSession().insert("re_andriod_user_task.insert", record);
    }

    public void insertSelective(ReAndriodUserTask record) {
        getSqlSession().insert("re_andriod_user_task.insertSelective", record);
    }

    public void insertBatch(List<ReAndriodUserTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_andriod_user_task.insertBatch", records);
    }

    public ReAndriodUserTask selectByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodUserTask _key = new ReAndriodUserTask();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_andriod_user_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndriodUserTask record) {
        return getSqlSession().update("re_andriod_user_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndriodUserTask record) {
        return getSqlSession().update("re_andriod_user_task.updateByPrimaryKey", record);
    }

    /**
     * 查询正在进行中的任务
     *
     * @param userId
     * @return
     */
    public ReAndriodUserTask selectByUserIdAndStatus(Integer userId) {
        return getSqlSession().selectOne("re_andriod_user_task.selectByUserIdAndStatus", userId);
    }

    /**
     * 查询是否做过这个积分墙任务
     *
     * @param userId
     * @param wallId
     * @return
     */
    public ReAndriodUserTask selectByUserIdAndWallIdAndStatus(Integer userId, Long wallId, Integer taskStatus) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("wallId", wallId);
        map.put("taskStatus", taskStatus);
        return getSqlSession().selectOne("re_andriod_user_task.selectByUserIdAndWallIdAndStatus", map);
    }


    /**
     * 查询正在进行中的积分墙任务
     *
     * @param userId
     * @param wallId
     * @return
     */
    public ReAndriodUserTask selectLockByUserIdAndWallId(Integer userId, Long wallId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("wallId", wallId);
        return getSqlSession().selectOne("re_andriod_user_task.selectLockByUserIdAndWallId", map);
    }


    /**
     * 查询用户是否完成过积分墙任务
     *
     * @param userId
     * @return
     */
    public ReAndriodUserTask selectFinishByUserId(Integer userId) {
        return getSqlSession().selectOne("re_andriod_user_task.selectFinishByUserId", userId);
    }

}