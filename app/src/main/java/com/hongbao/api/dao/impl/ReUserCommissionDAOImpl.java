package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserCommissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserCommission;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-20
 */
@Repository
public class ReUserCommissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserCommissionDAO {
    public int deleteByPrimaryKey(Integer userId) {
        ReUserCommission _key = new ReUserCommission();
        _key.setUserId(userId);
        return getSqlSession().delete("re_user_commission.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserCommission record) {
        getSqlSession().insert("re_user_commission.insert", record);
    }

    public void insertSelective(ReUserCommission record) {
        getSqlSession().insert("re_user_commission.insertSelective", record);
    }

    public void insertBatch(List<ReUserCommission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_commission.insertBatch", records);
    }

    public ReUserCommission selectByPrimaryKey(Integer userId) {
        ReUserCommission _key = new ReUserCommission();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_user_commission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserCommission record) {
        return getSqlSession().update("re_user_commission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserCommission record) {
        return getSqlSession().update("re_user_commission.updateByPrimaryKey", record);
    }
}