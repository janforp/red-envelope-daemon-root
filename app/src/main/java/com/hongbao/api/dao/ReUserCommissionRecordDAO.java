package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserCommissionRecord;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-22
 */
public interface ReUserCommissionRecordDAO {
    int deleteByPrimaryKey(String dataDay, Integer userId, Integer invitedUserId);

    void insert(ReUserCommissionRecord record);

    void insertSelective(ReUserCommissionRecord record);

    void insertBatch(List<ReUserCommissionRecord> records);

    ReUserCommissionRecord selectByPrimaryKey(String dataDay, Integer userId, Integer invitedUserId);

    int updateByPrimaryKeySelective(ReUserCommissionRecord record);

    int updateByPrimaryKey(ReUserCommissionRecord record);
}