package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidAuxiliaryMissionFinishDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidAuxiliaryMissionFinish;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-04
 */
@Repository
public class ReAndroidAuxiliaryMissionFinishDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidAuxiliaryMissionFinishDAO {
    public int deleteByPrimaryKey(Long missionId, Integer missionNo, Integer userId) {
        ReAndroidAuxiliaryMissionFinish _key = new ReAndroidAuxiliaryMissionFinish();
        _key.setMissionId(missionId);
        _key.setMissionNo(missionNo);
        _key.setUserId(userId);
        return getSqlSession().delete("re_android_auxiliary_mission_finish.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidAuxiliaryMissionFinish record) {
        getSqlSession().insert("re_android_auxiliary_mission_finish.insert", record);
    }

    public void insertSelective(ReAndroidAuxiliaryMissionFinish record) {
        getSqlSession().insert("re_android_auxiliary_mission_finish.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidAuxiliaryMissionFinish> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_auxiliary_mission_finish.insertBatch", records);
    }

    public ReAndroidAuxiliaryMissionFinish selectByPrimaryKey(Long missionId, Integer missionNo, Integer userId) {
        ReAndroidAuxiliaryMissionFinish _key = new ReAndroidAuxiliaryMissionFinish();
        _key.setMissionId(missionId);
        _key.setMissionNo(missionNo);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_android_auxiliary_mission_finish.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidAuxiliaryMissionFinish record) {
        return getSqlSession().update("re_android_auxiliary_mission_finish.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidAuxiliaryMissionFinish record) {
        return getSqlSession().update("re_android_auxiliary_mission_finish.updateByPrimaryKey", record);
    }

    /**
     * 查询最新的一条完成情况
     *
     * @param missionId
     * @param userId
     * @return
     */
    public ReAndroidAuxiliaryMissionFinish selectByMissionIdAndUserId(Long missionId, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("missionId", missionId);
        map.put("userId", userId);
        return getSqlSession().selectOne("re_android_auxiliary_mission_finish.selectByMissionIdAndUserId", map);
    }


}