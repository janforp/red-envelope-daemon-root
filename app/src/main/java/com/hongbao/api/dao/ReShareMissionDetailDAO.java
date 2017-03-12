package com.hongbao.api.dao;

import com.hongbao.api.model.ReShareMissionDetail;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-17
 */
public interface ReShareMissionDetailDAO {
    int deleteByPrimaryKey(Long missionId, String openId);

    void insert(ReShareMissionDetail record);

    void insertSelective(ReShareMissionDetail record);

    void insertBatch(List<ReShareMissionDetail> records);

    ReShareMissionDetail selectByPrimaryKey(Long missionId, String openId);

    int updateByPrimaryKeySelective(ReShareMissionDetail record);

    int updateByPrimaryKey(ReShareMissionDetail record);

    /**
     * 获该任务已经有多少个用户参加了
     *
     * @param missionId
     * @return
     */
    int selectPartInNum(Long missionId);

    /**
     * 查询1条用户分享的点击详情
     *
     * @param userId
     * @return
     */
    ReShareMissionDetail selectByUserId(int userId);

}