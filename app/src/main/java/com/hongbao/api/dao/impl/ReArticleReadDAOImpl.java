package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReArticleReadDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReArticleRead;

import com.hongbao.api.model.dto.ReArticleInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-07
 */
@Repository
public class ReArticleReadDAOImpl extends BaseSqlSessionDaoSupport
        implements ReArticleReadDAO {
    public int deleteByPrimaryKey(Long articleId, Integer userId) {
        ReArticleRead _key = new ReArticleRead();
        _key.setArticleId(articleId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_article_read.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleRead record) {
        getSqlSession().insert("re_article_read.insert", record);
    }

    public void insertSelective(ReArticleRead record) {
        getSqlSession().insert("re_article_read.insertSelective", record);
    }

    public void insertBatch(List<ReArticleRead> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_read.insertBatch", records);
    }

    public ReArticleRead selectByPrimaryKey(Long articleId, Integer userId) {
        ReArticleRead _key = new ReArticleRead();
        _key.setArticleId(articleId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_article_read.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleRead record) {
        return getSqlSession().update("re_article_read.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleRead record) {
        return getSqlSession().update("re_article_read.updateByPrimaryKey", record);
    }

    /**
     * 分享记录
     *
     * @param articleId
     * @param userId
     * @return
     */
    public List<ReArticleInfo> selectByArticleIdAndUserId(Long articleId, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("articleId", articleId);
        map.put("userId", userId);
        return getSqlSession().selectList("re_article_read.selectByArticleIdAndUserId", map);
    }

}