package com.hongbao.api.dao;

import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.dto.IndexRollInfo;
import com.hongbao.api.model.dto.RollInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-18
 */
public interface ReAccountDetailDAO {
    int deleteByPrimaryKey(Long detailId);

    void insert(ReAccountDetail record);

    void insertSelective(ReAccountDetail record);

    void insertBatch(List<ReAccountDetail> records);

    ReAccountDetail selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(ReAccountDetail record);

    int updateByPrimaryKey(ReAccountDetail record);

    /**
     * 查询账户明细
     * @param userId
     * @param detailId
     * @return
     */
    List<ReAccountDetail> selectAccountDetailList(Integer userId, Long detailId);

    /**
     * 查询首页滚动信息
     * @return
     */
    List<RollInfo> selectRollList();

    /**
     * 查询今日收入
     *
     * @param userId
     * @param dayTime
     * @return
     */
    BigDecimal selectByUserIdAndDay(Integer userId, String dayTime);

    /**
     * 查询首页滚动信息
     * v4.0
     *
     * @return
     */
    List<IndexRollInfo> selectIndexRollList();


    /**
     * 查询用户分享任务累计收入
     *
     * @param userId
     * @return
     */
    BigDecimal selectShareByUserId(Integer userId);

}