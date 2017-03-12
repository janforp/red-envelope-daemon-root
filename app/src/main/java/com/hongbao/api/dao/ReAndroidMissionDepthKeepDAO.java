package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidMissionDepthKeep;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-06
 */
public interface ReAndroidMissionDepthKeepDAO {
    int deleteByPrimaryKey(Long missionId, Integer depthId, String dataDay);

    void insert(ReAndroidMissionDepthKeep record);

    void insertSelective(ReAndroidMissionDepthKeep record);

    void insertBatch(List<ReAndroidMissionDepthKeep> records);

    ReAndroidMissionDepthKeep selectByPrimaryKey(Long missionId, Integer depthId, String dataDay);

    int updateByPrimaryKeySelective(ReAndroidMissionDepthKeep record);

    int updateByPrimaryKey(ReAndroidMissionDepthKeep record);

    /**
     * 查询真实数量
     *
     * @param missionId
     * @param depthId
     * @param dataDay
     * @return
     */
    int selectRealNumLock(Long missionId, Integer depthId, String dataDay);

}