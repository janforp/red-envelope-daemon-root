package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReArticleAdDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReArticleAd;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
@Repository
public class ReArticleAdDAOImpl extends BaseSqlSessionDaoSupport
        implements ReArticleAdDAO {
    public int deleteByPrimaryKey(Long adId) {
        ReArticleAd _key = new ReArticleAd();
        _key.setAdId(adId);
        return getSqlSession().delete("re_article_ad.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleAd record) {
        getSqlSession().insert("re_article_ad.insert", record);
    }

    public void insertSelective(ReArticleAd record) {
        getSqlSession().insert("re_article_ad.insertSelective", record);
    }

    public void insertBatch(List<ReArticleAd> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_ad.insertBatch", records);
    }

    public ReArticleAd selectByPrimaryKey(Long adId) {
        ReArticleAd _key = new ReArticleAd();
        _key.setAdId(adId);
        return getSqlSession().selectOne("re_article_ad.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleAd record) {
        return getSqlSession().update("re_article_ad.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleAd record) {
        return getSqlSession().update("re_article_ad.updateByPrimaryKey", record);
    }
}