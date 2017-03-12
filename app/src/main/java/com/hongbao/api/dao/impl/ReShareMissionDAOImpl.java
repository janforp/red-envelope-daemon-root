package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReShareMissionDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReShareMission;

import com.hongbao.api.util.CommonMethod;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-14
 */
@Repository
public class ReShareMissionDAOImpl extends BaseSqlSessionDaoSupport
        implements ReShareMissionDAO {
    public int deleteByPrimaryKey(Long missionId) {
        ReShareMission _key = new ReShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_share_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReShareMission record) {
        getSqlSession().insert("re_share_mission.insert", record);
    }

    public void insertSelective(ReShareMission record) {
        getSqlSession().insert("re_share_mission.insertSelective", record);
    }

    public void insertBatch(List<ReShareMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_share_mission.insertBatch", records);
    }

    public ReShareMission selectByPrimaryKey(Long missionId) {
        ReShareMission _key = new ReShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_share_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReShareMission record) {
        return getSqlSession().update("re_share_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReShareMission record) {
        return getSqlSession().update("re_share_mission.updateByPrimaryKey", record);
    }

    /**
     * 获取所有已经开始的任务,
     * 剩余次数为0的也取出来
     * @return
     */
    public List<ReShareMission> getListByStartTime(){
        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());
        return getSqlSession().selectList("re_share_mission.getList", nowTime);
    }


    /**
     * 查询正在进行中分享任务数量
     *
     * @return
     */
    public int selectShareCount() {
        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());
        return getSqlSession().selectOne("re_share_mission.selectShareCount", nowTime);
    }

}