package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUmengPushDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUmengPush;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-18
 */
@Repository
public class ReUmengPushDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUmengPushDAO {
    public int deleteByPrimaryKey(Integer userId) {
        ReUmengPush _key = new ReUmengPush();
        _key.setUserId(userId);
        return getSqlSession().delete("re_umeng_push.deleteByPrimaryKey", _key);
    }

    public void insert(ReUmengPush record) {
        getSqlSession().insert("re_umeng_push.insert", record);
    }

    public void insertSelective(ReUmengPush record) {
        getSqlSession().insert("re_umeng_push.insertSelective", record);
    }

    public void insertBatch(List<ReUmengPush> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_umeng_push.insertBatch", records);
    }

    public ReUmengPush selectByPrimaryKey(Integer userId) {
        ReUmengPush _key = new ReUmengPush();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_umeng_push.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUmengPush record) {
        return getSqlSession().update("re_umeng_push.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUmengPush record) {
        return getSqlSession().update("re_umeng_push.updateByPrimaryKey", record);
    }
}