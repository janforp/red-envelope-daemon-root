package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidMissionInstallDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidMissionInstall;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-05
 */
@Repository
public class ReAndroidMissionInstallDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidMissionInstallDAO {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReAndroidMissionInstall _key = new ReAndroidMissionInstall();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_android_mission_install.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidMissionInstall record) {
        getSqlSession().insert("re_android_mission_install.insert", record);
    }

    public void insertSelective(ReAndroidMissionInstall record) {
        getSqlSession().insert("re_android_mission_install.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidMissionInstall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_mission_install.insertBatch", records);
    }

    public ReAndroidMissionInstall selectByPrimaryKey(Long missionId, Integer userId) {
        ReAndroidMissionInstall _key = new ReAndroidMissionInstall();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_android_mission_install.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidMissionInstall record) {
        return getSqlSession().update("re_android_mission_install.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidMissionInstall record) {
        return getSqlSession().update("re_android_mission_install.updateByPrimaryKey", record);
    }

    /**
     * 查询还未体验完的任务id列表
     *
     * @param userId
     * @return
     */
    public List<Long> selectUnderWayId(Integer userId) {
        return getSqlSession().selectList("re_android_mission_install.selectUnderWayId", userId);
    }

}