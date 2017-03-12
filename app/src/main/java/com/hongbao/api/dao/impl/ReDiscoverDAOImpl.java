package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReDiscoverDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReDiscover;

import com.hongbao.api.model.dto.ReDiscoverInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-15
 */
@Repository
public class ReDiscoverDAOImpl extends BaseSqlSessionDaoSupport
        implements ReDiscoverDAO {
    public int deleteByPrimaryKey(Integer discoverId) {
        ReDiscover _key = new ReDiscover();
        _key.setDiscoverId(discoverId);
        return getSqlSession().delete("re_discover.deleteByPrimaryKey", _key);
    }

    public void insert(ReDiscover record) {
        getSqlSession().insert("re_discover.insert", record);
    }

    public void insertSelective(ReDiscover record) {
        getSqlSession().insert("re_discover.insertSelective", record);
    }

    public void insertBatch(List<ReDiscover> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_discover.insertBatch", records);
    }

    public ReDiscover selectByPrimaryKey(Integer discoverId) {
        ReDiscover _key = new ReDiscover();
        _key.setDiscoverId(discoverId);
        return getSqlSession().selectOne("re_discover.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDiscover record) {
        return getSqlSession().update("re_discover.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDiscover record) {
        return getSqlSession().update("re_discover.updateByPrimaryKey", record);
    }

    /**
     * 查询发现列表
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public List<ReDiscoverInfo> selectDiscoverByPlatform(int platform, String version, String packageName, String channelName) {

        Map<String, Object> map = new HashMap<>(4);
        map.put("platform", platform);
        map.put("version", version);
        map.put("packageName", packageName);
        map.put("channelName", channelName);
        return getSqlSession().selectList("re_discover.selectDiscoverByPlatform", map);

    }

}