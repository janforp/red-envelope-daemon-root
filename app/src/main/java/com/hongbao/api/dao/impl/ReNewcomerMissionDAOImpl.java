package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReNewcomerMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReNewcomerMission;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
@Repository
public class ReNewcomerMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReNewcomerMissionDAO {
    public int deleteByPrimaryKey(Long missionId) {
        ReNewcomerMission _key = new ReNewcomerMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_newcomer_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReNewcomerMission record) {
        getSqlSession().insert("re_newcomer_mission.insert", record);
    }

    public void insertSelective(ReNewcomerMission record) {
        getSqlSession().insert("re_newcomer_mission.insertSelective", record);
    }

    public void insertBatch(List<ReNewcomerMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_newcomer_mission.insertBatch", records);
    }

    public ReNewcomerMission selectByPrimaryKey(Long missionId) {
        ReNewcomerMission _key = new ReNewcomerMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_newcomer_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReNewcomerMission record) {
        return getSqlSession().update("re_newcomer_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReNewcomerMission record) {
        return getSqlSession().update("re_newcomer_mission.updateByPrimaryKey", record);
    }

    /**
     * 查询新手任务
     *
     * @param platform
     * @return
     */
    public List<ReNewcomerMission> selectByPlatform(int platform) {
        return getSqlSession().selectList("re_newcomer_mission.selectByPlatform", platform);
    }

}