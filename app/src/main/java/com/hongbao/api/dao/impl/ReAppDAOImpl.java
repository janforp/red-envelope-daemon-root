package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAppDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReApp;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
@Repository
public class ReAppDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAppDAO {
    public int deleteByPrimaryKey(Long appId) {
        ReApp _key = new ReApp();
        _key.setAppId(appId);
        return getSqlSession().delete("re_app.deleteByPrimaryKey", _key);
    }

    public void insert(ReApp record) {
        getSqlSession().insert("re_app.insert", record);
    }

    public void insertSelective(ReApp record) {
        getSqlSession().insert("re_app.insertSelective", record);
    }

    public void insertBatch(List<ReApp> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app.insertBatch", records);
    }

    public ReApp selectByPrimaryKey(Long appId) {
        ReApp _key = new ReApp();
        _key.setAppId(appId);
        return getSqlSession().selectOne("re_app.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReApp record) {
        return getSqlSession().update("re_app.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReApp record) {
        return getSqlSession().update("re_app.updateByPrimaryKey", record);
    }
}