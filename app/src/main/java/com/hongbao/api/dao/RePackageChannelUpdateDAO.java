package com.hongbao.api.dao;

import com.hongbao.api.model.RePackageChannelUpdate;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-11
 */
public interface RePackageChannelUpdateDAO {
    int deleteByPrimaryKey(String deviceId, String channelName, String packageName);

    void insert(RePackageChannelUpdate record);

    void insertSelective(RePackageChannelUpdate record);

    void insertBatch(List<RePackageChannelUpdate> records);

    RePackageChannelUpdate selectByPrimaryKey(String deviceId, String channelName, String packageName);

    int updateByPrimaryKeySelective(RePackageChannelUpdate record);

    int updateByPrimaryKey(RePackageChannelUpdate record);
}