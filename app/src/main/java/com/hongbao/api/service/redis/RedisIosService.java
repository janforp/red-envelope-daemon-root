package com.hongbao.api.service.redis;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReIosMissionDAO;
import com.hongbao.api.dao.ReIosMissionFinishDAO;
import com.hongbao.api.dao.ReIosMissionStepDAO;
import com.hongbao.api.model.ReIosMission;
import com.hongbao.api.model.ReIosMissionFinish;
import com.hongbao.api.model.ReIosMissionStep;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2017/1/10.
 */
@Service
public class RedisIosService {

    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_ios;
    @Autowired
    private ReIosMissionDAO reIosMissionDAO;
    @Autowired
    private ReIosMissionStepDAO reIosMissionStepDAO;
    @Autowired
    private ReIosMissionFinishDAO reIosMissionFinishDAO;

    /**
     * 从缓存中获取所有可做的IOS任务
     *
     * @return
     */
    public List<ReIosMission> selectIosMissionList() {
        List<ReIosMission> list = cacheRedisTemplate_ios.getObj(CacheConsts.INDEX_IOS_MISSION);
        if(list == null || list.size() == 0) { // 缓存中取不到,从数据库中取
            String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());
            list = reIosMissionDAO.selectAllByTime(nowTime);
            if(list != null && list.size() > 0) {
                updateIosMissionList(CacheConsts.INDEX_IOS_MISSION, list);
            }
        }
        return list;
    }

    /**
     * 设置IOS任务 缓存1天
     */
    public void updateIosMissionList(String key, List<ReIosMission> list) {
        cacheRedisTemplate_ios.setex(key, list, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 从缓存中获取用户已做的任务id列表
     *
     * @return
     */
    public List<Long> selectFinishMissionList(Integer userId) {
        String key = CacheConsts.USER_IOS + userId;
        List<Long> list = cacheRedisTemplate_ios.getObj(key);
        if(list == null) { // 缓存中取不到,从数据库中取
            list = reIosMissionFinishDAO.selectFinishMissionList(userId);
            if(list != null) {
                updateFinishMissionList(key, list);
            }else {
                list = new ArrayList<>(1);
            }
        }
        return list;
    }

    /**
     * 设置任务id列表 缓存1天
     */
    public void updateFinishMissionList(String key, List<Long> list) {
        cacheRedisTemplate_ios.setex(key, list, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 从缓存中获取所有可做的IOS任务
     *
     * @return
     */
    public ReIosMission selectIosMission(Long missionId) {
        String key = CacheConsts.IOS_MISSION_INFO + missionId;
        ReIosMission info = cacheRedisTemplate_ios.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reIosMissionDAO.selectByPrimaryKey(missionId);
            if(info != null) {
                updateIosMission(key, info);
            }
        }
        return info;
    }

    /**
     * 设置IOS任务 缓存1天
     */
    public void updateIosMission(String key, ReIosMission info) {
        cacheRedisTemplate_ios.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 用户完成任务情况
     *
     * @param missionId
     * @param userId
     * @return
     */
    public ReIosMissionFinish selectUserFinishInfo(Long missionId, Integer userId) {
        String key = CacheConsts.FINISH_IOS_INFO + missionId + "_" + userId;
        ReIosMissionFinish info = cacheRedisTemplate_ios.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reIosMissionFinishDAO.selectByMissionIdAndUserId(missionId, userId);
            if(info != null) {
                updateUserFinishInfo(key, info);
            }
        }
        return info;
    }

    /**
     * 设置用户完成任务情况 缓存1天
     */
    public void updateUserFinishInfo(String key, ReIosMissionFinish info) {
        cacheRedisTemplate_ios.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 某一步任务详情
     *
     * @param missionId
     * @param stepId
     * @return
     */
    public ReIosMissionStep selectMissionStep(Long missionId, Integer stepId) {
        String key = CacheConsts.STEP_INFO + missionId + "_" + stepId;
        ReIosMissionStep info = cacheRedisTemplate_ios.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reIosMissionStepDAO.selectByPrimaryKey(missionId, stepId);
            if(info != null) {
                updateMissionStep(key, info);
            }
        }
        return info;
    }

    /**
     * 设置某一步任务详情 缓存1天
     */
    public void updateMissionStep(String key, ReIosMissionStep info) {
        cacheRedisTemplate_ios.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 附属任务列表
     *
     * @param missionId
     * @return
     */
    public List<ReIosMissionStep> selectIosMissionStepList(Long missionId) {
        String key = CacheConsts.IOS_LIST + missionId;
        List<ReIosMissionStep> list = cacheRedisTemplate_ios.getObj(key);
        if(list == null || list.size() == 0) {
            list = reIosMissionStepDAO.selectAllByMissionId(missionId);
            if(list != null) {
                updateIosMissionStepList(key, list);
            }
        }
        return list;
    }

    /**
     * 设置附属任务列表 缓存1天
     */
    public void updateIosMissionStepList(String key, List<ReIosMissionStep> list) {
        cacheRedisTemplate_ios.setex(key, list, CacheConsts.SECONDS_OF_ONE_DAY);
    }

}
