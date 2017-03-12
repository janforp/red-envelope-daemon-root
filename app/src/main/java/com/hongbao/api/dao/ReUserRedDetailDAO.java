package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserRedDetail;
import com.hongbao.api.model.dto.RedDetailInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
public interface ReUserRedDetailDAO {
    int deleteByPrimaryKey(Long detailId);

    void insert(ReUserRedDetail record);

    void insertSelective(ReUserRedDetail record);

    void insertBatch(List<ReUserRedDetail> records);

    ReUserRedDetail selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(ReUserRedDetail record);

    int updateByPrimaryKey(ReUserRedDetail record);

    /**
     * 查询用户是否领取红包
     *
     * @param redId
     * @param userId
     * @return
     */
    ReUserRedDetail selectByRedIdAndUserId(Long redId, Integer userId);

    /**
     * 查询领取红包列表
     *
     * @param redId
     * @return
     */
    List<RedDetailInfo> selectByRedId(Long redId);

}