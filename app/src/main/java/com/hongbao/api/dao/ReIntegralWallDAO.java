package com.hongbao.api.dao;

import com.hongbao.api.model.ReIntegralWall;
import com.hongbao.api.model.dto.ReIntegralWallInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-12
 */
public interface ReIntegralWallDAO {
    int deleteByPrimaryKey(Integer wallId);

    void insert(ReIntegralWall record);

    void insertSelective(ReIntegralWall record);

    void insertBatch(List<ReIntegralWall> records);

    ReIntegralWall selectByPrimaryKey(Integer wallId);

    int updateByPrimaryKeySelective(ReIntegralWall record);

    int updateByPrimaryKey(ReIntegralWall record);

    /**
     * 积分墙列表
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    List<ReIntegralWallInfo> selectWallListByPlatform(int platform, String version, String packageName, String channelName);

}