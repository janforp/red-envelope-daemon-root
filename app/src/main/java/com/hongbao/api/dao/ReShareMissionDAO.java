package com.hongbao.api.dao;

import com.hongbao.api.model.ReShareMission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-14
 */
public interface ReShareMissionDAO {
    int deleteByPrimaryKey(Long missionId);

    void insert(ReShareMission record);

    void insertSelective(ReShareMission record);

    void insertBatch(List<ReShareMission> records);

    ReShareMission selectByPrimaryKey(Long missionId);

    int updateByPrimaryKeySelective(ReShareMission record);

    int updateByPrimaryKey(ReShareMission record);

    List<ReShareMission> getListByStartTime();

    /**
     * 查询正在进行中分享任务数量
     *
     * @return
     */
    int selectShareCount();

}