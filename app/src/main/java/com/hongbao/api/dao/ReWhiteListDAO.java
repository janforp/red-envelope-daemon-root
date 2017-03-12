package com.hongbao.api.dao;

import com.hongbao.api.model.ReWhiteList;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-13
 */
public interface ReWhiteListDAO {
    int deleteByPrimaryKey(String mobile);

    void insert(ReWhiteList record);

    void insertSelective(ReWhiteList record);

    void insertBatch(List<ReWhiteList> records);

    ReWhiteList selectByPrimaryKey(String mobile);

    int updateByPrimaryKeySelective(ReWhiteList record);

    int updateByPrimaryKey(ReWhiteList record);
}