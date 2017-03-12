package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReIosMissionStepDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReIosMissionStep;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
@Repository
public class ReIosMissionStepDAOImpl extends BaseSqlSessionDaoSupport
        implements ReIosMissionStepDAO {
    public int deleteByPrimaryKey(Long missionId, Integer stepId) {
        ReIosMissionStep _key = new ReIosMissionStep();
        _key.setMissionId(missionId);
        _key.setStepId(stepId);
        return getSqlSession().delete("re_ios_mission_step.deleteByPrimaryKey", _key);
    }

    public void insert(ReIosMissionStep record) {
        getSqlSession().insert("re_ios_mission_step.insert", record);
    }

    public void insertSelective(ReIosMissionStep record) {
        getSqlSession().insert("re_ios_mission_step.insertSelective", record);
    }

    public void insertBatch(List<ReIosMissionStep> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_ios_mission_step.insertBatch", records);
    }

    public ReIosMissionStep selectByPrimaryKey(Long missionId, Integer stepId) {
        ReIosMissionStep _key = new ReIosMissionStep();
        _key.setMissionId(missionId);
        _key.setStepId(stepId);
        return getSqlSession().selectOne("re_ios_mission_step.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIosMissionStep record) {
        return getSqlSession().update("re_ios_mission_step.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIosMissionStep record) {
        return getSqlSession().update("re_ios_mission_step.updateByPrimaryKey", record);
    }

    /**
     * 查询所有步骤
     * @param missionId
     * @return
     */
    public List<ReIosMissionStep> selectAllByMissionId(Long missionId) {
        return getSqlSession().selectList("re_ios_mission_step.selectAllByMissionId", missionId);
    }

    /**
     * 查询步骤详情
     * @param missionId
     * @param stepId
     * @return
     */
    public ReIosMissionStep selectLock(Long missionId, Integer stepId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("missionId", missionId);
        map.put("stepId", stepId);
        return getSqlSession().selectOne("re_ios_mission_step.selectLock", map);
    }

}