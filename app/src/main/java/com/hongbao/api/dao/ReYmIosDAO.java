package com.hongbao.api.dao;

import com.hongbao.api.model.ReYmIos;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
public interface ReYmIosDAO {
    int deleteByPrimaryKey(String orderId);

    void insert(ReYmIos record);

    void insertSelective(ReYmIos record);

    void insertBatch(List<ReYmIos> records);

    ReYmIos selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(ReYmIos record);

    int updateByPrimaryKey(ReYmIos record);
}