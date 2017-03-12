package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReDianruNotifyDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReDianruNotify;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-12
 */
@Repository
public class ReDianruNotifyDAOImpl extends BaseSqlSessionDaoSupport
        implements ReDianruNotifyDAO {
    public int deleteByPrimaryKey(String hashId) {
        ReDianruNotify _key = new ReDianruNotify();
        _key.setHashId(hashId);
        return getSqlSession().delete("re_dianru_notify.deleteByPrimaryKey", _key);
    }

    public void insert(ReDianruNotify record) {
        getSqlSession().insert("re_dianru_notify.insert", record);
    }

    public void insertSelective(ReDianruNotify record) {
        getSqlSession().insert("re_dianru_notify.insertSelective", record);
    }

    public void insertBatch(List<ReDianruNotify> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_dianru_notify.insertBatch", records);
    }

    public ReDianruNotify selectByPrimaryKey(String hashId) {
        ReDianruNotify _key = new ReDianruNotify();
        _key.setHashId(hashId);
        return getSqlSession().selectOne("re_dianru_notify.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDianruNotify record) {
        return getSqlSession().update("re_dianru_notify.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDianruNotify record) {
        return getSqlSession().update("re_dianru_notify.updateByPrimaryKey", record);
    }
}