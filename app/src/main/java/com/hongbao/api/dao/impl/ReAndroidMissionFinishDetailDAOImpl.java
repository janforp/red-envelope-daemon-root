package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndroidMissionFinishDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndroidMissionFinishDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-12
 */
@Repository
public class ReAndroidMissionFinishDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndroidMissionFinishDetailDAO {
    public int deleteByPrimaryKey(Long detailId) {
        ReAndroidMissionFinishDetail _key = new ReAndroidMissionFinishDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_android_mission_finish_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndroidMissionFinishDetail record) {
        getSqlSession().insert("re_android_mission_finish_detail.insert", record);
    }

    public void insertSelective(ReAndroidMissionFinishDetail record) {
        getSqlSession().insert("re_android_mission_finish_detail.insertSelective", record);
    }

    public void insertBatch(List<ReAndroidMissionFinishDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_android_mission_finish_detail.insertBatch", records);
    }

    public ReAndroidMissionFinishDetail selectByPrimaryKey(Long detailId) {
        ReAndroidMissionFinishDetail _key = new ReAndroidMissionFinishDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_android_mission_finish_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndroidMissionFinishDetail record) {
        return getSqlSession().update("re_android_mission_finish_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndroidMissionFinishDetail record) {
        return getSqlSession().update("re_android_mission_finish_detail.updateByPrimaryKey", record);
    }
}