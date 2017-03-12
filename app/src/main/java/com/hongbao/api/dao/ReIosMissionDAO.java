package com.hongbao.api.dao;

import com.hongbao.api.model.ReIosMission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public interface ReIosMissionDAO {
    int deleteByPrimaryKey(Long missionId);

    void insert(ReIosMission record);

    void insertSelective(ReIosMission record);

    void insertBatch(List<ReIosMission> records);

    ReIosMission selectByPrimaryKey(Long missionId);

    int updateByPrimaryKeySelective(ReIosMission record);

    int updateByPrimaryKey(ReIosMission record);

    /**
     * 查询可做的ios任务
     *
     * @param nowTime
     * @return
     */
    List<ReIosMission> selectAllByTime(String nowTime);

}