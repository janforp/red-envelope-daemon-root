package com.hongbao.api.dao;

import com.hongbao.api.model.ReIosMissionUserSituation;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-16
 */
public interface ReIosMissionUserSituationDAO {
    int deleteByPrimaryKey(Long missionId, Integer userId);

    void insert(ReIosMissionUserSituation record);

    void insertSelective(ReIosMissionUserSituation record);

    void insertBatch(List<ReIosMissionUserSituation> records);

    ReIosMissionUserSituation selectByPrimaryKey(Long missionId, Integer userId);

    int updateByPrimaryKeySelective(ReIosMissionUserSituation record);

    int updateByPrimaryKey(ReIosMissionUserSituation record);

    /**
     * 查询已做的任务记录 (分页)
     *
     * @param userId
     * @param updateTime
     * @return
     */
    List<ReIosMissionUserSituation> selectByUserIdAndTime(Integer userId, String updateTime);

}