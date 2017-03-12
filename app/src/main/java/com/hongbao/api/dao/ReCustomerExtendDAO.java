package com.hongbao.api.dao;

import com.hongbao.api.model.ReCustomerExtend;
import com.hongbao.api.model.dto.ReExtendInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-28
 */
public interface ReCustomerExtendDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(ReCustomerExtend record);

    void insertSelective(ReCustomerExtend record);

    void insertBatch(List<ReCustomerExtend> records);

    ReCustomerExtend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReCustomerExtend record);

    int updateByPrimaryKey(ReCustomerExtend record);

    /**
     * 获取现金红包
     * @param platform
     * @return
     */
    List<ReExtendInfo> selectExtendByPlatform(int platform);

    /**
     * 获取首页现金红包
     * @param platform
     * @param pageSize
     * @return
     */
    List<ReExtendInfo> selectIndexExtendByPlatform(int platform, int pageSize);

}