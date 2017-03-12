package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidAuxiliaryMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidAuxiliaryMission;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
@Repository
public class ReAndroidAuxiliaryMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidAuxiliaryMissionDAO {
    public int deleteByPrimaryKey(Long missionId, Integer missionNo) {
        ReAndroidAuxiliaryMission _key = new ReAndroidAuxiliaryMission();
        _key.setMissionId(missionId);
        _key.setMissionNo(missionNo);
        return getSqlSession().delete("re_android_auxiliary_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidAuxiliaryMission record) {
        getSqlSession().insert("re_android_auxiliary_mission.insert", record);
    }

    public void insertSelective(ReAndroidAuxiliaryMission record) {
        getSqlSession().insert("re_android_auxiliary_mission.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidAuxiliaryMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_auxiliary_mission.insertBatch", records);
    }

    public ReAndroidAuxiliaryMission selectByPrimaryKey(Long missionId, Integer missionNo) {
        ReAndroidAuxiliaryMission _key = new ReAndroidAuxiliaryMission();
        _key.setMissionId(missionId);
        _key.setMissionNo(missionNo);
        return getSqlSession().selectOne("re_android_auxiliary_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidAuxiliaryMission record) {
        return getSqlSession().update("re_android_auxiliary_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidAuxiliaryMission record) {
        return getSqlSession().update("re_android_auxiliary_mission.updateByPrimaryKey", record);
    }

    /**
     * 查询所有附属任务
     *
     * @param missionId
     * @return
     */
    public List<ReAndroidAuxiliaryMission> selectAllByMissionId(Long missionId) {
        return getSqlSession().selectList("re_android_auxiliary_mission.selectAllByMissionId", missionId);
    }

    /**
     * 查询任务详情
     *
     * @param missionId
     * @param missionNo
     * @return
     */
    public ReAndroidAuxiliaryMission selectLock(Long missionId, Integer missionNo) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("missionId", missionId);
        map.put("missionNo", missionNo);
        return getSqlSession().selectOne("re_android_auxiliary_mission.selectLock", map);
    }


}