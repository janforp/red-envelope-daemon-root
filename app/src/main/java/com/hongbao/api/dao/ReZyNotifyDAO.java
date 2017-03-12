package com.hongbao.api.dao;

import com.hongbao.api.model.ReZyNotify;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-09
 */
public interface ReZyNotifyDAO {
    int deleteByPrimaryKey(String orderId);

    void insert(ReZyNotify record);

    void insertSelective(ReZyNotify record);

    void insertBatch(List<ReZyNotify> records);

    ReZyNotify selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(ReZyNotify record);

    int updateByPrimaryKey(ReZyNotify record);
}