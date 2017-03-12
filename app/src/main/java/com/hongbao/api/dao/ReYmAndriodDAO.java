package com.hongbao.api.dao;

import com.hongbao.api.model.ReYmAndriod;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
public interface ReYmAndriodDAO {
    int deleteByPrimaryKey(String orderId);

    void insert(ReYmAndriod record);

    void insertSelective(ReYmAndriod record);

    void insertBatch(List<ReYmAndriod> records);

    ReYmAndriod selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(ReYmAndriod record);

    int updateByPrimaryKey(ReYmAndriod record);
}