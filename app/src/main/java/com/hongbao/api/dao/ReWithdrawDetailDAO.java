package com.hongbao.api.dao;

import com.hongbao.api.model.ReWithdrawDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
public interface ReWithdrawDetailDAO {
    int deleteByPrimaryKey(Long withdrawId);

    void insert(ReWithdrawDetail record);

    void insertSelective(ReWithdrawDetail record);

    void insertBatch(List<ReWithdrawDetail> records);

    ReWithdrawDetail selectByPrimaryKey(Long withdrawId);

    int updateByPrimaryKeySelective(ReWithdrawDetail record);

    int updateByPrimaryKey(ReWithdrawDetail record);

    /**
     * 查询已提现次数
     * @param userId
     * @param monthTime
     * @return
     */
    int selectTimesByUserId(int userId, String monthTime);

    /**
     * 查询提现记录
     * @param userId
     * @param withdrawStatus
     * @param withdrawId
     * @return
     */
    List<ReWithdrawDetail> selectWithdrawList(Integer userId, Integer withdrawStatus, Long withdrawId);



}