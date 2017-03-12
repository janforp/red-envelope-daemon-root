package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidMissionDepthDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidMissionDepth;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-05
 */
@Repository
public class ReAndroidMissionDepthDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidMissionDepthDAO {
    public int deleteByPrimaryKey(Long missionId, Integer depthId) {
        ReAndroidMissionDepth _key = new ReAndroidMissionDepth();
        _key.setMissionId(missionId);
        _key.setDepthId(depthId);
        return getSqlSession().delete("re_android_mission_depth.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidMissionDepth record) {
        getSqlSession().insert("re_android_mission_depth.insert", record);
    }

    public void insertSelective(ReAndroidMissionDepth record) {
        getSqlSession().insert("re_android_mission_depth.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidMissionDepth> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_mission_depth.insertBatch", records);
    }

    public ReAndroidMissionDepth selectByPrimaryKey(Long missionId, Integer depthId) {
        ReAndroidMissionDepth _key = new ReAndroidMissionDepth();
        _key.setMissionId(missionId);
        _key.setDepthId(depthId);
        return getSqlSession().selectOne("re_android_mission_depth.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidMissionDepth record) {
        return getSqlSession().update("re_android_mission_depth.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidMissionDepth record) {
        return getSqlSession().update("re_android_mission_depth.updateByPrimaryKey", record);
    }


    /**
     * 查询所有深度体验任务
     *
     * @param missionId
     * @return
     */
    public List<ReAndroidMissionDepth> selectAllByMissionId(Long missionId) {
        return getSqlSession().selectList("re_android_mission_depth.selectAllByMissionId", missionId);
    }

}