package com.hongbao.api.dao;

import com.hongbao.api.model.ReMission;
import com.hongbao.api.model.dto.ReMissionInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-19
 */
public interface ReMissionDAO {
    int deleteByPrimaryKey(Integer missionId);

    void insert(ReMission record);

    void insertSelective(ReMission record);

    void insertBatch(List<ReMission> records);

    ReMission selectByPrimaryKey(Integer missionId);

    int updateByPrimaryKeySelective(ReMission record);

    int updateByPrimaryKey(ReMission record);

    /**
     * 任务列表
     * @param platform
     * @return
     */
    List<ReMissionInfo> selectByPlatform(int platform);


    /**
     * 首页任务列表
     * @param platform
     * @return
     */
    List<ReMissionInfo> selectIndexByPlatform(int platform);

}