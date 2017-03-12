package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReDcNotifyDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReDcNotify;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
@Repository
public class ReDcNotifyDAOImpl extends BaseSqlSessionDaoSupport
        implements ReDcNotifyDAO {
    public int deleteByPrimaryKey(String orderId) {
        ReDcNotify _key = new ReDcNotify();
        _key.setOrderId(orderId);
        return getSqlSession().delete("re_dc_notify.deleteByPrimaryKey", _key);
    }

    public void insert(ReDcNotify record) {
        getSqlSession().insert("re_dc_notify.insert", record);
    }

    public void insertSelective(ReDcNotify record) {
        getSqlSession().insert("re_dc_notify.insertSelective", record);
    }

    public void insertBatch(List<ReDcNotify> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_dc_notify.insertBatch", records);
    }

    public ReDcNotify selectByPrimaryKey(String orderId) {
        ReDcNotify _key = new ReDcNotify();
        _key.setOrderId(orderId);
        return getSqlSession().selectOne("re_dc_notify.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDcNotify record) {
        return getSqlSession().update("re_dc_notify.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDcNotify record) {
        return getSqlSession().update("re_dc_notify.updateByPrimaryKey", record);
    }
}