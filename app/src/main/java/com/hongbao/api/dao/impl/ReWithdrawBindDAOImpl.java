package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReWithdrawBindDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReWithdrawBind;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-23
 */
@Repository
public class ReWithdrawBindDAOImpl extends BaseSqlSessionDaoSupport
        implements ReWithdrawBindDAO {
    public int deleteByPrimaryKey(Integer userId) {
        ReWithdrawBind _key = new ReWithdrawBind();
        _key.setUserId(userId);
        return getSqlSession().delete("re_withdraw_bind.deleteByPrimaryKey", _key);
    }

    public void insert(ReWithdrawBind record) {
        getSqlSession().insert("re_withdraw_bind.insert", record);
    }

    public void insertSelective(ReWithdrawBind record) {
        getSqlSession().insert("re_withdraw_bind.insertSelective", record);
    }

    public void insertBatch(List<ReWithdrawBind> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_withdraw_bind.insertBatch", records);
    }

    public ReWithdrawBind selectByPrimaryKey(Integer userId) {
        ReWithdrawBind _key = new ReWithdrawBind();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_withdraw_bind.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWithdrawBind record) {
        return getSqlSession().update("re_withdraw_bind.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWithdrawBind record) {
        return getSqlSession().update("re_withdraw_bind.updateByPrimaryKey", record);
    }
}