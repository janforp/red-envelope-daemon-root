package com.hongbao.api.dao;

import com.hongbao.api.model.ReIosMissionFinish;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public interface ReIosMissionFinishDAO {
    int deleteByPrimaryKey(Long missionId, Integer stepId, Integer userId);

    void insert(ReIosMissionFinish record);

    void insertSelective(ReIosMissionFinish record);

    void insertBatch(List<ReIosMissionFinish> records);

    ReIosMissionFinish selectByPrimaryKey(Long missionId, Integer stepId, Integer userId);

    int updateByPrimaryKeySelective(ReIosMissionFinish record);

    int updateByPrimaryKey(ReIosMissionFinish record);


    /**
     * 用户已做的任务id列表
     *
     * @param userId
     * @return
     */
    List<Long> selectFinishMissionList(Integer userId);

    /**
     * 查询用户最新完成情况
     * @param missionId
     * @param userId
     * @return
     */
    ReIosMissionFinish selectByMissionIdAndUserId(Long missionId, Integer userId);

}