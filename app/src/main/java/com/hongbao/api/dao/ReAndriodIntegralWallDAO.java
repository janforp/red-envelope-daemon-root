package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndriodIntegralWall;
import com.hongbao.api.model.dto.TrialInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-02
 */
public interface ReAndriodIntegralWallDAO {
    int deleteByPrimaryKey(Long wallId);

    void insert(ReAndriodIntegralWall record);

    void insertSelective(ReAndriodIntegralWall record);

    void insertBatch(List<ReAndriodIntegralWall> records);

    ReAndriodIntegralWall selectByPrimaryKey(Long wallId);

    int updateByPrimaryKeySelective(ReAndriodIntegralWall record);

    int updateByPrimaryKey(ReAndriodIntegralWall record);

    /**
     * 查询任务
     *
     * @param wallId
     * @return
     */
    TrialInfo selectByWallId(Long wallId);

    /**
     * 查询进行中的任务列表
     *
     * @param startTime
     * @param userId
     * @param sim
     * @return
     */
    List<TrialInfo> selectAllByLeftAndTime(String startTime, Integer userId, int sim);

    /**
     * 查询任务明细
     *
     * @param wallId
     * @param nowTime
     * @return
     */
    ReAndriodIntegralWall selectLockByWallId(Long wallId, String nowTime);

}