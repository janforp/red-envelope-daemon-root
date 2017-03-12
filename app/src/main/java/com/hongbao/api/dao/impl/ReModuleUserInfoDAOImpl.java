package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReModuleUserInfoDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReModuleUserInfo;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-03
 */
@Repository
public class ReModuleUserInfoDAOImpl extends BaseSqlSessionDaoSupport
        implements ReModuleUserInfoDAO {
    public int deleteByPrimaryKey(Long infoId) {
        ReModuleUserInfo _key = new ReModuleUserInfo();
        _key.setInfoId(infoId);
        return getSqlSession().delete("re_module_user_info.deleteByPrimaryKey", _key);
    }

    public void insert(ReModuleUserInfo record) {
        getSqlSession().insert("re_module_user_info.insert", record);
    }

    public void insertSelective(ReModuleUserInfo record) {
        getSqlSession().insert("re_module_user_info.insertSelective", record);
    }

    public void insertBatch(List<ReModuleUserInfo> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_module_user_info.insertBatch", records);
    }

    public ReModuleUserInfo selectByPrimaryKey(Long infoId) {
        ReModuleUserInfo _key = new ReModuleUserInfo();
        _key.setInfoId(infoId);
        return getSqlSession().selectOne("re_module_user_info.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReModuleUserInfo record) {
        return getSqlSession().update("re_module_user_info.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReModuleUserInfo record) {
        return getSqlSession().update("re_module_user_info.updateByPrimaryKey", record);
    }
}