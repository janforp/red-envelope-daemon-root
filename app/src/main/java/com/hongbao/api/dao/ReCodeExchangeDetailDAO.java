package com.hongbao.api.dao;

import com.hongbao.api.model.ReCodeExchangeDetail;
import com.hongbao.api.model.ReRecommendMission;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public interface ReCodeExchangeDetailDAO {
    int deleteByPrimaryKey(Long missionId, Integer userId);

    void insert(ReCodeExchangeDetail record);

    void insertSelective(ReCodeExchangeDetail record);

    void insertBatch(List<ReCodeExchangeDetail> records);

    ReCodeExchangeDetail selectByPrimaryKey(Long missionId, Integer userId);

    int updateByPrimaryKeySelective(ReCodeExchangeDetail record);

    int updateByPrimaryKey(ReCodeExchangeDetail record);

    /**
     * 查询1条用户已完成的关注任务
     *
     * @param userId
     * @return
     */
    ReCodeExchangeDetail selectByUserId(Integer userId);

    /**
     * 查询用户已完成的关注任务
     *
     * @param userId
     * @return
     */
    List<ReRecommendMission> selectFinishAttentionList(Integer userId);

}