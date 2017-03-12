package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReBdNotifyDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReBdNotify;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
@Repository
public class ReBdNotifyDAOImpl extends BaseSqlSessionDaoSupport
        implements ReBdNotifyDAO {
    public int deleteByPrimaryKey(Long id) {
        ReBdNotify _key = new ReBdNotify();
        _key.setId(id);
        return getSqlSession().delete("re_bd_notify.deleteByPrimaryKey", _key);
    }

    public void insert(ReBdNotify record) {
        getSqlSession().insert("re_bd_notify.insert", record);
    }

    public void insertSelective(ReBdNotify record) {
        getSqlSession().insert("re_bd_notify.insertSelective", record);
    }

    public void insertBatch(List<ReBdNotify> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_bd_notify.insertBatch", records);
    }

    public ReBdNotify selectByPrimaryKey(Long id) {
        ReBdNotify _key = new ReBdNotify();
        _key.setId(id);
        return getSqlSession().selectOne("re_bd_notify.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReBdNotify record) {
        return getSqlSession().update("re_bd_notify.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReBdNotify record) {
        return getSqlSession().update("re_bd_notify.updateByPrimaryKey", record);
    }

    /**
     * 去重查询
     * @param device_id
     * @param ad_packname
     * @param trade_type
     * @param time_stamp
     * @return
     */
    public ReBdNotify selectRepeat(String device_id, String ad_packname, int trade_type, long time_stamp) {

        Map<String, Object> map = new HashMap<>();
        map.put("device_id", device_id);
        map.put("ad_packname", ad_packname);
        map.put("trade_type", trade_type);
        map.put("time_stamp", time_stamp);
        return getSqlSession().selectOne("re_bd_notify.selectRepeat", map);

    }

}