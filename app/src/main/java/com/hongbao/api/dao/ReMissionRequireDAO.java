package com.hongbao.api.dao;

import com.hongbao.api.model.ReMissionRequire;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-06
 */
public interface ReMissionRequireDAO {
    int deleteByPrimaryKey(Long requireId);

    void insert(ReMissionRequire record);

    void insertSelective(ReMissionRequire record);

    void insertBatch(List<ReMissionRequire> records);

    ReMissionRequire selectByPrimaryKey(Long requireId);

    int updateByPrimaryKeySelective(ReMissionRequire record);

    int updateByPrimaryKey(ReMissionRequire record);
}