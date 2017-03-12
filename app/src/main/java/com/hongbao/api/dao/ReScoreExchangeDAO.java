package com.hongbao.api.dao;

import com.hongbao.api.model.ReScoreExchange;
import com.hongbao.api.model.dto.ReScoreExchangeInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
public interface ReScoreExchangeDAO {
    int deleteByPrimaryKey(Integer exchangeId);

    void insert(ReScoreExchange record);

    void insertSelective(ReScoreExchange record);

    void insertBatch(List<ReScoreExchange> records);

    ReScoreExchange selectByPrimaryKey(Integer exchangeId);

    int updateByPrimaryKeySelective(ReScoreExchange record);

    int updateByPrimaryKey(ReScoreExchange record);

    /**
     * 查询金币兑换列表
     * @param platform
     * @return
     */
    List<ReScoreExchangeInfo> selectScoreExchangeByPlatform(int platform);

}