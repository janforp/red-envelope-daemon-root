package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidMissionFinish;
import com.hongbao.api.model.dto.AndroidFinishInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
public interface ReAndroidMissionFinishDAO {
    int deleteByPrimaryKey(Long missionId, Integer userId);

    void insert(ReAndroidMissionFinish record);

    void insertSelective(ReAndroidMissionFinish record);

    void insertBatch(List<ReAndroidMissionFinish> records);

    ReAndroidMissionFinish selectByPrimaryKey(Long missionId, Integer userId);

    int updateByPrimaryKeySelective(ReAndroidMissionFinish record);

    int updateByPrimaryKey(ReAndroidMissionFinish record);

    /**
     * 用户做过的积分墙app包名列表
     *
     * @param userId
     * @return
     */
    List<String> selectFinishAppPackageList(Integer userId);

    /**
     * 查询专属
     *
     * @param userId
     * @param dayTime
     * @return
     */
    List<Long> selectExclusiveId(Integer userId, String dayTime);

    /**
     * 查询完成列表 (分页)
     * @param userId
     * @param updateTime
     * @return
     */
    List<AndroidFinishInfo> selectByUserIdAndTime(Integer userId, String updateTime);

}