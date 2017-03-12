package com.hongbao.api.dao;

import com.hongbao.api.model.ReWithdrawBindZfb;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-02
 */
public interface ReWithdrawBindZfbDAO {
    int deleteByPrimaryKey(Integer userId);

    void insert(ReWithdrawBindZfb record);

    void insertSelective(ReWithdrawBindZfb record);

    void insertBatch(List<ReWithdrawBindZfb> records);

    ReWithdrawBindZfb selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ReWithdrawBindZfb record);

    int updateByPrimaryKey(ReWithdrawBindZfb record);
}