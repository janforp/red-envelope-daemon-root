package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidAuxiliaryMission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
public interface ReAndroidAuxiliaryMissionDAO {
    int deleteByPrimaryKey(Long missionId, Integer missionNo);

    void insert(ReAndroidAuxiliaryMission record);

    void insertSelective(ReAndroidAuxiliaryMission record);

    void insertBatch(List<ReAndroidAuxiliaryMission> records);

    ReAndroidAuxiliaryMission selectByPrimaryKey(Long missionId, Integer missionNo);

    int updateByPrimaryKeySelective(ReAndroidAuxiliaryMission record);

    int updateByPrimaryKey(ReAndroidAuxiliaryMission record);

    /**
     * 查询所有附属任务
     *
     * @param missionId
     * @return
     */
    List<ReAndroidAuxiliaryMission> selectAllByMissionId(Long missionId);

    /**
     * 查询任务详情
     *
     * @param missionId
     * @param missionNo
     * @return
     */
    ReAndroidAuxiliaryMission selectLock(Long missionId, Integer missionNo);

}