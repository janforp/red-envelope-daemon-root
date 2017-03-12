package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserRed;
import com.hongbao.api.model.dto.ReUserRedInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
public interface ReUserRedDAO {
    int deleteByPrimaryKey(Long redId);

    void insert(ReUserRed record);

    void insertSelective(ReUserRed record);

    void insertBatch(List<ReUserRed> records);

    ReUserRed selectByPrimaryKey(Long redId);

    int updateByPrimaryKeySelective(ReUserRed record);

    int updateByPrimaryKey(ReUserRed record);

    /**
     * 查询红包列表
     *
     * @param userId
     * @param redId
     * @return
     */
    List<ReUserRedInfo> selectRedList(Integer userId, Long redId);

    /**
     * 查询红包
     *
     * @param redId
     * @return
     */
    ReUserRed selectLockByRedId(Long redId);

    /**
     * 查询红包详情
     *
     * @param userId
     * @param redId
     * @return
     */
    ReUserRedInfo selectRedDetail(Integer userId, Long redId);

}