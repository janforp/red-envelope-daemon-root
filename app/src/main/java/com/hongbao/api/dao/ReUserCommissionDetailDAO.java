package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserCommissionDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-20
 */
public interface ReUserCommissionDetailDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReUserCommissionDetail record);

    void insertSelective(ReUserCommissionDetail record);

    void insertBatch(List<ReUserCommissionDetail> records);

    ReUserCommissionDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReUserCommissionDetail record);

    int updateByPrimaryKey(ReUserCommissionDetail record);
}