package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReDtnNotifyDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReDtnNotify;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
@Repository
public class ReDtnNotifyDAOImpl extends BaseSqlSessionDaoSupport
        implements ReDtnNotifyDAO {
    public int deleteByPrimaryKey(String orderId) {
        ReDtnNotify _key = new ReDtnNotify();
        _key.setOrderId(orderId);
        return getSqlSession().delete("re_dtn_notify.deleteByPrimaryKey", _key);
    }

    public void insert(ReDtnNotify record) {
        getSqlSession().insert("re_dtn_notify.insert", record);
    }

    public void insertSelective(ReDtnNotify record) {
        getSqlSession().insert("re_dtn_notify.insertSelective", record);
    }

    public void insertBatch(List<ReDtnNotify> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_dtn_notify.insertBatch", records);
    }

    public ReDtnNotify selectByPrimaryKey(String orderId) {
        ReDtnNotify _key = new ReDtnNotify();
        _key.setOrderId(orderId);
        return getSqlSession().selectOne("re_dtn_notify.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDtnNotify record) {
        return getSqlSession().update("re_dtn_notify.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDtnNotify record) {
        return getSqlSession().update("re_dtn_notify.updateByPrimaryKey", record);
    }
}