package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidMissionDepthKeepDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidMissionDepthKeep;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-06
 */
@Repository
public class ReAndroidMissionDepthKeepDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidMissionDepthKeepDAO {
    public int deleteByPrimaryKey(Long missionId, Integer depthId, String dataDay) {
        ReAndroidMissionDepthKeep _key = new ReAndroidMissionDepthKeep();
        _key.setMissionId(missionId);
        _key.setDepthId(depthId);
        _key.setDataDay(dataDay);
        return getSqlSession().delete("re_android_mission_depth_keep.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidMissionDepthKeep record) {
        getSqlSession().insert("re_android_mission_depth_keep.insert", record);
    }

    public void insertSelective(ReAndroidMissionDepthKeep record) {
        getSqlSession().insert("re_android_mission_depth_keep.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidMissionDepthKeep> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_mission_depth_keep.insertBatch", records);
    }

    public ReAndroidMissionDepthKeep selectByPrimaryKey(Long missionId, Integer depthId, String dataDay) {
        ReAndroidMissionDepthKeep _key = new ReAndroidMissionDepthKeep();
        _key.setMissionId(missionId);
        _key.setDepthId(depthId);
        _key.setDataDay(dataDay);
        return getSqlSession().selectOne("re_android_mission_depth_keep.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidMissionDepthKeep record) {
        return getSqlSession().update("re_android_mission_depth_keep.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidMissionDepthKeep record) {
        return getSqlSession().update("re_android_mission_depth_keep.updateByPrimaryKey", record);
    }

    /**
     * 查询真实数量
     *
     * @param missionId
     * @param depthId
     * @param dataDay
     * @return
     */
    public int selectRealNumLock(Long missionId, Integer depthId, String dataDay) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("missionId", missionId);
        map.put("depthId", depthId);
        map.put("dataDay", dataDay);
        return getSqlSession().selectOne("re_android_mission_depth_keep.selectRealNumLock", map);
    }

}