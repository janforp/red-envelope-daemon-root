package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserCommissionRecordDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserCommissionRecord;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-22
 */
@Repository
public class ReUserCommissionRecordDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserCommissionRecordDAO {
    public int deleteByPrimaryKey(String dataDay, Integer userId, Integer invitedUserId) {
        ReUserCommissionRecord _key = new ReUserCommissionRecord();
        _key.setDataDay(dataDay);
        _key.setUserId(userId);
        _key.setInvitedUserId(invitedUserId);
        return getSqlSession().delete("re_user_commission_record.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserCommissionRecord record) {
        getSqlSession().insert("re_user_commission_record.insert", record);
    }

    public void insertSelective(ReUserCommissionRecord record) {
        getSqlSession().insert("re_user_commission_record.insertSelective", record);
    }

    public void insertBatch(List<ReUserCommissionRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_commission_record.insertBatch", records);
    }

    public ReUserCommissionRecord selectByPrimaryKey(String dataDay, Integer userId, Integer invitedUserId) {
        ReUserCommissionRecord _key = new ReUserCommissionRecord();
        _key.setDataDay(dataDay);
        _key.setUserId(userId);
        _key.setInvitedUserId(invitedUserId);
        return getSqlSession().selectOne("re_user_commission_record.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserCommissionRecord record) {
        return getSqlSession().update("re_user_commission_record.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserCommissionRecord record) {
        return getSqlSession().update("re_user_commission_record.updateByPrimaryKey", record);
    }
}