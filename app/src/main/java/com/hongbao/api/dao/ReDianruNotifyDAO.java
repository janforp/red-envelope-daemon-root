package com.hongbao.api.dao;

import com.hongbao.api.model.ReDianruNotify;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-12
 */
public interface ReDianruNotifyDAO {
    int deleteByPrimaryKey(String hashId);

    void insert(ReDianruNotify record);

    void insertSelective(ReDianruNotify record);

    void insertBatch(List<ReDianruNotify> records);

    ReDianruNotify selectByPrimaryKey(String hashId);

    int updateByPrimaryKeySelective(ReDianruNotify record);

    int updateByPrimaryKey(ReDianruNotify record);
}