package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAuxiliaryStatisticDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAuxiliaryStatistic;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-20
 */
@Repository
public class ReAuxiliaryStatisticDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAuxiliaryStatisticDAO {
    public int deleteByPrimaryKey(Long missionId, Integer stepId, Integer platform, String statisticTime) {
        ReAuxiliaryStatistic _key = new ReAuxiliaryStatistic();
        _key.setMissionId(missionId);
        _key.setStepId(stepId);
        _key.setPlatform(platform);
        _key.setStatisticTime(statisticTime);
        return getSqlSession().delete("re_auxiliary_statistic.deleteByPrimaryKey", _key);
    }

    public void insert(ReAuxiliaryStatistic record) {
        getSqlSession().insert("re_auxiliary_statistic.insert", record);
    }

    public void insertSelective(ReAuxiliaryStatistic record) {
        getSqlSession().insert("re_auxiliary_statistic.insertSelective", record);
    }

    public void insertBatch(List<ReAuxiliaryStatistic> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_auxiliary_statistic.insertBatch", records);
    }

    public ReAuxiliaryStatistic selectByPrimaryKey(Long missionId, Integer stepId, Integer platform, String statisticTime) {
        ReAuxiliaryStatistic _key = new ReAuxiliaryStatistic();
        _key.setMissionId(missionId);
        _key.setStepId(stepId);
        _key.setPlatform(platform);
        _key.setStatisticTime(statisticTime);
        return getSqlSession().selectOne("re_auxiliary_statistic.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAuxiliaryStatistic record) {
        return getSqlSession().update("re_auxiliary_statistic.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAuxiliaryStatistic record) {
        return getSqlSession().update("re_auxiliary_statistic.updateByPrimaryKey", record);
    }
}