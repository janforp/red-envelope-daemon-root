package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReRecommendMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReRecommendMission;

import com.hongbao.api.model.dto.GreatMissionInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-28
 */
@Repository
public class ReRecommendMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReRecommendMissionDAO {
    public int deleteByPrimaryKey(Long missionId) {
        ReRecommendMission _key = new ReRecommendMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_recommend_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMission record) {
        getSqlSession().insert("re_recommend_mission.insert", record);
    }

    public void insertSelective(ReRecommendMission record) {
        getSqlSession().insert("re_recommend_mission.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission.insertBatch", records);
    }

    public ReRecommendMission selectByPrimaryKey(Long missionId) {
        ReRecommendMission _key = new ReRecommendMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_recommend_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMission record) {
        return getSqlSession().update("re_recommend_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMission record) {
        return getSqlSession().update("re_recommend_mission.updateByPrimaryKey", record);
    }

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
    public List<GreatMissionInfo> selectGreatMissionList(Long missionId, Integer userId, int platform, String today){
        Map<String, Object> map = new HashMap<>(4);
        map.put("missionId", missionId);
        map.put("userId", userId);
        map.put("platform", platform);
        map.put("today", today);
        return getSqlSession().selectList("re_recommend_mission.selectGreatMissionList", map);
    }

    /**
     * 查询推荐任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    public List<ReRecommendMission> selectByPlatformAndUserId(int platform, Integer userId, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("platform", platform);
        map.put("userId", userId);
        map.put("today", today);
        return getSqlSession().selectList("re_recommend_mission.selectByPlatformAndUserId", map);
    }

    /**
     * 查询正在进行中的关注任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    public List<ReRecommendMission> selectUnderwayAttentionList(int platform, Integer userId, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("platform", platform);
        map.put("userId", userId);
        map.put("today", today);
        return getSqlSession().selectList("re_recommend_mission.selectUnderwayAttentionList", map);
    }

    /**
     * 查询正在进行中的高额任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    public List<ReRecommendMission> selectUnderwayGreatList(int platform, Integer userId, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("platform", platform);
        map.put("userId", userId);
        map.put("today", today);
        return getSqlSession().selectList("re_recommend_mission.selectUnderwayGreatList", map);
    }

    /**
     * 查询已结束的高额任务
     *
     * @param platform
     * @param userId
     * @param today
     * @return
     */
    public List<ReRecommendMission> selectOverGreatList(int platform, Integer userId, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("platform", platform);
        map.put("userId", userId);
        map.put("today", today);
        return getSqlSession().selectList("re_recommend_mission.selectOverGreatList", map);
    }

}