package com.hongbao.api.dao;

import com.hongbao.api.model.ReBdNotify;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
public interface ReBdNotifyDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReBdNotify record);

    void insertSelective(ReBdNotify record);

    void insertBatch(List<ReBdNotify> records);

    ReBdNotify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReBdNotify record);

    int updateByPrimaryKey(ReBdNotify record);

    /**
     * 去重查询
     * @param device_id
     * @param ad_packname
     * @param trade_type
     * @param time_stamp
     * @return
     */
    ReBdNotify selectRepeat(String device_id, String ad_packname, int trade_type, long time_stamp);

}