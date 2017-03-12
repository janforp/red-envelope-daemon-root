package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.RePasswordRedDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.RePasswordRed;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
@Repository
public class RePasswordRedDAOImpl extends BaseSqlSessionDaoSupport
        implements RePasswordRedDAO {
    public int deleteByPrimaryKey(Long id) {
        RePasswordRed _key = new RePasswordRed();
        _key.setId(id);
        return getSqlSession().delete("re_password_red.deleteByPrimaryKey", _key);
    }

    public void insert(RePasswordRed record) {
        getSqlSession().insert("re_password_red.insert", record);
    }

    public void insertSelective(RePasswordRed record) {
        getSqlSession().insert("re_password_red.insertSelective", record);
    }

    public void insertBatch(List<RePasswordRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_password_red.insertBatch", records);
    }

    public RePasswordRed selectByPrimaryKey(Long id) {
        RePasswordRed _key = new RePasswordRed();
        _key.setId(id);
        return getSqlSession().selectOne("re_password_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePasswordRed record) {
        return getSqlSession().update("re_password_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePasswordRed record) {
        return getSqlSession().update("re_password_red.updateByPrimaryKey", record);
    }

    /**
     * 由口令查询口令红包
     * @param password
     * @return
     */
    public RePasswordRed selectLockByPassword(String password){
        return getSqlSession().selectOne("re_password_red.selectLockByPassword",password);
    }
}