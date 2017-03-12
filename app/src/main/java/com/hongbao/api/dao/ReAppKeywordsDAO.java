package com.hongbao.api.dao;

import com.hongbao.api.model.ReAppKeywords;
import com.hongbao.api.model.dto.TaskInfo;
import com.hongbao.api.model.dto.TrialInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
public interface ReAppKeywordsDAO {
    int deleteByPrimaryKey(Long keywordId);

    void insert(ReAppKeywords record);

    void insertSelective(ReAppKeywords record);

    void insertBatch(List<ReAppKeywords> records);

    ReAppKeywords selectByPrimaryKey(Long keywordId);

    int updateByPrimaryKeySelective(ReAppKeywords record);

    int updateByPrimaryKey(ReAppKeywords record);

    /**
     * 查询任务
     *
     * @param keywordId
     * @return
     */
    TrialInfo selectTrialByKeywordId(Long keywordId);

    /**
     * 查询任务
     *
     * @param keywordId
     * @return
     */
    TaskInfo selectByKeywordId(Long keywordId);

    /**
     * 查询进行中的任务列表
     *
     * @param releaseTime
     * @param userId
     * @return
     */
    List<TaskInfo> selectAllByLeftAndTime(String releaseTime, Integer userId);

    /**
     * 查询即将开始的任务列表
     *
     * @param releaseTime
     * @param dayTime
     * @param userId
     * @return
     */
    List<TaskInfo> selectNextByTime(String releaseTime, String dayTime, Integer userId);

    /**
     * 查询任务
     *
     * @param keywordId
     * @param nowTime
     * @return
     */
    ReAppKeywords selectLockByKeywordId(Long keywordId, String nowTime);

    /**
     * 查询进行中的任务列表
     *
     * @param startTime
     * @param userId
     * @return
     */
    List<TrialInfo> selectAllByTime(String startTime, Integer userId);

}