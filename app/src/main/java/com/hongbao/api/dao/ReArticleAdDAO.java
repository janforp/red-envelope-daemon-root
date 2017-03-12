package com.hongbao.api.dao;

import com.hongbao.api.model.ReArticleAd;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
public interface ReArticleAdDAO {
    int deleteByPrimaryKey(Long adId);

    void insert(ReArticleAd record);

    void insertSelective(ReArticleAd record);

    void insertBatch(List<ReArticleAd> records);

    ReArticleAd selectByPrimaryKey(Long adId);

    int updateByPrimaryKeySelective(ReArticleAd record);

    int updateByPrimaryKey(ReArticleAd record);
}