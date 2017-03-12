package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReZyNotifyDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReZyNotify;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-09
 */
@Repository
public class ReZyNotifyDAOImpl extends BaseSqlSessionDaoSupport
        implements ReZyNotifyDAO {
    public int deleteByPrimaryKey(String orderId) {
        ReZyNotify _key = new ReZyNotify();
        _key.setOrderId(orderId);
        return getSqlSession().delete("re_zy_notify.deleteByPrimaryKey", _key);
    }

    public void insert(ReZyNotify record) {
        getSqlSession().insert("re_zy_notify.insert", record);
    }

    public void insertSelective(ReZyNotify record) {
        getSqlSession().insert("re_zy_notify.insertSelective", record);
    }

    public void insertBatch(List<ReZyNotify> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_zy_notify.insertBatch", records);
    }

    public ReZyNotify selectByPrimaryKey(String orderId) {
        ReZyNotify _key = new ReZyNotify();
        _key.setOrderId(orderId);
        return getSqlSession().selectOne("re_zy_notify.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReZyNotify record) {
        return getSqlSession().update("re_zy_notify.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReZyNotify record) {
        return getSqlSession().update("re_zy_notify.updateByPrimaryKey", record);
    }
}