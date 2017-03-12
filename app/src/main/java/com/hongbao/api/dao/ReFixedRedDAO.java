package com.hongbao.api.dao;

import com.hongbao.api.model.ReFixedRed;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-18
 */
public interface ReFixedRedDAO {
    int deleteByPrimaryKey(Integer fixedId);

    void insert(ReFixedRed record);

    void insertSelective(ReFixedRed record);

    void insertBatch(List<ReFixedRed> records);

    ReFixedRed selectByPrimaryKey(Integer fixedId);

    int updateByPrimaryKeySelective(ReFixedRed record);

    int updateByPrimaryKey(ReFixedRed record);

    /**
     * 查询定时红包列表
     * @param platform
     * @return
     */
    List<ReFixedRed> selectFixedRedByPlatform(int platform);

    /**
     * 查询所有定时红包
     * @return
     */
    List<ReFixedRed> selectAll();


    /**
     * 查询定时红包剩余
     *
     * @param fixedId
     * @return
     */
    int selectRemainder(int fixedId);


    /**
     * 查询定时红包
     * @param fixedId
     * @return
     */
    ReFixedRed selectNumLockById(int fixedId);


    /**
     * 更新剩余数量
     * @param fixedId
     * @param remainderNum
     * @return
     */
    int updateRemainder(int fixedId, int remainderNum);

}