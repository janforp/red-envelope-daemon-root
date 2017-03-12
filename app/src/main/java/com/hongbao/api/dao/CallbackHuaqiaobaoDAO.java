package com.hongbao.api.dao;

import com.hongbao.api.model.CallbackHuaqiaobao;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-31
 */
public interface CallbackHuaqiaobaoDAO {
    int deleteByPrimaryKey(Long id);

    void insert(CallbackHuaqiaobao record);

    void insertSelective(CallbackHuaqiaobao record);

    void insertBatch(List<CallbackHuaqiaobao> records);

    CallbackHuaqiaobao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CallbackHuaqiaobao record);

    int updateByPrimaryKey(CallbackHuaqiaobao record);

    /**
     * 去重查询
     * @param callType
     * @param userId
     * @param mobile
     * @return
     */
    CallbackHuaqiaobao selectRepeat(int callType, Integer userId, String mobile);

}