package com.hongbao.api.dao;

import com.hongbao.api.model.ReIndexSort;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-18
 */
public interface ReIndexSortDAO {
    int deleteByPrimaryKey(Integer sortId);

    void insert(ReIndexSort record);

    void insertSelective(ReIndexSort record);

    void insertBatch(List<ReIndexSort> records);

    ReIndexSort selectByPrimaryKey(Integer sortId);

    int updateByPrimaryKeySelective(ReIndexSort record);

    int updateByPrimaryKey(ReIndexSort record);

    /**
     * 查询首页分类
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    List<ReIndexSort> selectByPlatform(int platform, String version, String packageName, String channelName);
}