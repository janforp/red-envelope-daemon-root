package com.hongbao.api.dao;

import com.hongbao.api.model.ReDcNotify;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
public interface ReDcNotifyDAO {
    int deleteByPrimaryKey(String orderId);

    void insert(ReDcNotify record);

    void insertSelective(ReDcNotify record);

    void insertBatch(List<ReDcNotify> records);

    ReDcNotify selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(ReDcNotify record);

    int updateByPrimaryKey(ReDcNotify record);
}