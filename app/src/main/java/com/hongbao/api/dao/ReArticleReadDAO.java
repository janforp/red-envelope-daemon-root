package com.hongbao.api.dao;

import com.hongbao.api.model.ReArticleRead;
import com.hongbao.api.model.dto.ReArticleInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-07
 */
public interface ReArticleReadDAO {
    int deleteByPrimaryKey(Long articleId, Integer userId);

    void insert(ReArticleRead record);

    void insertSelective(ReArticleRead record);

    void insertBatch(List<ReArticleRead> records);

    ReArticleRead selectByPrimaryKey(Long articleId, Integer userId);

    int updateByPrimaryKeySelective(ReArticleRead record);

    int updateByPrimaryKey(ReArticleRead record);

    /**
     * 分享记录
     *
     * @param articleId
     * @param userId
     * @return
     */
    List<ReArticleInfo> selectByArticleIdAndUserId(Long articleId, Integer userId);

}