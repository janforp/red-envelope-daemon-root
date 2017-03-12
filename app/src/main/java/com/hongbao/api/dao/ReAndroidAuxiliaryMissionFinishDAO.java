package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidAuxiliaryMissionFinish;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
public interface ReAndroidAuxiliaryMissionFinishDAO {
    int deleteByPrimaryKey(Long missionId, Integer missionNo, Integer userId);

    void insert(ReAndroidAuxiliaryMissionFinish record);

    void insertSelective(ReAndroidAuxiliaryMissionFinish record);

    void insertBatch(List<ReAndroidAuxiliaryMissionFinish> records);

    ReAndroidAuxiliaryMissionFinish selectByPrimaryKey(Long missionId, Integer missionNo, Integer userId);

    int updateByPrimaryKeySelective(ReAndroidAuxiliaryMissionFinish record);

    int updateByPrimaryKey(ReAndroidAuxiliaryMissionFinish record);

    /**
     * 查询最新的一条完成情况
     *
     * @param missionId
     * @param userId
     * @return
     */
    ReAndroidAuxiliaryMissionFinish selectByMissionIdAndUserId(Long missionId, Integer userId);

}