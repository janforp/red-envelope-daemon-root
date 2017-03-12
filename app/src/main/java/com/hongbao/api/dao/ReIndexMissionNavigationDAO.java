package com.hongbao.api.dao;

import com.hongbao.api.model.ReIndexMissionNavigation;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-10
 */
public interface ReIndexMissionNavigationDAO {
    int deleteByPrimaryKey(Long navigationId);

    void insert(ReIndexMissionNavigation record);

    void insertSelective(ReIndexMissionNavigation record);

    void insertBatch(List<ReIndexMissionNavigation> records);

    ReIndexMissionNavigation selectByPrimaryKey(Long navigationId);

    int updateByPrimaryKeySelective(ReIndexMissionNavigation record);

    int updateByPrimaryKey(ReIndexMissionNavigation record);

    /**
     * 查询导航列表
     *
     * @param platform
     * @return
     */
    List<ReIndexMissionNavigation> selectByPlatform(int platform);

}