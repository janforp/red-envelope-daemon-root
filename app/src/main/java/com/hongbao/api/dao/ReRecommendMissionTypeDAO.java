package com.hongbao.api.dao;

import com.hongbao.api.model.ReRecommendMissionType;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-15
 */
public interface ReRecommendMissionTypeDAO {
    int deleteByPrimaryKey(Integer typeId);

    void insert(ReRecommendMissionType record);

    void insertSelective(ReRecommendMissionType record);

    void insertBatch(List<ReRecommendMissionType> records);

    ReRecommendMissionType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(ReRecommendMissionType record);

    int updateByPrimaryKey(ReRecommendMissionType record);
}