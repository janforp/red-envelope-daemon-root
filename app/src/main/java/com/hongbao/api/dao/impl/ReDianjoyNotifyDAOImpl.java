package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReDianjoyNotifyDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReDianjoyNotify;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-27
 */
@Repository
public class ReDianjoyNotifyDAOImpl extends BaseSqlSessionDaoSupport
        implements ReDianjoyNotifyDAO {
    public int deleteByPrimaryKey(Long id) {
        ReDianjoyNotify _key = new ReDianjoyNotify();
        _key.setId(id);
        return getSqlSession().delete("re_dianjoy_notify.deleteByPrimaryKey", _key);
    }

    public void insert(ReDianjoyNotify record) {
        getSqlSession().insert("re_dianjoy_notify.insert", record);
    }

    public void insertSelective(ReDianjoyNotify record) {
        getSqlSession().insert("re_dianjoy_notify.insertSelective", record);
    }

    public void insertBatch(List<ReDianjoyNotify> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_dianjoy_notify.insertBatch", records);
    }

    public ReDianjoyNotify selectByPrimaryKey(Long id) {
        ReDianjoyNotify _key = new ReDianjoyNotify();
        _key.setId(id);
        return getSqlSession().selectOne("re_dianjoy_notify.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDianjoyNotify record) {
        return getSqlSession().update("re_dianjoy_notify.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDianjoyNotify record) {
        return getSqlSession().update("re_dianjoy_notify.updateByPrimaryKey", record);
    }


    /**
     * 去重查询
     * @param device_id
     * @param pack_name
     * @param trade_type
     * @param task_id
     * @return
     */
    public ReDianjoyNotify selectRepeat(String device_id, String pack_name, String trade_type, String task_id) {

        Map<String, Object> map = new HashMap<>();
        map.put("device_id", device_id);
        map.put("pack_name", pack_name);
        map.put("trade_type", trade_type);
        map.put("task_id", task_id);
        return getSqlSession().selectOne("re_dianjoy_notify.selectRepeat", map);

    }


}