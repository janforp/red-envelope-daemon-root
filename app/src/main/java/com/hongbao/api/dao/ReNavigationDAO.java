package com.hongbao.api.dao;

import com.hongbao.api.model.ReNavigation;
import com.hongbao.api.model.dto.ReNavigationInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-15
 */
public interface ReNavigationDAO {
    int deleteByPrimaryKey(Integer navigationId);

    void insert(ReNavigation record);

    void insertSelective(ReNavigation record);

    void insertBatch(List<ReNavigation> records);

    ReNavigation selectByPrimaryKey(Integer navigationId);

    int updateByPrimaryKeySelective(ReNavigation record);

    int updateByPrimaryKey(ReNavigation record);

    /**
     * 查询首页导航
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    List<ReNavigationInfo> selectNavigationByPlatform(int platform, String version, String packageName, String channelName, boolean flag);

}