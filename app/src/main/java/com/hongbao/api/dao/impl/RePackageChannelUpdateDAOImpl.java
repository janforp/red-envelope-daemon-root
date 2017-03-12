package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.RePackageChannelUpdateDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.RePackageChannelUpdate;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-11
 */
@Repository
public class RePackageChannelUpdateDAOImpl extends BaseSqlSessionDaoSupport
        implements RePackageChannelUpdateDAO {
    public int deleteByPrimaryKey(String deviceId, String channelName, String packageName) {
        RePackageChannelUpdate _key = new RePackageChannelUpdate();
        _key.setDeviceId(deviceId);
        _key.setChannelName(channelName);
        _key.setPackageName(packageName);
        return getSqlSession().delete("re_package_channel_update.deleteByPrimaryKey", _key);
    }

    public void insert(RePackageChannelUpdate record) {
        getSqlSession().insert("re_package_channel_update.insert", record);
    }

    public void insertSelective(RePackageChannelUpdate record) {
        getSqlSession().insert("re_package_channel_update.insertSelective", record);
    }

    public void insertBatch(List<RePackageChannelUpdate> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_package_channel_update.insertBatch", records);
    }

    public RePackageChannelUpdate selectByPrimaryKey(String deviceId, String channelName, String packageName) {
        RePackageChannelUpdate _key = new RePackageChannelUpdate();
        _key.setDeviceId(deviceId);
        _key.setChannelName(channelName);
        _key.setPackageName(packageName);
        return getSqlSession().selectOne("re_package_channel_update.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePackageChannelUpdate record) {
        return getSqlSession().update("re_package_channel_update.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePackageChannelUpdate record) {
        return getSqlSession().update("re_package_channel_update.updateByPrimaryKey", record);
    }
}