package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReMission;

import com.hongbao.api.model.dto.ReMissionInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-19
 */
@Repository
public class ReMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReMissionDAO {
    public int deleteByPrimaryKey(Integer missionId) {
        ReMission _key = new ReMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReMission record) {
        getSqlSession().insert("re_mission.insert", record);
    }

    public void insertSelective(ReMission record) {
        getSqlSession().insert("re_mission.insertSelective", record);
    }

    public void insertBatch(List<ReMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_mission.insertBatch", records);
    }

    public ReMission selectByPrimaryKey(Integer missionId) {
        ReMission _key = new ReMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReMission record) {
        return getSqlSession().update("re_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReMission record) {
        return getSqlSession().update("re_mission.updateByPrimaryKey", record);
    }

    /**
     * 任务列表
     * @param platform
     * @return
     */
    public List<ReMissionInfo> selectByPlatform(int platform) {
        if(platform == 0) { // ios
            return getSqlSession().selectList("re_mission.selectIosMission");
        }else { // andriod
            return getSqlSession().selectList("re_mission.selectAndriodMission");
        }
    }

    /**
     * 首页任务列表
     * @param platform
     * @return
     */
    public List<ReMissionInfo> selectIndexByPlatform(int platform) {
        if(platform == 0) { // ios
            return getSqlSession().selectList("re_mission.selectIndexIosMission");
        }else { // andriod
            return getSqlSession().selectList("re_mission.selectIndexAndriodMission");
        }
    }


}