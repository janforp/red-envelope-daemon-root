package com.hongbao.api.dao;

import com.hongbao.api.model.ReUmengPush;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-18
 */
public interface ReUmengPushDAO {
    int deleteByPrimaryKey(Integer userId);

    void insert(ReUmengPush record);

    void insertSelective(ReUmengPush record);

    void insertBatch(List<ReUmengPush> records);

    ReUmengPush selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ReUmengPush record);

    int updateByPrimaryKey(ReUmengPush record);
}