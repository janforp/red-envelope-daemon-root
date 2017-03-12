package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReArticleClickDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReArticleClickDetail;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
@Repository
public class ReArticleClickDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReArticleClickDetailDAO {
    public int deleteByPrimaryKey(Long articleId, String openId) {
        ReArticleClickDetail _key = new ReArticleClickDetail();
        _key.setArticleId(articleId);
        _key.setOpenId(openId);
        return getSqlSession().delete("re_article_click_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleClickDetail record) {
        getSqlSession().insert("re_article_click_detail.insert", record);
    }

    public void insertSelective(ReArticleClickDetail record) {
        getSqlSession().insert("re_article_click_detail.insertSelective", record);
    }

    public void insertBatch(List<ReArticleClickDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_click_detail.insertBatch", records);
    }

    public ReArticleClickDetail selectByPrimaryKey(Long articleId, String openId) {
        ReArticleClickDetail _key = new ReArticleClickDetail();
        _key.setArticleId(articleId);
        _key.setOpenId(openId);
        return getSqlSession().selectOne("re_article_click_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleClickDetail record) {
        return getSqlSession().update("re_article_click_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleClickDetail record) {
        return getSqlSession().update("re_article_click_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询文章点击量
     *
     * @param articleId
     * @param userId
     * @return
     */
    public int selectCountByArticleIdAndUserId(Long articleId, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("articleId", articleId);
        map.put("userId", userId);
        return getSqlSession().selectOne("re_article_click_detail.selectCountByArticleIdAndUserId", map);
    }

    /**
     * 判断userId用户是否完成了一次分享任务
     * @param userId
     * @return
     */
    @Override
    public ReArticleClickDetail selectByUserId(Integer userId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);
        return getSqlSession().selectOne("re_article_click_detail.selectByUserId", map);
    }

}