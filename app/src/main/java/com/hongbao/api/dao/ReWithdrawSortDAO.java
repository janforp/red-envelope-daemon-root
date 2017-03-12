package com.hongbao.api.dao;

import com.hongbao.api.model.ReWithdrawSort;
import com.hongbao.api.model.dto.ReWithdrawSortInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
public interface ReWithdrawSortDAO {
    int deleteByPrimaryKey(Integer withdrawSortId);

    void insert(ReWithdrawSort record);

    void insertSelective(ReWithdrawSort record);

    void insertBatch(List<ReWithdrawSort> records);

    ReWithdrawSort selectByPrimaryKey(Integer withdrawSortId);

    int updateByPrimaryKeySelective(ReWithdrawSort record);

    int updateByPrimaryKey(ReWithdrawSort record);

    /**
     * 查询提现列表
     *
     * @param platform
     * @return
     */
    List<ReWithdrawSortInfo> selectWithdrawSortByPlatform(int platform);


}