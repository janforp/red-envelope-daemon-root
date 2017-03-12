package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReYmAndriodDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReYmAndriod;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
@Repository
public class ReYmAndriodDAOImpl extends BaseSqlSessionDaoSupport
        implements ReYmAndriodDAO {
    public int deleteByPrimaryKey(String orderId) {
        ReYmAndriod _key = new ReYmAndriod();
        _key.setOrderId(orderId);
        return getSqlSession().delete("re_ym_andriod.deleteByPrimaryKey", _key);
    }

    public void insert(ReYmAndriod record) {
        getSqlSession().insert("re_ym_andriod.insert", record);
    }

    public void insertSelective(ReYmAndriod record) {
        getSqlSession().insert("re_ym_andriod.insertSelective", record);
    }

    public void insertBatch(List<ReYmAndriod> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_ym_andriod.insertBatch", records);
    }

    public ReYmAndriod selectByPrimaryKey(String orderId) {
        ReYmAndriod _key = new ReYmAndriod();
        _key.setOrderId(orderId);
        return getSqlSession().selectOne("re_ym_andriod.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReYmAndriod record) {
        return getSqlSession().update("re_ym_andriod.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReYmAndriod record) {
        return getSqlSession().update("re_ym_andriod.updateByPrimaryKey", record);
    }
}