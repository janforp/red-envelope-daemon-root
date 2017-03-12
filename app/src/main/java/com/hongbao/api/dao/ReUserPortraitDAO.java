package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserPortrait;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-19
 */
public interface ReUserPortraitDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(ReUserPortrait record);

    void insertSelective(ReUserPortrait record);

    void insertBatch(List<ReUserPortrait> records);

    ReUserPortrait selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReUserPortrait record);

    int updateByPrimaryKey(ReUserPortrait record);

    /**
     * 随机查询一个头像
     * @return
     */
    String selectRandom();
}