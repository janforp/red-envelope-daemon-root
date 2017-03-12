package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidMissionFinishDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidMissionFinish;

import com.hongbao.api.model.dto.AndroidFinishInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
@Repository
public class ReAndroidMissionFinishDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidMissionFinishDAO {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReAndroidMissionFinish _key = new ReAndroidMissionFinish();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_android_mission_finish.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidMissionFinish record) {
        getSqlSession().insert("re_android_mission_finish.insert", record);
    }

    public void insertSelective(ReAndroidMissionFinish record) {
        getSqlSession().insert("re_android_mission_finish.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidMissionFinish> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_mission_finish.insertBatch", records);
    }

    public ReAndroidMissionFinish selectByPrimaryKey(Long missionId, Integer userId) {
        ReAndroidMissionFinish _key = new ReAndroidMissionFinish();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_android_mission_finish.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidMissionFinish record) {
        return getSqlSession().update("re_android_mission_finish.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidMissionFinish record) {
        return getSqlSession().update("re_android_mission_finish.updateByPrimaryKey", record);
    }

    /**
     * 用户做过的积分墙app包名列表
     *
     * @param userId
     * @return
     */
    public List<String> selectFinishAppPackageList(Integer userId) {
        return getSqlSession().selectList("re_android_mission_finish.selectFinishAppPackageList", userId);
    }

    /**
     * 查询专属
     *
     * @param userId
     * @param dayTime
     * @return
     */
    public List<Long> selectExclusiveId(Integer userId, String dayTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectList("re_android_mission_finish.selectExclusiveId", map);
    }

    /**
     * 查询完成列表 (分页)
     * @param userId
     * @param updateTime
     * @return
     */
    public List<AndroidFinishInfo> selectByUserIdAndTime(Integer userId, String updateTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("updateTime", updateTime);
        return getSqlSession().selectList("re_android_mission_finish.selectByUserIdAndTime", map);
    }

}