package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAppMarketDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAppMarket;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
@Repository
public class ReAppMarketDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAppMarketDAO {
    public int deleteByPrimaryKey(Integer marketId) {
        ReAppMarket _key = new ReAppMarket();
        _key.setMarketId(marketId);
        return getSqlSession().delete("re_app_market.deleteByPrimaryKey", _key);
    }

    public void insert(ReAppMarket record) {
        getSqlSession().insert("re_app_market.insert", record);
    }

    public void insertSelective(ReAppMarket record) {
        getSqlSession().insert("re_app_market.insertSelective", record);
    }

    public void insertBatch(List<ReAppMarket> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app_market.insertBatch", records);
    }

    public ReAppMarket selectByPrimaryKey(Integer marketId) {
        ReAppMarket _key = new ReAppMarket();
        _key.setMarketId(marketId);
        return getSqlSession().selectOne("re_app_market.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAppMarket record) {
        return getSqlSession().update("re_app_market.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAppMarket record) {
        return getSqlSession().update("re_app_market.updateByPrimaryKey", record);
    }
}