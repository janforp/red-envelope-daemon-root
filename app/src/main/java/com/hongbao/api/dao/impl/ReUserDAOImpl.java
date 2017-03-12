package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.model.ReUser;

import com.hongbao.api.model.cache.UserKeySecret;
import com.hongbao.api.model.dto.ReUserMoneyAndScore;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-03
 */
@Repository
public class ReUserDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserDAO {
    public int deleteByPrimaryKey(Integer userId) {
        ReUser _key = new ReUser();
        _key.setUserId(userId);
        return getSqlSession().delete("re_user.deleteByPrimaryKey", _key);
    }

    public void insert(ReUser record) {
        getSqlSession().insert("re_user.insert", record);
    }

    public void insertSelective(ReUser record) {
        getSqlSession().insert("re_user.insertSelective", record);
    }

    public void insertBatch(List<ReUser> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user.insertBatch", records);
    }

    public ReUser selectByPrimaryKey(Integer userId) {
        ReUser _key = new ReUser();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_user.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUser record) {
        return getSqlSession().update("re_user.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUser record) {
        return getSqlSession().update("re_user.updateByPrimaryKey", record);
    }

    /**
     * 获取用户的status
     * @param userId
     * @return
     */
    public Integer selectStatusByUserId(Integer userId) {
        return getSqlSession().selectOne("re_user.selectStatusByUserId", userId);
    }

    /**
     * 通过userKey获取用户秘钥
     * @param userKey
     * @return
     */
    public UserKeySecret selectUserByUserKey(String userKey) {
        return getSqlSession().selectOne("re_user.selectUserByUserKey", userKey);
    }

    /**
     * 查询有效用户
     * @param userId
     * @return
     */
    public ReUser selectByUserIdAndStatus(Integer userId) {
        return getSqlSession().selectOne("re_user.selectByUserIdAndStatus", userId);
    }

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    public ReUser login(String loginName, String password) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("loginName", loginName);
        map.put("password", password);
        return getSqlSession().selectOne("re_user.login", map);
    }

    /**
     * 通过手机查询用户
     * @param mobile
     * @return
     */
    public Integer selectUserIdByMobile(String mobile) {
        return getSqlSession().selectOne("re_user.selectUserIdByMobile", mobile);
    }

    /**
     * 查询userKey
     * @param userId
     * @return
     */
    public String selectUserKeyByUserId(Integer userId) {
        return getSqlSession().selectOne("re_user.selectUserKeyByUserId", userId);
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public ReUser selectLockByUserId(Integer userId) {
        return getSqlSession().selectOne("re_user.selectLockByUserId", userId);
    }

    /**
     * 通过手机查询用户
     * @param mobile
     * @return
     */
    public ReUser selectUserByMobile(String mobile) {
        return getSqlSession().selectOne("re_user.selectUserByMobile", mobile);
    }

    /**
     * 查询用户余额积分
     * @param userId
     * @return
     */
    public ReUserMoneyAndScore selectUserMoneyAndScore(int userId) {
        return getSqlSession().selectOne("re_user.selectUserMoneyAndScore", userId);
    }

    /**
     * 查询邀请码是否重复
     */
    public int selectInvitationCode(String invitationCode) {
        return getSqlSession().selectOne("re_user.selectInvitationCode", invitationCode);
    }

    /**
     * 通过邀请码查询用户
     * @param invitationCode
     * @return
     */
    public Integer selectUserIdByInvitationCode(String invitationCode) {
        return getSqlSession().selectOne("re_user.selectUserIdByInvitationCode", invitationCode);
    }

    /**
     * 通过userKey获取用户
     * @param userKey
     * @return
     */
    public ReUser selectUserLockByUserKey(String userKey) {
        return getSqlSession().selectOne("re_user.selectUserLockByUserKey", userKey);
    }

    /**
     * 重置免费领取红包机会
     * @param freeTimes
     * @return
     */
    public int updateFreeTimes(int freeTimes) {
        return getSqlSession().update("re_user.updateFreeTimes", freeTimes);
    }

    /**
     * 获取一些用户数据用于测试
     * @return
     */
    public List<ReUser> getUserList(){
        return getSqlSession().selectList("re_user.getUserList");
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public ReUser selectUserLockByUserId(Integer userId) {
        return getSqlSession().selectOne("re_user.selectUserLockByUserId", userId);
    }

    /**
     * 修改用户
     * @param record
     * @return
     */
    public int updateSelective(ReUser record) {
        return getSqlSession().update("re_user.updateSelective", record);
    }

}