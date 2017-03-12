package com.hongbao.api.dao;

import com.hongbao.api.model.RePackageChannel;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-25
 */
public interface RePackageChannelDAO {
    int deleteByPrimaryKey(String channelName, String packageName);

    void insert(RePackageChannel record);

    void insertSelective(RePackageChannel record);

    void insertBatch(List<RePackageChannel> records);

    RePackageChannel selectByPrimaryKey(String channelName, String packageName);

    int updateByPrimaryKeySelective(RePackageChannel record);

    int updateByPrimaryKey(RePackageChannel record);
}