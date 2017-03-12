package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.RePackageChannelDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.RePackageChannel;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-25
 */
@Repository
public class RePackageChannelDAOImpl extends BaseSqlSessionDaoSupport
        implements RePackageChannelDAO {
    public int deleteByPrimaryKey(String channelName, String packageName) {
        RePackageChannel _key = new RePackageChannel();
        _key.setChannelName(channelName);
        _key.setPackageName(packageName);
        return getSqlSession().delete("re_package_channel.deleteByPrimaryKey", _key);
    }

    public void insert(RePackageChannel record) {
        getSqlSession().insert("re_package_channel.insert", record);
    }

    public void insertSelective(RePackageChannel record) {
        getSqlSession().insert("re_package_channel.insertSelective", record);
    }

    public void insertBatch(List<RePackageChannel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_package_channel.insertBatch", records);
    }

    public RePackageChannel selectByPrimaryKey(String channelName, String packageName) {
        RePackageChannel _key = new RePackageChannel();
        _key.setChannelName(channelName);
        _key.setPackageName(packageName);
        return getSqlSession().selectOne("re_package_channel.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePackageChannel record) {
        return getSqlSession().update("re_package_channel.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePackageChannel record) {
        return getSqlSession().update("re_package_channel.updateByPrimaryKey", record);
    }
}