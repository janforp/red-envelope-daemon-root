package com.hongbao.api.dao;

import com.hongbao.api.model.ReDiscover;
import com.hongbao.api.model.dto.ReDiscoverInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-15
 */
public interface ReDiscoverDAO {
    int deleteByPrimaryKey(Integer discoverId);

    void insert(ReDiscover record);

    void insertSelective(ReDiscover record);

    void insertBatch(List<ReDiscover> records);

    ReDiscover selectByPrimaryKey(Integer discoverId);

    int updateByPrimaryKeySelective(ReDiscover record);

    int updateByPrimaryKey(ReDiscover record);

    /**
     * 查询发现列表
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    List<ReDiscoverInfo> selectDiscoverByPlatform(int platform, String version, String packageName, String channelName);

}