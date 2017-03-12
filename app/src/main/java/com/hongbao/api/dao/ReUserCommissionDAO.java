package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserCommission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-20
 */
public interface ReUserCommissionDAO {
    int deleteByPrimaryKey(Integer userId);

    void insert(ReUserCommission record);

    void insertSelective(ReUserCommission record);

    void insertBatch(List<ReUserCommission> records);

    ReUserCommission selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ReUserCommission record);

    int updateByPrimaryKey(ReUserCommission record);
}