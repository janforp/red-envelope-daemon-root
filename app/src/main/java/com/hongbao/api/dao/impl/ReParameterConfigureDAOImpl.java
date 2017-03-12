package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReParameterConfigureDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReParameterConfigure;

import com.hongbao.api.model.vo.PasswordRedIntroduction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
@Repository
public class ReParameterConfigureDAOImpl extends BaseSqlSessionDaoSupport
        implements ReParameterConfigureDAO {
    public int deleteByPrimaryKey(String configureId) {
        ReParameterConfigure _key = new ReParameterConfigure();
        _key.setConfigureId(configureId);
        return getSqlSession().delete("re_parameter_configure.deleteByPrimaryKey", _key);
    }

    public void insert(ReParameterConfigure record) {
        getSqlSession().insert("re_parameter_configure.insert", record);
    }

    public void insertSelective(ReParameterConfigure record) {
        getSqlSession().insert("re_parameter_configure.insertSelective", record);
    }

    public void insertBatch(List<ReParameterConfigure> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_parameter_configure.insertBatch", records);
    }

    public ReParameterConfigure selectByPrimaryKey(String configureId) {
        ReParameterConfigure _key = new ReParameterConfigure();
        _key.setConfigureId(configureId);
        return getSqlSession().selectOne("re_parameter_configure.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReParameterConfigure record) {
        return getSqlSession().update("re_parameter_configure.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReParameterConfigure record) {
        return getSqlSession().update("re_parameter_configure.updateByPrimaryKey", record);
    }

    /**
     * 获取口令红包介绍
     * @return
     */
    public ReParameterConfigure getPasswordRedIntroduction(){
        return getSqlSession().selectOne("re_parameter_configure.getPasswordRedIntroduction");
    }
}