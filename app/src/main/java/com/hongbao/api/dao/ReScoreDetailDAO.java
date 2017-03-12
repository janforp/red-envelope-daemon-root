package com.hongbao.api.dao;

import com.hongbao.api.model.ReScoreDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-10
 */
public interface ReScoreDetailDAO {
    int deleteByPrimaryKey(Integer scoreId);

    void insert(ReScoreDetail record);

    void insertSelective(ReScoreDetail record);

    void insertBatch(List<ReScoreDetail> records);

    ReScoreDetail selectByPrimaryKey(Integer scoreId);

    int updateByPrimaryKeySelective(ReScoreDetail record);

    int updateByPrimaryKey(ReScoreDetail record);

    /**
     * 查询金币明细
     * @param userId
     * @param scoreId
     * @return
     */
    List<ReScoreDetail> selectScoreDetailList(Integer userId, Integer scoreId);

    /**
     * 查询今日收入
     *
     * @param userId
     * @param dayTime
     * @return
     */
    Integer selectByUserIdAndDay(Integer userId, String dayTime);


}