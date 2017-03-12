package com.hongbao.api.dao;

import com.hongbao.api.model.ReAuxiliaryStatistic;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-20
 */
public interface ReAuxiliaryStatisticDAO {
    int deleteByPrimaryKey(Long missionId, Integer stepId, Integer platform, String statisticTime);

    void insert(ReAuxiliaryStatistic record);

    void insertSelective(ReAuxiliaryStatistic record);

    void insertBatch(List<ReAuxiliaryStatistic> records);

    ReAuxiliaryStatistic selectByPrimaryKey(Long missionId, Integer stepId, Integer platform, String statisticTime);

    int updateByPrimaryKeySelective(ReAuxiliaryStatistic record);

    int updateByPrimaryKey(ReAuxiliaryStatistic record);
}