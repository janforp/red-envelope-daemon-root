package com.hongbao.api.dao;

import com.hongbao.api.model.ReDianjoyNotify;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-27
 */
public interface ReDianjoyNotifyDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReDianjoyNotify record);

    void insertSelective(ReDianjoyNotify record);

    void insertBatch(List<ReDianjoyNotify> records);

    ReDianjoyNotify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReDianjoyNotify record);

    int updateByPrimaryKey(ReDianjoyNotify record);

    /**
     * 去重查询
     * @param device_id
     * @param pack_name
     * @param trade_type
     * @param task_id
     * @return
     */
    ReDianjoyNotify selectRepeat(String device_id, String pack_name, String trade_type, String task_id);

}