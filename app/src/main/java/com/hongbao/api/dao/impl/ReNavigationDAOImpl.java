package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReNavigationDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReNavigation;

import com.hongbao.api.model.dto.ReNavigationInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-15
 */
@Repository
public class ReNavigationDAOImpl extends BaseSqlSessionDaoSupport
        implements ReNavigationDAO {
    public int deleteByPrimaryKey(Integer navigationId) {
        ReNavigation _key = new ReNavigation();
        _key.setNavigationId(navigationId);
        return getSqlSession().delete("re_navigation.deleteByPrimaryKey", _key);
    }

    public void insert(ReNavigation record) {
        getSqlSession().insert("re_navigation.insert", record);
    }

    public void insertSelective(ReNavigation record) {
        getSqlSession().insert("re_navigation.insertSelective", record);
    }

    public void insertBatch(List<ReNavigation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_navigation.insertBatch", records);
    }

    public ReNavigation selectByPrimaryKey(Integer navigationId) {
        ReNavigation _key = new ReNavigation();
        _key.setNavigationId(navigationId);
        return getSqlSession().selectOne("re_navigation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReNavigation record) {
        return getSqlSession().update("re_navigation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReNavigation record) {
        return getSqlSession().update("re_navigation.updateByPrimaryKey", record);
    }

    /**
     * 查询首页导航
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public List<ReNavigationInfo> selectNavigationByPlatform(int platform, String version, String packageName, String channelName, boolean flag) {

        Map<String, Object> map = new HashMap<>(4);
        map.put("platform", platform);
        map.put("version", version);
        map.put("packageName", packageName);
        map.put("channelName", channelName);

        if(flag) { // 正常
            return getSqlSession().selectList("re_navigation.selectNormalNavigation", map);
        }else { // 审核中
            return getSqlSession().selectList("re_navigation.selectVerifyingNavigation", map);
        }

    }

}