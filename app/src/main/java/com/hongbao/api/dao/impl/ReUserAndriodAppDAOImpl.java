package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserAndriodAppDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserAndriodApp;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-14
 */
@Repository
public class ReUserAndriodAppDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserAndriodAppDAO {
    public int deleteByPrimaryKey(Long id) {
        ReUserAndriodApp _key = new ReUserAndriodApp();
        _key.setId(id);
        return getSqlSession().delete("re_user_andriod_app.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserAndriodApp record) {
        getSqlSession().insert("re_user_andriod_app.insert", record);
    }

    public void insertSelective(ReUserAndriodApp record) {
        getSqlSession().insert("re_user_andriod_app.insertSelective", record);
    }

    public void insertBatch(List<ReUserAndriodApp> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_andriod_app.insertBatch", records);
    }

    public ReUserAndriodApp selectByPrimaryKey(Long id) {
        ReUserAndriodApp _key = new ReUserAndriodApp();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_andriod_app.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserAndriodApp record) {
        return getSqlSession().update("re_user_andriod_app.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserAndriodApp record) {
        return getSqlSession().update("re_user_andriod_app.updateByPrimaryKey", record);
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    public int deleteByUserId(int userId) {
        return getSqlSession().delete("re_user_andriod_app.deleteByUserId", userId);
    }

}