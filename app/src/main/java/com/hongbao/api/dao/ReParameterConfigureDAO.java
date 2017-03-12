package com.hongbao.api.dao;

import com.hongbao.api.model.ReParameterConfigure;
import com.hongbao.api.model.vo.PasswordRedIntroduction;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public interface ReParameterConfigureDAO {
    int deleteByPrimaryKey(String configureId);

    void insert(ReParameterConfigure record);

    void insertSelective(ReParameterConfigure record);

    void insertBatch(List<ReParameterConfigure> records);

    ReParameterConfigure selectByPrimaryKey(String configureId);

    int updateByPrimaryKeySelective(ReParameterConfigure record);

    int updateByPrimaryKey(ReParameterConfigure record);

    ReParameterConfigure getPasswordRedIntroduction();
}