package com.hongbao.api.dao;

import com.hongbao.api.model.ReArticleClickDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
public interface ReArticleClickDetailDAO {
    int deleteByPrimaryKey(Long articleId, String openId);

    void insert(ReArticleClickDetail record);

    void insertSelective(ReArticleClickDetail record);

    void insertBatch(List<ReArticleClickDetail> records);

    ReArticleClickDetail selectByPrimaryKey(Long articleId, String openId);

    int updateByPrimaryKeySelective(ReArticleClickDetail record);

    int updateByPrimaryKey(ReArticleClickDetail record);

    /**
     * 查询文章点击量
     *
     * @param articleId
     * @param userId
     * @return
     */
    int selectCountByArticleIdAndUserId(Long articleId, Integer userId);

    /**
     * 判断userId用户是否完成了一次分享任务
     * @param userId
     * @return
     */
    ReArticleClickDetail selectByUserId(Integer userId);

}