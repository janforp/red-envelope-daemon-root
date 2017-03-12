package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReIosMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReIosMission;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
@Repository
public class ReIosMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReIosMissionDAO {
    public int deleteByPrimaryKey(Long missionId) {
        ReIosMission _key = new ReIosMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_ios_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReIosMission record) {
        getSqlSession().insert("re_ios_mission.insert", record);
    }

    public void insertSelective(ReIosMission record) {
        getSqlSession().insert("re_ios_mission.insertSelective", record);
    }

    public void insertBatch(List<ReIosMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_ios_mission.insertBatch", records);
    }

    public ReIosMission selectByPrimaryKey(Long missionId) {
        ReIosMission _key = new ReIosMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_ios_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIosMission record) {
        return getSqlSession().update("re_ios_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIosMission record) {
        return getSqlSession().update("re_ios_mission.updateByPrimaryKey", record);
    }

    /**
     * 查询可做的ios任务
     *
     * @param nowTime
     * @return
     */
    public List<ReIosMission> selectAllByTime(String nowTime) {
        return getSqlSession().selectList("re_ios_mission.selectAllByTime", nowTime);
    }

}