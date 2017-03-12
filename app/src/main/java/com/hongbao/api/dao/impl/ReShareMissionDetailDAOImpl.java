package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReShareMissionDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReShareMissionDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-17
 */
@Repository
public class ReShareMissionDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReShareMissionDetailDAO {
    public int deleteByPrimaryKey(Long missionId, String openId) {
        ReShareMissionDetail _key = new ReShareMissionDetail();
        _key.setMissionId(missionId);
        _key.setOpenId(openId);
        return getSqlSession().delete("re_share_mission_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReShareMissionDetail record) {
        getSqlSession().insert("re_share_mission_detail.insert", record);
    }

    public void insertSelective(ReShareMissionDetail record) {
        getSqlSession().insert("re_share_mission_detail.insertSelective", record);
    }

    public void insertBatch(List<ReShareMissionDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_share_mission_detail.insertBatch", records);
    }

    public ReShareMissionDetail selectByPrimaryKey(Long missionId, String openId) {
        ReShareMissionDetail _key = new ReShareMissionDetail();
        _key.setMissionId(missionId);
        _key.setOpenId(openId);
        return getSqlSession().selectOne("re_share_mission_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReShareMissionDetail record) {
        return getSqlSession().update("re_share_mission_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReShareMissionDetail record) {
        return getSqlSession().update("re_share_mission_detail.updateByPrimaryKey", record);
    }


    /**
     * 获该任务已经有多少个用户参加了
     *
     * @param missionId
     * @return
     */
    public int selectPartInNum(Long missionId){
        return getSqlSession().selectOne("re_share_mission_detail.selectPartInNum",missionId);
    }

    /**
     * 查询1条用户分享的点击详情
     *
     * @param userId
     * @return
     */
    public ReShareMissionDetail selectByUserId(int userId) {
        return getSqlSession().selectOne("re_share_mission_detail.selectByUserId", userId);
    }

}