package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReArticleMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReArticleMission;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
@Repository
public class ReArticleMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReArticleMissionDAO {
    public int deleteByPrimaryKey(Long articleId) {
        ReArticleMission _key = new ReArticleMission();
        _key.setArticleId(articleId);
        return getSqlSession().delete("re_article_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleMission record) {
        getSqlSession().insert("re_article_mission.insert", record);
    }

    public void insertSelective(ReArticleMission record) {
        getSqlSession().insert("re_article_mission.insertSelective", record);
    }

    public void insertBatch(List<ReArticleMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_mission.insertBatch", records);
    }

    public ReArticleMission selectByPrimaryKey(Long articleId) {
        ReArticleMission _key = new ReArticleMission();
        _key.setArticleId(articleId);
        return getSqlSession().selectOne("re_article_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleMission record) {
        return getSqlSession().update("re_article_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleMission record) {
        return getSqlSession().update("re_article_mission.updateByPrimaryKey", record);
    }

    /**
     * 查询进行中的任务数量
     * @param nowTime
     * @return
     */
    public int selectArticleCount(String nowTime) {
        return getSqlSession().selectOne("re_article_mission.selectArticleCount", nowTime);
    }


    /**
     * 查询进行中的任务
     *
     * @param nowTime
     * @param articleId
     * @return
     */
    public List<ReArticleMission> selectArticleList(String nowTime, Long articleId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("nowTime", nowTime);
        map.put("articleId", articleId);
        return getSqlSession().selectList("re_article_mission.selectArticleList", map);
    }

}