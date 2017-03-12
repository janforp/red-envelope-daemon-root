package com.hongbao.api.dao;

import com.hongbao.api.model.ReArticleMission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
public interface ReArticleMissionDAO {
    int deleteByPrimaryKey(Long articleId);

    void insert(ReArticleMission record);

    void insertSelective(ReArticleMission record);

    void insertBatch(List<ReArticleMission> records);

    ReArticleMission selectByPrimaryKey(Long articleId);

    int updateByPrimaryKeySelective(ReArticleMission record);

    int updateByPrimaryKey(ReArticleMission record);

    /**
     * 查询进行中的任务数量
     * @param nowTime
     * @return
     */
    int selectArticleCount(String nowTime);


    /**
     * 查询进行中的任务
     * @param nowTime
     * @return
     */
    List<ReArticleMission> selectArticleList(String nowTime, Long articleId);


}