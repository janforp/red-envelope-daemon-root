package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserAndriodAppHistoryDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserAndriodAppHistory;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-14
 */
@Repository
public class ReUserAndriodAppHistoryDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserAndriodAppHistoryDAO {
    public int deleteByPrimaryKey(Long id) {
        ReUserAndriodAppHistory _key = new ReUserAndriodAppHistory();
        _key.setId(id);
        return getSqlSession().delete("re_user_andriod_app_history.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserAndriodAppHistory record) {
        getSqlSession().insert("re_user_andriod_app_history.insert", record);
    }

    public void insertSelective(ReUserAndriodAppHistory record) {
        getSqlSession().insert("re_user_andriod_app_history.insertSelective", record);
    }

    public void insertBatch(List<ReUserAndriodAppHistory> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_andriod_app_history.insertBatch", records);
    }

    public ReUserAndriodAppHistory selectByPrimaryKey(Long id) {
        ReUserAndriodAppHistory _key = new ReUserAndriodAppHistory();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_andriod_app_history.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserAndriodAppHistory record) {
        return getSqlSession().update("re_user_andriod_app_history.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserAndriodAppHistory record) {
        return getSqlSession().update("re_user_andriod_app_history.updateByPrimaryKey", record);
    }

    /**
     * 查询历史记录
     *
     * @param userId
     * @param appPackage
     * @return
     */
    public ReUserAndriodAppHistory selectByAppPackage(int userId, String appPackage) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("appPackage", appPackage);
        return getSqlSession().selectOne("re_user_andriod_app_history.selectByAppPackage", map);
    }


}