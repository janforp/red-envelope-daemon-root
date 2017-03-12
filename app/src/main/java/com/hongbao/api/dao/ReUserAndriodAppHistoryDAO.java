package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserAndriodAppHistory;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-14
 */
public interface ReUserAndriodAppHistoryDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReUserAndriodAppHistory record);

    void insertSelective(ReUserAndriodAppHistory record);

    void insertBatch(List<ReUserAndriodAppHistory> records);

    ReUserAndriodAppHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReUserAndriodAppHistory record);

    int updateByPrimaryKey(ReUserAndriodAppHistory record);

    /**
     * 查询历史记录
     *
     * @param userId
     * @param appPackage
     * @return
     */
    ReUserAndriodAppHistory selectByAppPackage(int userId, String appPackage);

}