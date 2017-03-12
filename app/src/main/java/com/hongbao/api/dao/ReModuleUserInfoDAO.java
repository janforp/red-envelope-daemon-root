package com.hongbao.api.dao;

import com.hongbao.api.model.ReModuleUserInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-03
 */
public interface ReModuleUserInfoDAO {
    int deleteByPrimaryKey(Long infoId);

    void insert(ReModuleUserInfo record);

    void insertSelective(ReModuleUserInfo record);

    void insertBatch(List<ReModuleUserInfo> records);

    ReModuleUserInfo selectByPrimaryKey(Long infoId);

    int updateByPrimaryKeySelective(ReModuleUserInfo record);

    int updateByPrimaryKey(ReModuleUserInfo record);
}