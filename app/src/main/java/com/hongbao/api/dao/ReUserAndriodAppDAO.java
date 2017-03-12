package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserAndriodApp;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-14
 */
public interface ReUserAndriodAppDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReUserAndriodApp record);

    void insertSelective(ReUserAndriodApp record);

    void insertBatch(List<ReUserAndriodApp> records);

    ReUserAndriodApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReUserAndriodApp record);

    int updateByPrimaryKey(ReUserAndriodApp record);

    /**
     * 删除
     * @param userId
     * @return
     */
    int deleteByUserId(int userId);



}