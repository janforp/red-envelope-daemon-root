package com.hongbao.api.dao;

import com.hongbao.api.model.RePasswordRed;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public interface RePasswordRedDAO {
    int deleteByPrimaryKey(Long id);

    void insert(RePasswordRed record);

    void insertSelective(RePasswordRed record);

    void insertBatch(List<RePasswordRed> records);

    RePasswordRed selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RePasswordRed record);

    int updateByPrimaryKey(RePasswordRed record);

    /**
     * 查询
     *
     * @param password
     * @return
     */
    RePasswordRed selectLockByPassword(String password);
}