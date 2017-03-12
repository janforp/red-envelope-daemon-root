package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.CallbackHuaqiaobaoDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.CallbackHuaqiaobao;

import com.hongbao.api.model.ReUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-31
 */
@Repository
public class CallbackHuaqiaobaoDAOImpl extends BaseSqlSessionDaoSupport
        implements CallbackHuaqiaobaoDAO {
    public int deleteByPrimaryKey(Long id) {
        CallbackHuaqiaobao _key = new CallbackHuaqiaobao();
        _key.setId(id);
        return getSqlSession().delete("callback_huaqiaobao.deleteByPrimaryKey", _key);
    }

    public void insert(CallbackHuaqiaobao record) {
        getSqlSession().insert("callback_huaqiaobao.insert", record);
    }

    public void insertSelective(CallbackHuaqiaobao record) {
        getSqlSession().insert("callback_huaqiaobao.insertSelective", record);
    }

    public void insertBatch(List<CallbackHuaqiaobao> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("callback_huaqiaobao.insertBatch", records);
    }

    public CallbackHuaqiaobao selectByPrimaryKey(Long id) {
        CallbackHuaqiaobao _key = new CallbackHuaqiaobao();
        _key.setId(id);
        return getSqlSession().selectOne("callback_huaqiaobao.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(CallbackHuaqiaobao record) {
        return getSqlSession().update("callback_huaqiaobao.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(CallbackHuaqiaobao record) {
        return getSqlSession().update("callback_huaqiaobao.updateByPrimaryKey", record);
    }

    /**
     * 去重查询
     * @param callType
     * @param userId
     * @param mobile
     * @return
     */
    public CallbackHuaqiaobao selectRepeat(int callType, Integer userId, String mobile) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("callType", callType);
        map.put("userId", userId);
        map.put("mobile", mobile);
        return getSqlSession().selectOne("callback_huaqiaobao.selectRepeat", map);
    }

}