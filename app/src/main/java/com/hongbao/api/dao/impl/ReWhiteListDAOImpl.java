package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReWhiteListDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReWhiteList;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-13
 */
@Repository
public class ReWhiteListDAOImpl extends BaseSqlSessionDaoSupport
        implements ReWhiteListDAO {
    public int deleteByPrimaryKey(String mobile) {
        ReWhiteList _key = new ReWhiteList();
        _key.setMobile(mobile);
        return getSqlSession().delete("re_white_list.deleteByPrimaryKey", _key);
    }

    public void insert(ReWhiteList record) {
        getSqlSession().insert("re_white_list.insert", record);
    }

    public void insertSelective(ReWhiteList record) {
        getSqlSession().insert("re_white_list.insertSelective", record);
    }

    public void insertBatch(List<ReWhiteList> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_white_list.insertBatch", records);
    }

    public ReWhiteList selectByPrimaryKey(String mobile) {
        ReWhiteList _key = new ReWhiteList();
        _key.setMobile(mobile);
        return getSqlSession().selectOne("re_white_list.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWhiteList record) {
        return getSqlSession().update("re_white_list.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWhiteList record) {
        return getSqlSession().update("re_white_list.updateByPrimaryKey", record);
    }
}