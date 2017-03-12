package com.hongbao.api.dao;

import com.hongbao.api.model.ReRecommendTask;
import com.hongbao.api.model.dto.GreatMissionInfo;
import com.hongbao.api.model.dto.ReTaskInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-28
 */
public interface ReRecommendTaskDAO {
    int deleteByPrimaryKey(Long taskId);

    void insert(ReRecommendTask record);

    void insertSelective(ReRecommendTask record);

    void insertBatch(List<ReRecommendTask> records);

    ReRecommendTask selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(ReRecommendTask record);

    int updateByPrimaryKey(ReRecommendTask record);

    /**
     * 我的任务
     * 5.0版本
     *
     * @param missionId
     * @param userId
     * @param platform
     * @param missionType
     * @return
     */
    List<GreatMissionInfo> selectMyMissionList(Long missionId, Integer userId, int platform, Integer missionType);

    /**
     * 获取1条用户已完成的任务
     *
     * @param userId
     * @return
     */
    ReRecommendTask selectOneByUserId(Integer userId);

    /**
     * 获取用户已完成的任务
     *
     * @param userId
     * @return
     */
    List<ReTaskInfo> selectByUserId(Integer userId);

    /**
     * 获取用户任务列表
     *
     * @param userId
     * @param taskStatus
     * @param taskId
     * @return
     */
    List<ReTaskInfo> selectUserTask(Integer userId, Integer taskStatus, Long taskId);

    /**
     * 查询任务
     *
     * @param taskId
     * @return
     */
    ReRecommendTask selectByIdAndStatus(Long taskId);

}