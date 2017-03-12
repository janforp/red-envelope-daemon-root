package com.hongbao.api.dao;

import com.hongbao.api.model.ReNewcomerMission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public interface ReNewcomerMissionDAO {
    int deleteByPrimaryKey(Long missionId);

    void insert(ReNewcomerMission record);

    void insertSelective(ReNewcomerMission record);

    void insertBatch(List<ReNewcomerMission> records);

    ReNewcomerMission selectByPrimaryKey(Long missionId);

    int updateByPrimaryKeySelective(ReNewcomerMission record);

    int updateByPrimaryKey(ReNewcomerMission record);

    /**
     * 查询新手任务
     *
     * @param platform
     * @return
     */
    List<ReNewcomerMission> selectByPlatform(int platform);

}