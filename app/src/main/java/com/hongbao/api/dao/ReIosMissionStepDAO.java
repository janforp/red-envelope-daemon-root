package com.hongbao.api.dao;

import com.hongbao.api.model.ReIosMissionStep;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public interface ReIosMissionStepDAO {
    int deleteByPrimaryKey(Long missionId, Integer stepId);

    void insert(ReIosMissionStep record);

    void insertSelective(ReIosMissionStep record);

    void insertBatch(List<ReIosMissionStep> records);

    ReIosMissionStep selectByPrimaryKey(Long missionId, Integer stepId);

    int updateByPrimaryKeySelective(ReIosMissionStep record);

    int updateByPrimaryKey(ReIosMissionStep record);

    /**
     * 查询所有步骤
     * @param missionId
     * @return
     */
    List<ReIosMissionStep> selectAllByMissionId(Long missionId);

    /**
     * 查询步骤详情
     * @param missionId
     * @param stepId
     * @return
     */
    ReIosMissionStep selectLock(Long missionId, Integer stepId);

}