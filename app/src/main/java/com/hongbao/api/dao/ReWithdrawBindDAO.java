package com.hongbao.api.dao;

import com.hongbao.api.model.ReWithdrawBind;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-23
 */
public interface ReWithdrawBindDAO {
    int deleteByPrimaryKey(Integer userId);

    void insert(ReWithdrawBind record);

    void insertSelective(ReWithdrawBind record);

    void insertBatch(List<ReWithdrawBind> records);

    ReWithdrawBind selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ReWithdrawBind record);

    int updateByPrimaryKey(ReWithdrawBind record);
}