package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReNewcomerMissionDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReNewcomerMissionDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
@Repository
public class ReNewcomerMissionDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReNewcomerMissionDetailDAO {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReNewcomerMissionDetail _key = new ReNewcomerMissionDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_newcomer_mission_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReNewcomerMissionDetail record) {
        getSqlSession().insert("re_newcomer_mission_detail.insert", record);
    }

    public void insertSelective(ReNewcomerMissionDetail record) {
        getSqlSession().insert("re_newcomer_mission_detail.insertSelective", record);
    }

    public void insertBatch(List<ReNewcomerMissionDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_newcomer_mission_detail.insertBatch", records);
    }

    public ReNewcomerMissionDetail selectByPrimaryKey(Long missionId, Integer userId) {
        ReNewcomerMissionDetail _key = new ReNewcomerMissionDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_newcomer_mission_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReNewcomerMissionDetail record) {
        return getSqlSession().update("re_newcomer_mission_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReNewcomerMissionDetail record) {
        return getSqlSession().update("re_newcomer_mission_detail.updateByPrimaryKey", record);
    }
}