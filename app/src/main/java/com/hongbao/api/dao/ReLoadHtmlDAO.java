package com.hongbao.api.dao;

import com.hongbao.api.model.ReLoadHtml;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-22
 */
public interface ReLoadHtmlDAO {
    int deleteByPrimaryKey(Long htmlId);

    void insert(ReLoadHtml record);

    void insertSelective(ReLoadHtml record);

    void insertBatch(List<ReLoadHtml> records);

    ReLoadHtml selectByPrimaryKey(Long htmlId);

    int updateByPrimaryKeySelective(ReLoadHtml record);

    int updateByPrimaryKey(ReLoadHtml record);

    /**
     * 查询网页链接列表
     *
     * @param deviceId
     * @return
     */
    List<ReLoadHtml> selectHtmlUtl(String deviceId);

    /**
     * 查询网页点击
     *
     * @param htmlId
     * @return
     */
    ReLoadHtml selectLockByHtmlId(Long htmlId);

}