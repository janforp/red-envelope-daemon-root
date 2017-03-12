package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndriodUserTask;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-03
 */
public interface ReAndriodUserTaskDAO {
    int deleteByPrimaryKey(Long wallId, Integer userId);

    void insert(ReAndriodUserTask record);

    void insertSelective(ReAndriodUserTask record);

    void insertBatch(List<ReAndriodUserTask> records);

    ReAndriodUserTask selectByPrimaryKey(Long wallId, Integer userId);

    int updateByPrimaryKeySelective(ReAndriodUserTask record);

    int updateByPrimaryKey(ReAndriodUserTask record);

    /**
     * 查询正在进行中的任务
     *
     * @param userId
     * @return
     */
    ReAndriodUserTask selectByUserIdAndStatus(Integer userId);


    /**
     * 查询是否做过这个积分墙任务
     *
     * @param userId
     * @param wallId
     * @return
     */
    ReAndriodUserTask selectByUserIdAndWallIdAndStatus(Integer userId, Long wallId, Integer taskStatus);


    /**
     * 查询正在进行中的积分墙任务
     *
     * @param userId
     * @param wallId
     * @return
     */
    ReAndriodUserTask selectLockByUserIdAndWallId(Integer userId, Long wallId);

    /**
     * 查询用户是否完成过积分墙任务
     *
     * @param userId
     * @return
     */
    ReAndriodUserTask selectFinishByUserId(Integer userId);

}