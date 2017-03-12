package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReYmIosDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReYmIos;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
@Repository
public class ReYmIosDAOImpl extends BaseSqlSessionDaoSupport
        implements ReYmIosDAO {
    public int deleteByPrimaryKey(String orderId) {
        ReYmIos _key = new ReYmIos();
        _key.setOrderId(orderId);
        return getSqlSession().delete("re_ym_ios.deleteByPrimaryKey", _key);
    }

    public void insert(ReYmIos record) {
        getSqlSession().insert("re_ym_ios.insert", record);
    }

    public void insertSelective(ReYmIos record) {
        getSqlSession().insert("re_ym_ios.insertSelective", record);
    }

    public void insertBatch(List<ReYmIos> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_ym_ios.insertBatch", records);
    }

    public ReYmIos selectByPrimaryKey(String orderId) {
        ReYmIos _key = new ReYmIos();
        _key.setOrderId(orderId);
        return getSqlSession().selectOne("re_ym_ios.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReYmIos record) {
        return getSqlSession().update("re_ym_ios.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReYmIos record) {
        return getSqlSession().update("re_ym_ios.updateByPrimaryKey", record);
    }
}