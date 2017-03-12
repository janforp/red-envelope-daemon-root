package com.hongbao.api.dao;

import com.hongbao.api.model.ReReceiveDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-18
 */
public interface ReReceiveDetailDAO {
    int deleteByPrimaryKey(Long detailId);

    void insert(ReReceiveDetail record);

    void insertSelective(ReReceiveDetail record);

    void insertBatch(List<ReReceiveDetail> records);

    ReReceiveDetail selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(ReReceiveDetail record);

    int updateByPrimaryKey(ReReceiveDetail record);

    /**
     * 查询是否领取定时红包
     * @param userId
     * @param redId
     * @param dayTime
     * @return
     */
    ReReceiveDetail selectIsReceiveFixedRed(int userId, int redId, String dayTime);

    /**
     * 获取前3名
     * @param redId
     * @param today
     * @return
     */
    List<ReReceiveDetail> getTop3Detail(Integer redId,String today);

}