package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReWithdrawBindZfbDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReWithdrawBindZfb;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-02
 */
@Repository
public class ReWithdrawBindZfbDAOImpl extends BaseSqlSessionDaoSupport
        implements ReWithdrawBindZfbDAO {
    public int deleteByPrimaryKey(Integer userId) {
        ReWithdrawBindZfb _key = new ReWithdrawBindZfb();
        _key.setUserId(userId);
        return getSqlSession().delete("re_withdraw_bind_zfb.deleteByPrimaryKey", _key);
    }

    public void insert(ReWithdrawBindZfb record) {
        getSqlSession().insert("re_withdraw_bind_zfb.insert", record);
    }

    public void insertSelective(ReWithdrawBindZfb record) {
        getSqlSession().insert("re_withdraw_bind_zfb.insertSelective", record);
    }

    public void insertBatch(List<ReWithdrawBindZfb> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_withdraw_bind_zfb.insertBatch", records);
    }

    public ReWithdrawBindZfb selectByPrimaryKey(Integer userId) {
        ReWithdrawBindZfb _key = new ReWithdrawBindZfb();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_withdraw_bind_zfb.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWithdrawBindZfb record) {
        return getSqlSession().update("re_withdraw_bind_zfb.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWithdrawBindZfb record) {
        return getSqlSession().update("re_withdraw_bind_zfb.updateByPrimaryKey", record);
    }
}