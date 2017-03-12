package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidMissionInstall;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-05
 */
public interface ReAndroidMissionInstallDAO {
    int deleteByPrimaryKey(Long missionId, Integer userId);

    void insert(ReAndroidMissionInstall record);

    void insertSelective(ReAndroidMissionInstall record);

    void insertBatch(List<ReAndroidMissionInstall> records);

    ReAndroidMissionInstall selectByPrimaryKey(Long missionId, Integer userId);

    int updateByPrimaryKeySelective(ReAndroidMissionInstall record);

    int updateByPrimaryKey(ReAndroidMissionInstall record);

    /**
     * 查询还未体验完的任务id列表
     *
     * @param userId
     * @return
     */
    List<Long> selectUnderWayId(Integer userId);

}