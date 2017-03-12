package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserChannel;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-16
 */
public interface ReUserChannelDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReUserChannel record);

    void insertSelective(ReUserChannel record);

    void insertBatch(List<ReUserChannel> records);

    ReUserChannel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReUserChannel record);

    int updateByPrimaryKey(ReUserChannel record);

    /**
     * 通过设备号查找用户id
     * @param deviceId
     * @return
     */
    Integer selectUserIdByDeviceId(String deviceId);


    /**
     * 通过用户id查询
     * @param userId
     * @return
     */
    ReUserChannel selectByUserId(Integer userId);

}