package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReScoreExchangeDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReScoreExchange;

import com.hongbao.api.model.dto.ReScoreExchangeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
@Repository
public class ReScoreExchangeDAOImpl extends BaseSqlSessionDaoSupport
        implements ReScoreExchangeDAO {
    public int deleteByPrimaryKey(Integer exchangeId) {
        ReScoreExchange _key = new ReScoreExchange();
        _key.setExchangeId(exchangeId);
        return getSqlSession().delete("re_score_exchange.deleteByPrimaryKey", _key);
    }

    public void insert(ReScoreExchange record) {
        getSqlSession().insert("re_score_exchange.insert", record);
    }

    public void insertSelective(ReScoreExchange record) {
        getSqlSession().insert("re_score_exchange.insertSelective", record);
    }

    public void insertBatch(List<ReScoreExchange> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_score_exchange.insertBatch", records);
    }

    public ReScoreExchange selectByPrimaryKey(Integer exchangeId) {
        ReScoreExchange _key = new ReScoreExchange();
        _key.setExchangeId(exchangeId);
        return getSqlSession().selectOne("re_score_exchange.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReScoreExchange record) {
        return getSqlSession().update("re_score_exchange.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReScoreExchange record) {
        return getSqlSession().update("re_score_exchange.updateByPrimaryKey", record);
    }


    /**
     * 查询金币兑换列表
     * @param platform
     * @return
     */
    public List<ReScoreExchangeInfo> selectScoreExchangeByPlatform(int platform) {

        if(platform == 0) { // ios
            return getSqlSession().selectList("re_score_exchange.selectIosScoreExchange");
        }else { // andriod
            return getSqlSession().selectList("re_score_exchange.selectAndriodScoreExchange");
        }

    }

}