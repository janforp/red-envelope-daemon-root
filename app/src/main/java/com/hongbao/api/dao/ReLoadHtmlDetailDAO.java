package com.hongbao.api.dao;

import com.hongbao.api.model.ReLoadHtmlDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-22
 */
public interface ReLoadHtmlDetailDAO {
    int deleteByPrimaryKey(Long detailId);

    void insert(ReLoadHtmlDetail record);

    void insertSelective(ReLoadHtmlDetail record);

    void insertBatch(List<ReLoadHtmlDetail> records);

    ReLoadHtmlDetail selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(ReLoadHtmlDetail record);

    int updateByPrimaryKey(ReLoadHtmlDetail record);
}