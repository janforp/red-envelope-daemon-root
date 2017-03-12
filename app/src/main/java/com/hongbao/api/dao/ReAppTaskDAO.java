package com.hongbao.api.dao;

import com.hongbao.api.model.ReAppTask;
import com.hongbao.api.model.dto.TaskInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
public interface ReAppTaskDAO {
    int deleteByPrimaryKey(Long taskId);

    void insert(ReAppTask record);

    void insertSelective(ReAppTask record);

    void insertBatch(List<ReAppTask> records);

    ReAppTask selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(ReAppTask record);

    int updateByPrimaryKey(ReAppTask record);

    /**
     * 查询1条用户完成的任务
     *
     * @param userId
     * @return
     */
    ReAppTask selectFinishByUserId(Integer userId);

    /**
     * 查询用户完成的任务
     *
     * @param userId
     * @param limitSize
     * @return
     */
    List<TaskInfo> selectFinishByUserIdAndSize(Integer userId, int limitSize);

    /**
     * 查询正在进行中的任务
     *
     * @param userId
     * @param deviceId
     * @return
     */
    ReAppTask selectByUserIdAndDeviceId(Integer userId, String deviceId);

    /**
     * 查询正在进行中的任务
     *
     * @param userId
     * @return
     */
    ReAppTask selectIngTaskByUserIdAndKeywordIdLock(Integer userId,Long keywordId);

    /**
     * 查询是否做过这个app的下载任务
     *
     * @param userId
     * @param appId
     * @return
     */
    ReAppTask selectByUserIdAndAppId(Integer userId, Long appId);

    /**
     * 查询超时任务列表
     *
     * @return
     */
    List<ReAppTask> selectOvertimeTask(Long createTime);

}