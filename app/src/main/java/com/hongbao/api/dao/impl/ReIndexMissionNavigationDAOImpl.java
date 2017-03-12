package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReIndexMissionNavigationDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReIndexMissionNavigation;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-10
 */
@Repository
public class ReIndexMissionNavigationDAOImpl extends BaseSqlSessionDaoSupport
        implements ReIndexMissionNavigationDAO {
    public int deleteByPrimaryKey(Long navigationId) {
        ReIndexMissionNavigation _key = new ReIndexMissionNavigation();
        _key.setNavigationId(navigationId);
        return getSqlSession().delete("re_index_mission_navigation.deleteByPrimaryKey", _key);
    }

    public void insert(ReIndexMissionNavigation record) {
        getSqlSession().insert("re_index_mission_navigation.insert", record);
    }

    public void insertSelective(ReIndexMissionNavigation record) {
        getSqlSession().insert("re_index_mission_navigation.insertSelective", record);
    }

    public void insertBatch(List<ReIndexMissionNavigation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_index_mission_navigation.insertBatch", records);
    }

    public ReIndexMissionNavigation selectByPrimaryKey(Long navigationId) {
        ReIndexMissionNavigation _key = new ReIndexMissionNavigation();
        _key.setNavigationId(navigationId);
        return getSqlSession().selectOne("re_index_mission_navigation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIndexMissionNavigation record) {
        return getSqlSession().update("re_index_mission_navigation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIndexMissionNavigation record) {
        return getSqlSession().update("re_index_mission_navigation.updateByPrimaryKey", record);
    }

    /**
     * 查询导航列表
     *
     * @param platform
     * @return
     */
    public List<ReIndexMissionNavigation> selectByPlatform(int platform) {
        return getSqlSession().selectList("re_index_mission_navigation.selectByPlatform", platform);
    }
}