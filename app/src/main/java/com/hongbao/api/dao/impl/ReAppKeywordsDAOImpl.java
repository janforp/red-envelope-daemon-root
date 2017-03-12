package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAppKeywordsDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAppKeywords;

import com.hongbao.api.model.dto.TaskInfo;
import com.hongbao.api.model.dto.TrialInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
@Repository
public class ReAppKeywordsDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAppKeywordsDAO {
    public int deleteByPrimaryKey(Long keywordId) {
        ReAppKeywords _key = new ReAppKeywords();
        _key.setKeywordId(keywordId);
        return getSqlSession().delete("re_app_keywords.deleteByPrimaryKey", _key);
    }

    public void insert(ReAppKeywords record) {
        getSqlSession().insert("re_app_keywords.insert", record);
    }

    public void insertSelective(ReAppKeywords record) {
        getSqlSession().insert("re_app_keywords.insertSelective", record);
    }

    public void insertBatch(List<ReAppKeywords> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app_keywords.insertBatch", records);
    }

    public ReAppKeywords selectByPrimaryKey(Long keywordId) {
        ReAppKeywords _key = new ReAppKeywords();
        _key.setKeywordId(keywordId);
        return getSqlSession().selectOne("re_app_keywords.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAppKeywords record) {
        return getSqlSession().update("re_app_keywords.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAppKeywords record) {
        return getSqlSession().update("re_app_keywords.updateByPrimaryKey", record);
    }

    /**
     * 查询任务
     *
     * @param keywordId
     * @return
     */
    public TrialInfo selectTrialByKeywordId(Long keywordId) {
        return getSqlSession().selectOne("re_app_keywords.selectTrialByKeywordId", keywordId);
    }

    /**
     * 查询任务
     *
     * @param keywordId
     * @return
     */
    public TaskInfo selectByKeywordId(Long keywordId) {
        return getSqlSession().selectOne("re_app_keywords.selectByKeywordId", keywordId);
    }

    /**
     * 查询进行中的任务列表
     *
     * @param releaseTime
     * @param userId
     * @return
     */
    public List<TaskInfo> selectAllByLeftAndTime(String releaseTime, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("releaseTime", releaseTime);
        map.put("userId", userId);
        return getSqlSession().selectList("re_app_keywords.selectAllByLeftAndTime", map);
    }

    /**
     * 查询即将开始的任务列表
     *
     * @param releaseTime
     * @param dayTime
     * @param userId
     * @return
     */
    public List<TaskInfo> selectNextByTime(String releaseTime, String dayTime, Integer userId) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("releaseTime", releaseTime);
        map.put("dayTime", dayTime);
        map.put("userId", userId);
        return getSqlSession().selectList("re_app_keywords.selectNextByTime", map);
    }

    /**
     * 查询任务
     *
     * @param keywordId
     * @param nowTime
     * @return
     */
    public ReAppKeywords selectLockByKeywordId(Long keywordId, String nowTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("keywordId", keywordId);
        map.put("nowTime", nowTime);
        return getSqlSession().selectOne("re_app_keywords.selectLockByKeywordId", map);
    }

    /**
     * 查询进行中的任务列表
     *
     * @param startTime
     * @param userId
     * @return
     */
    public List<TrialInfo> selectAllByTime(String startTime, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("startTime", startTime);
        map.put("userId", userId);
        return getSqlSession().selectList("re_app_keywords.selectAllByTime", map);
    }

}