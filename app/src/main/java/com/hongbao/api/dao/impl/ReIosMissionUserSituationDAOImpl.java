package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReIosMissionUserSituationDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReIosMissionUserSituation;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-16
 */
@Repository
public class ReIosMissionUserSituationDAOImpl extends BaseSqlSessionDaoSupport
        implements ReIosMissionUserSituationDAO {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReIosMissionUserSituation _key = new ReIosMissionUserSituation();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_ios_mission_user_situation.deleteByPrimaryKey", _key);
    }

    public void insert(ReIosMissionUserSituation record) {
        getSqlSession().insert("re_ios_mission_user_situation.insert", record);
    }

    public void insertSelective(ReIosMissionUserSituation record) {
        getSqlSession().insert("re_ios_mission_user_situation.insertSelective", record);
    }

    public void insertBatch(List<ReIosMissionUserSituation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_ios_mission_user_situation.insertBatch", records);
    }

    public ReIosMissionUserSituation selectByPrimaryKey(Long missionId, Integer userId) {
        ReIosMissionUserSituation _key = new ReIosMissionUserSituation();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_ios_mission_user_situation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIosMissionUserSituation record) {
        return getSqlSession().update("re_ios_mission_user_situation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIosMissionUserSituation record) {
        return getSqlSession().update("re_ios_mission_user_situation.updateByPrimaryKey", record);
    }

    /**
     * 查询已做的任务记录 (分页)
     *
     * @param userId
     * @param updateTime
     * @return
     */
    public List<ReIosMissionUserSituation> selectByUserIdAndTime(Integer userId, String updateTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("updateTime", updateTime);
        return getSqlSession().selectList("re_ios_mission_user_situation.selectByUserIdAndTime", map);
    }

}