package com.hongbao.api.dao;

import com.hongbao.api.model.RePasswordRedDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public interface RePasswordRedDetailDAO {
    int deleteByPrimaryKey(Long passwordId, Integer userId);

    void insert(RePasswordRedDetail record);

    void insertSelective(RePasswordRedDetail record);

    void insertBatch(List<RePasswordRedDetail> records);

    RePasswordRedDetail selectByPrimaryKey(Long passwordId, Integer userId);

    int updateByPrimaryKeySelective(RePasswordRedDetail record);

    int updateByPrimaryKey(RePasswordRedDetail record);
}