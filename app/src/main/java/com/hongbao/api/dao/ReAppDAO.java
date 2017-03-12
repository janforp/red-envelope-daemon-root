package com.hongbao.api.dao;

import com.hongbao.api.model.ReApp;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
public interface ReAppDAO {
    int deleteByPrimaryKey(Long appId);

    void insert(ReApp record);

    void insertSelective(ReApp record);

    void insertBatch(List<ReApp> records);

    ReApp selectByPrimaryKey(Long appId);

    int updateByPrimaryKeySelective(ReApp record);

    int updateByPrimaryKey(ReApp record);
}