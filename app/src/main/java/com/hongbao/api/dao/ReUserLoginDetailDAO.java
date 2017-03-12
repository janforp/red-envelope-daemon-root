package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserLoginDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-22
 */
public interface ReUserLoginDetailDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReUserLoginDetail record);

    void insertSelective(ReUserLoginDetail record);

    void insertBatch(List<ReUserLoginDetail> records);

    ReUserLoginDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReUserLoginDetail record);

    int updateByPrimaryKey(ReUserLoginDetail record);
}