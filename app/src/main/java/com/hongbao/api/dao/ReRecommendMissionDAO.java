package com.hongbao.api.dao;

import com.hongbao.api.model.ReRecommendMission;
import com.hongbao.api.model.dto.GreatMissionInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-28
 */
public interface ReRecommendMissionDAO {
    int deleteByPrimaryKey(Long missionId);

    void insert(ReRecommendMission record);

    void insertSelective(ReRecommendMission record);

    void insertBatch(List<ReRecommendMission> records);

    ReRecommendMission selectByPrimaryKey(Long missionId);

    int updateByPrimaryKeySelective(ReRecommendMission record);

    int updateByPrimaryKey(ReRecommendMission record);

    /**
     * 高额任务列表
     * 5.0版本
     *
     * @param missionId
     * @param userId
     * @param platform
     * @param today
     * @return
     */
    List<GreatMissionInfo> selectGreatMissionList(Long missionId, Integer userId, int platform, String today);

    /**
     * 查询推荐任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    List<ReRecommendMission> selectByPlatformAndUserId(int platform, Integer userId, String today);


    /**
     * 查询正在进行中的关注任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    List<ReRecommendMission> selectUnderwayAttentionList(int platform, Integer userId, String today);

    /**
     * 查询正在进行中的高额任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    List<ReRecommendMission> selectUnderwayGreatList(int platform, Integer userId, String today);


    /**
     * 查询已结束的高额任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    List<ReRecommendMission> selectOverGreatList(int platform, Integer userId, String today);

}