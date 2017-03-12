package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidMissionDepth;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-05
 */
public interface ReAndroidMissionDepthDAO {
    int deleteByPrimaryKey(Long missionId, Integer depthId);

    void insert(ReAndroidMissionDepth record);

    void insertSelective(ReAndroidMissionDepth record);

    void insertBatch(List<ReAndroidMissionDepth> records);

    ReAndroidMissionDepth selectByPrimaryKey(Long missionId, Integer depthId);

    int updateByPrimaryKeySelective(ReAndroidMissionDepth record);

    int updateByPrimaryKey(ReAndroidMissionDepth record);

    /**
     * 查询所有深度体验任务
     *
     * @param missionId
     * @return
     */
    List<ReAndroidMissionDepth> selectAllByMissionId(Long missionId);


}