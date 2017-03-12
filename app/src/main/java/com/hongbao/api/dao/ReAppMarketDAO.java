package com.hongbao.api.dao;

import com.hongbao.api.model.ReAppMarket;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
public interface ReAppMarketDAO {
    int deleteByPrimaryKey(Integer marketId);

    void insert(ReAppMarket record);

    void insertSelective(ReAppMarket record);

    void insertBatch(List<ReAppMarket> records);

    ReAppMarket selectByPrimaryKey(Integer marketId);

    int updateByPrimaryKeySelective(ReAppMarket record);

    int updateByPrimaryKey(ReAppMarket record);
}