package com.hongbao.api.dao;

import com.hongbao.api.model.ReNewcomerMissionDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public interface ReNewcomerMissionDetailDAO {
    int deleteByPrimaryKey(Long missionId, Integer userId);

    void insert(ReNewcomerMissionDetail record);

    void insertSelective(ReNewcomerMissionDetail record);

    void insertBatch(List<ReNewcomerMissionDetail> records);

    ReNewcomerMissionDetail selectByPrimaryKey(Long missionId, Integer userId);

    int updateByPrimaryKeySelective(ReNewcomerMissionDetail record);

    int updateByPrimaryKey(ReNewcomerMissionDetail record);
}