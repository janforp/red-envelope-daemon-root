package com.hongbao.api.dao;

import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.cache.UserKeySecret;
import com.hongbao.api.model.dto.ReUserMoneyAndScore;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-03
 */
public interface ReUserDAO {
    int deleteByPrimaryKey(Integer userId);

    void insert(ReUser record);

    void insertSelective(ReUser record);

    void insertBatch(List<ReUser> records);

    ReUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ReUser record);

    int updateByPrimaryKey(ReUser record);

    /**
     * 获取用户的status
     * @param userId
     * @return
     */
    Integer selectStatusByUserId(Integer userId);

    /**
     * 通过userKey获取用户秘钥
     * @param userKey
     * @return
     */
    UserKeySecret selectUserByUserKey(String userKey);

    /**
     * 查询有效用户
     * @param userId
     * @return
     */
    ReUser selectByUserIdAndStatus(Integer userId);

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    ReUser login(String loginName, String password);

    /**
     * 通过手机查询用户
     * @param mobile
     * @return
     */
    Integer selectUserIdByMobile(String mobile);

    /**
     * 查询userKey
     * @param userId
     * @return
     */
    String selectUserKeyByUserId(Integer userId);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    ReUser selectLockByUserId(Integer userId);

    /**
     * 通过手机查询用户
     * @param mobile
     * @return
     */
    ReUser selectUserByMobile(String mobile);

    /**
     * 查询用户余额积分
     * @param userId
     * @return
     */
    ReUserMoneyAndScore selectUserMoneyAndScore(int userId);

    /**
     * 查询邀请码是否重复
     */
    int selectInvitationCode(String invitationCode);

    /**
     * 通过邀请码查询用户
     * @param invitationCode
     * @return
     */
    Integer selectUserIdByInvitationCode(String invitationCode);


    /**
     * 通过userKey获取用户
     * @param userKey
     * @return
     */
    ReUser selectUserLockByUserKey(String userKey);

    /**
     * 重置免费领取红包机会
     * @param freeTimes
     * @return
     */
    int updateFreeTimes(int freeTimes);

    /**
     * 获取一些用户数据用于测试
     * @return
     */
    List<ReUser> getUserList();

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    ReUser selectUserLockByUserId(Integer userId);

    /**
     * 修改用户
     * @param record
     * @return
     */
    int updateSelective(ReUser record);

}