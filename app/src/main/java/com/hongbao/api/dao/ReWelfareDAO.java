package com.hongbao.api.dao;

import com.hongbao.api.model.ReWelfare;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-12
 */
public interface ReWelfareDAO {
    int deleteByPrimaryKey(Long welfareId);

    void insert(ReWelfare record);

    void insertSelective(ReWelfare record);

    void insertBatch(List<ReWelfare> records);

    ReWelfare selectByPrimaryKey(Long welfareId);

    int updateByPrimaryKeySelective(ReWelfare record);

    int updateByPrimaryKey(ReWelfare record);

    /**
     * 精选列表
     * @param platform
     * @return
     */
    List<ReWelfare> selectSelectionListByPlatform(int platform);

    /**
     * 福利
     *
     * @param platform
     * @param welfareId
     * @param welfareType
     * @return
     */
    List<ReWelfare> selectListByPlatform(int platform, Long welfareId, Integer welfareType);

    /**
     * 从mysql中获取福利id列表
     * @param platform      平台
     * @param welfareType   类型
     * @return
     */
    List<Long> selectWelfareIdListOrderByUpdateTimeDesc(int platform,Integer welfareType);
}