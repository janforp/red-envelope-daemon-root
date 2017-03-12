package com.hongbao.api.dao;

import com.hongbao.api.model.ReAndroidMissionFinishDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-12
 */
public interface ReAndroidMissionFinishDetailDAO {
    int deleteByPrimaryKey(Long detailId);

    void insert(ReAndroidMissionFinishDetail record);

    void insertSelective(ReAndroidMissionFinishDetail record);

    void insertBatch(List<ReAndroidMissionFinishDetail> records);

    ReAndroidMissionFinishDetail selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(ReAndroidMissionFinishDetail record);

    int updateByPrimaryKey(ReAndroidMissionFinishDetail record);
}