package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserPortraitDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserPortrait;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-19
 */
@Repository
public class ReUserPortraitDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserPortraitDAO {
    public int deleteByPrimaryKey(Integer id) {
        ReUserPortrait _key = new ReUserPortrait();
        _key.setId(id);
        return getSqlSession().delete("re_user_portrait.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserPortrait record) {
        getSqlSession().insert("re_user_portrait.insert", record);
    }

    public void insertSelective(ReUserPortrait record) {
        getSqlSession().insert("re_user_portrait.insertSelective", record);
    }

    public void insertBatch(List<ReUserPortrait> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_portrait.insertBatch", records);
    }

    public ReUserPortrait selectByPrimaryKey(Integer id) {
        ReUserPortrait _key = new ReUserPortrait();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_portrait.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserPortrait record) {
        return getSqlSession().update("re_user_portrait.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserPortrait record) {
        return getSqlSession().update("re_user_portrait.updateByPrimaryKey", record);
    }


    /**
     * 随机查询一个头像
     * @return
     */
    public String selectRandom() {
        return getSqlSession().selectOne("re_user_portrait.selectRandom");
    }

}