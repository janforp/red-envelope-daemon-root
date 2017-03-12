package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndriodDeepMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndriodDeepMission;

import com.hongbao.api.model.ReAndriodIntegralWall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-05
 */
@Repository
public class ReAndriodDeepMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndriodDeepMissionDAO {
    public int deleteByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodDeepMission _key = new ReAndriodDeepMission();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_andriod_deep_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndriodDeepMission record) {
        getSqlSession().insert("re_andriod_deep_mission.insert", record);
    }

    public void insertSelective(ReAndriodDeepMission record) {
        getSqlSession().insert("re_andriod_deep_mission.insertSelective", record);
    }

    public void insertBatch(List<ReAndriodDeepMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_andriod_deep_mission.insertBatch", records);
    }

    public ReAndriodDeepMission selectByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodDeepMission _key = new ReAndriodDeepMission();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_andriod_deep_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndriodDeepMission record) {
        return getSqlSession().update("re_andriod_deep_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndriodDeepMission record) {
        return getSqlSession().update("re_andriod_deep_mission.updateByPrimaryKey", record);
    }

    /**
     * 查询可做的深度任务
     *
     * @param userId
     * @param dayTime
     * @return
     */
    public List<ReAndriodIntegralWall> selectUnderwayByUserId(Integer userId, String dayTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectList("re_andriod_deep_mission.selectUnderwayByUserId", map);
    }


    /**
     * 查询 深度任务
     *
     * @param userId
     * @param wallId
     * @return
     */
    public ReAndriodDeepMission selectLockByWallId(Integer userId, Long wallId, String dayTime) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("wallId", wallId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectOne("re_andriod_deep_mission.selectLockByWallId", map);
    }

}