package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.RePasswordRedDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.RePasswordRedDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
@Repository
public class RePasswordRedDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements RePasswordRedDetailDAO {
    public int deleteByPrimaryKey(Long passwordId, Integer userId) {
        RePasswordRedDetail _key = new RePasswordRedDetail();
        _key.setPasswordId(passwordId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_password_red_detail.deleteByPrimaryKey", _key);
    }

    public void insert(RePasswordRedDetail record) {
        getSqlSession().insert("re_password_red_detail.insert", record);
    }

    public void insertSelective(RePasswordRedDetail record) {
        getSqlSession().insert("re_password_red_detail.insertSelective", record);
    }

    public void insertBatch(List<RePasswordRedDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_password_red_detail.insertBatch", records);
    }

    public RePasswordRedDetail selectByPrimaryKey(Long passwordId, Integer userId) {
        RePasswordRedDetail _key = new RePasswordRedDetail();
        _key.setPasswordId(passwordId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_password_red_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePasswordRedDetail record) {
        return getSqlSession().update("re_password_red_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePasswordRedDetail record) {
        return getSqlSession().update("re_password_red_detail.updateByPrimaryKey", record);
    }
}