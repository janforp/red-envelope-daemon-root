package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidMission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
public interface ReAndroidMissionDAO {
    int deleteByPrimaryKey(Long missionId);

    void insert(ReAndroidMission record);

    void insertSelective(ReAndroidMission record);

    void insertBatch(List<ReAndroidMission> records);

    ReAndroidMission selectByPrimaryKey(Long missionId);

    int updateByPrimaryKeySelective(ReAndroidMission record);

    int updateByPrimaryKey(ReAndroidMission record);

    /**
     * 查询当日可做的积分墙
     *
     * @param nowDay
     * @return
     */
    List<ReAndroidMission> selectAllByDay(String nowDay);

    /**
     * 查询剩余数量
     *
     * @param missionId
     * @return
     */
    int selectLeftNumLock(Long missionId);

    /**
     * 修改剩余数量
     *
     * @param leftNum
     * @param missionId
     * @return
     */
    int updateLeftNum(int leftNum, Long missionId);

}