package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserChannelDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserChannel;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-16
 */
@Repository
public class ReUserChannelDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserChannelDAO {
    public int deleteByPrimaryKey(Long id) {
        ReUserChannel _key = new ReUserChannel();
        _key.setId(id);
        return getSqlSession().delete("re_user_channel.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserChannel record) {
        getSqlSession().insert("re_user_channel.insert", record);
    }

    public void insertSelective(ReUserChannel record) {
        getSqlSession().insert("re_user_channel.insertSelective", record);
    }

    public void insertBatch(List<ReUserChannel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_channel.insertBatch", records);
    }

    public ReUserChannel selectByPrimaryKey(Long id) {
        ReUserChannel _key = new ReUserChannel();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_channel.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserChannel record) {
        return getSqlSession().update("re_user_channel.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserChannel record) {
        return getSqlSession().update("re_user_channel.updateByPrimaryKey", record);
    }

    /**
     * 通过设备号查找用户id
     * @param deviceId
     * @return
     */
    public Integer selectUserIdByDeviceId(String deviceId) {
        return getSqlSession().selectOne("re_user_channel.selectUserIdByDeviceId", deviceId);
    }

    /**
     * 通过用户id查询
     * @param userId
     * @return
     */
    public ReUserChannel selectByUserId(Integer userId) {
        return getSqlSession().selectOne("re_user_channel.selectByUserId", userId);
    }

}