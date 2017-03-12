package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndriodDeepMission;
import com.hongbao.api.model.ReAndriodIntegralWall;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-05
 */
public interface ReAndriodDeepMissionDAO {
    int deleteByPrimaryKey(Long wallId, Integer userId);

    void insert(ReAndriodDeepMission record);

    void insertSelective(ReAndriodDeepMission record);

    void insertBatch(List<ReAndriodDeepMission> records);

    ReAndriodDeepMission selectByPrimaryKey(Long wallId, Integer userId);

    int updateByPrimaryKeySelective(ReAndriodDeepMission record);

    int updateByPrimaryKey(ReAndriodDeepMission record);

    /**
     * 查询可做的深度任务
     *
     * @param userId
     * @param dayTime
     * @return
     */
    List<ReAndriodIntegralWall> selectUnderwayByUserId(Integer userId, String dayTime);


    /**
     * 查询 深度任务
     *
     * @param userId
     * @param wallId
     * @return
     */
    ReAndriodDeepMission selectLockByWallId(Integer userId, Long wallId, String dayTime);

}