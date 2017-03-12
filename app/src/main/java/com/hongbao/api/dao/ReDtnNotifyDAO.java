package com.hongbao.api.dao;

import com.hongbao.api.model.ReDtnNotify;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
public interface ReDtnNotifyDAO {
    int deleteByPrimaryKey(String orderId);

    void insert(ReDtnNotify record);

    void insertSelective(ReDtnNotify record);

    void insertBatch(List<ReDtnNotify> records);

    ReDtnNotify selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(ReDtnNotify record);

    int updateByPrimaryKey(ReDtnNotify record);
}