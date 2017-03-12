package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReIntegralWallDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReIntegralWall;

import com.hongbao.api.model.dto.ReIntegralWallInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-12
 */
@Repository
public class ReIntegralWallDAOImpl extends BaseSqlSessionDaoSupport
        implements ReIntegralWallDAO {
    public int deleteByPrimaryKey(Integer wallId) {
        ReIntegralWall _key = new ReIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().delete("re_integral_wall.deleteByPrimaryKey", _key);
    }

    public void insert(ReIntegralWall record) {
        getSqlSession().insert("re_integral_wall.insert", record);
    }

    public void insertSelective(ReIntegralWall record) {
        getSqlSession().insert("re_integral_wall.insertSelective", record);
    }

    public void insertBatch(List<ReIntegralWall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_integral_wall.insertBatch", records);
    }

    public ReIntegralWall selectByPrimaryKey(Integer wallId) {
        ReIntegralWall _key = new ReIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().selectOne("re_integral_wall.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIntegralWall record) {
        return getSqlSession().update("re_integral_wall.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIntegralWall record) {
        return getSqlSession().update("re_integral_wall.updateByPrimaryKey", record);
    }

    /**
     * 积分墙列表
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public List<ReIntegralWallInfo> selectWallListByPlatform(int platform, String version, String packageName,
                                                             String channelName) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("platform", platform);
        map.put("version", version);
        map.put("packageName", packageName);
        map.put("channelName", channelName);
        return getSqlSession().selectList("re_integral_wall.selectWallListByPlatform", map);
    }

}