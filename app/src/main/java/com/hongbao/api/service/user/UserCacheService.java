package com.hongbao.api.service.user;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.dao.ReUserInvitationDAO;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.cache.UserKeySecret;
import com.wujie.common.beanutil.BeanUtils;
import com.wujie.common.redis.StringKeyRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wuqiang on 15-8-7.
 * <p/>
 * 定义：所有能通过缓存获取的到的数据/记录，均表面在数据库层，此数据为有效数据（已逻辑删除的数据不应被缓存查出）
 * <p/>
 * <p/>
 * Propagation属性
 *
 * @author wuqiang
 *         Transactional中Propagation属性有7个选项可供选择：
 *         <p/>
 *         Propagation.MANDATORY 。当前方法必须在已经定义的Transaction中运行，如果没有已定义的Transaction则抛出异常。
 *         Propagation.NESTED 。如果没有已定义的Transaction，当前方法新开一个Transaction并在该Transaction中运行。如果存在已定义的Transaction，当前方法在嵌套事务(Nested Transaction)中运行 — 嵌套事务中可以定义储存点，因此可以独立于外部的Transaction而进行rollback。
 *         Propagation.NEVER 。当前方法不应在Transaction中运行，如果存在已经定义的Transaction则抛出异常。
 *         Propagation.NOT_SUPPORTED 。当前方法不应在Transaction中运行，如果存在已经定义的Transaction，则该Transaction暂停(挂起)直至该方法运行完毕。
 *         Propagation.REQUIRED 。 默认值 。当前方法必须在Transaction中运行。如果存在已经定义的Transaction，则该方法在已定义的Transaction中运行；如果不存在已经定义的Transaction，则该方法新开一个Transaction并在其中运行。
 *         Propagation.REQUIRES_NEW 。当前方法必须在新开的Transaction中运行。如果存在已经定义的Transaction，则该已定义的Transaction暂停直至新开的Transaction执行完毕。
 *         Propagation.SUPPORTS 。当前方法不需要在Transaction中运行，但如果存在已经定义的Transaction，则该方法也可以在Transaction中正常执行。
 */
@Service
public class UserCacheService {

    @Autowired
    private StringKeyRedisTemplate userCacheRedisTemplate;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReUserInvitationDAO reUserInvitationDAO;

    /**
     * 通过userKey获取用户秘钥
     */
    public UserKeySecret getUserSecret(String userKey) {
        String key = CacheConsts.CACHE_USER_KEY + userKey;
        UserKeySecret userSecret = userCacheRedisTemplate.getObj(key);
        if (userSecret == null) {
            // 从数据库查询
            userSecret = reUserDAO.selectUserByUserKey(userKey);
            if (userSecret != null) {
                this.updateUserSecret(userSecret);
            }
        }
        return userSecret;
    }

    /**
     * 设置用户秘钥对象UserKeySecret，到缓存
     */
    public void updateUserSecret(UserKeySecret userKeySecret) {
        String key = CacheConsts.CACHE_USER_KEY + userKeySecret.getUserKey();
        userCacheRedisTemplate.setex(key, userKeySecret, CacheConsts.SECONDS_OF_ONE_HOUR);
    }

    /**
     * 延迟1分钟后，删除缓存的用户秘钥对象UserKeySecret
     */
    public void delayRemoveUserSecret(String userKey) {
        UserKeySecret userKeySecret = getUserSecret(userKey);
        String key = CacheConsts.CACHE_USER_KEY + userKey;
        userCacheRedisTemplate.setex(key, userKeySecret, CacheConsts.SECONDS_OF_ONE_MINUTE);
    }

    public UserInfo getUser(Integer userId) {
        if (userId == null) {
            return null;
        }
        String key = CacheConsts.CACHE_USER + userId.toString();
        UserInfo userInfo = userCacheRedisTemplate.getObj(key);
        if (userInfo == null) {
            ReUser reUser = reUserDAO.selectByUserIdAndStatus(userId);
            if (reUser != null) {
                userInfo = new UserInfo();
                BeanUtils.copyProperties(reUser, userInfo);
                this.updateUser(userInfo);
            }
        }
        return userInfo;
    }

    /**
     * 获取用户信息，包括黑名单
     * @param userId
     * @return
     */
    public UserInfo getUserIncludeBlack(Integer userId){
        if (userId == null) {
            return null;
        }
        String key = CacheConsts.CACHE_USER + userId.toString();
        UserInfo userInfo = userCacheRedisTemplate.getObj(key);
        if (userInfo == null) {
            ReUser reUser = reUserDAO.selectByPrimaryKey(userId);
            if (reUser != null) {
                userInfo = new UserInfo();
                BeanUtils.copyProperties(reUser, userInfo);
                this.updateUser(userInfo);
            }
        }
        return userInfo;
    }


    public void updateUser(UserInfo userInfo) {
        String key = CacheConsts.CACHE_USER + userInfo.getUserId().toString();
        userCacheRedisTemplate.setex(key, userInfo, CacheConsts.SECONDS_OF_ONE_HOUR);
    }

    public void removeUser(Integer userId) {
        String key = CacheConsts.CACHE_USER + userId.toString();
        userCacheRedisTemplate.delete(key);
    }

    /**
     * 查询邀请人id
     * @param userId
     * @return
     */
    public Integer selectInvitationUser(Integer userId) {
        String key = CacheConsts.INVITATION_USER + userId;
        Integer user_id = userCacheRedisTemplate.getObj(key);
        if(user_id == null) { // 从数据库查找
            user_id = reUserInvitationDAO.selectUserId(userId);
            if(user_id != null) {
                updateInvitationUser(key, user_id);
            }
        }
        return user_id;
    }

    /**
     * 邀请人id 缓存1周
     * @param key
     * @param userId
     */
    public void updateInvitationUser(String key, Integer userId) {
        userCacheRedisTemplate.setex(key, userId, CacheConsts.SECONDS_OF_ONE_WEEK);
    }

}
