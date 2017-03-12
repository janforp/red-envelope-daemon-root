package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidMission;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
@Repository
public class ReAndroidMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidMissionDAO {
    public int deleteByPrimaryKey(Long missionId) {
        ReAndroidMission _key = new ReAndroidMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_android_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidMission record) {
        getSqlSession().insert("re_android_mission.insert", record);
    }

    public void insertSelective(ReAndroidMission record) {
        getSqlSession().insert("re_android_mission.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_mission.insertBatch", records);
    }

    public ReAndroidMission selectByPrimaryKey(Long missionId) {
        ReAndroidMission _key = new ReAndroidMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_android_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidMission record) {
        return getSqlSession().update("re_android_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidMission record) {
        return getSqlSession().update("re_android_mission.updateByPrimaryKey", record);
    }


    /**
     * 查询当日可做的积分墙
     *
     * @param nowDay
     * @return
     */
    public List<ReAndroidMission> selectAllByDay(String nowDay) {
        return getSqlSession().selectList("re_android_mission.selectAllByDay", nowDay);
    }

    /**
     * 查询剩余数量
     *
     * @param missionId
     * @return
     */
    public int selectLeftNumLock(Long missionId) {
        return getSqlSession().selectOne("re_android_mission.selectLeftNumLock", missionId);
    }

    /**
     * 修改剩余数量
     *
     * @param leftNum
     * @param missionId
     * @return
     */
    public int updateLeftNum(int leftNum, Long missionId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("leftNum", leftNum);
        map.put("missionId", missionId);
        return getSqlSession().update("re_android_mission.updateLeftNum", map);
    }

}