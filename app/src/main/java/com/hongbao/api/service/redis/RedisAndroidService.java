package com.hongbao.api.service.redis;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.*;
import com.hongbao.api.model.*;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Summer on 2017/1/5.
 */
@Service
public class RedisAndroidService {

    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_android;
    @Autowired
    private ReAndroidMissionDAO reAndroidMissionDAO;
    @Autowired
    private ReAndroidMissionDepthDAO reAndroidMissionDepthDAO;
    @Autowired
    private ReAndroidAuxiliaryMissionDAO reAndroidAuxiliaryMissionDAO;
    @Autowired
    private ReAndroidMissionFinishDAO reAndroidMissionFinishDAO;
    @Autowired
    private ReAndroidAuxiliaryMissionFinishDAO reAndroidAuxiliaryMissionFinishDAO;
    @Autowired
    private ReAndroidMissionInstallDAO reAndroidMissionInstallDAO;
    @Autowired
    private ReAndroidMissionDepthKeepDAO reAndroidMissionDepthKeepDAO;

    /**
     * 从缓存中获取所有可做的积分墙任务
     *
     * @return
     */
    public List<ReAndroidMission> selectAndroidIntegralWall() {
        List<ReAndroidMission> list = cacheRedisTemplate_android.getObj(CacheConsts.INDEX_INTEGRAL_WALL);
        if(list == null || list.size() == 0) { // 缓存中取不到,从数据库中取
            String nowDay = CommonMethod.fmtDay(System.currentTimeMillis());
            list = reAndroidMissionDAO.selectAllByDay(nowDay);
            if(list != null && list.size() >0) {
                updateAndroidIntegralWall(CacheConsts.INDEX_INTEGRAL_WALL, list);
            }
        }
        return list;
    }

    /**
     * 设置积分墙任务 缓存1天
     */
    public void updateAndroidIntegralWall(String key, List<ReAndroidMission> list) {
        cacheRedisTemplate_android.setex(key, list, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 从缓存中获取用户已做包名的列表
     *
     * @return
     */
    public List<String> selectFinishAppPackageList(Integer userId) {
        String key = CacheConsts.USER_WALL + userId;
        List<String> list = cacheRedisTemplate_android.getObj(key);
        if(list == null) { // 缓存中取不到,从数据库中取
            list = reAndroidMissionFinishDAO.selectFinishAppPackageList(userId);
            if(list != null) {
                updateFinishAppPackageList(key, list);
            }
        }
        return list;
    }

    /**
     * 设置包名列表缓存1天
     */
    public void updateFinishAppPackageList(String key, List<String> list) {
        cacheRedisTemplate_android.setex(key, list, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 从缓存中获取用户正在做的积分墙任务id列表
     *
     * @return
     */
    public List<Long> selectUnderWayId(Integer userId) {
        String key = CacheConsts.USER_UNDERWAY_WALL + userId;
        List<Long> list = cacheRedisTemplate_android.getObj(key);
        if(list == null) { // 缓存中取不到,从数据库中取
            list = reAndroidMissionInstallDAO.selectUnderWayId(userId);
            if(list != null) {
                updateUnderWayId(key, list);
            }
        }
        return list;
    }

    /**
     * 设置正在做的积分墙任务id列表 缓存1小时
     */
    public void updateUnderWayId(String key, List<Long> list) {
        cacheRedisTemplate_android.setex(key, list, CacheConsts.SECONDS_OF_ONE_HOUR);
    }

    /**
     * 查询积分墙任务详情
     *
     * @param missionId
     * @return
     */
    public ReAndroidMission selectAndroidMission(Long missionId) {
        String key = CacheConsts.MISSION_INFO + missionId;
        ReAndroidMission info = cacheRedisTemplate_android.getObj(key);
        if(info == null) {
            info = reAndroidMissionDAO.selectByPrimaryKey(missionId);
            if(info != null) {
                updateAndroidMission(key, info);
            }
        }
        return info;
    }

    /**
     * 设置积分墙任务详情 缓存1天
     */
    public void updateAndroidMission(String key, ReAndroidMission info) {
        cacheRedisTemplate_android.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 深度任务列表
     *
     * @param missionId
     * @return
     */
    public List<ReAndroidMissionDepth> selectDepthList(Long missionId) {
        String key = CacheConsts.DEPTH_LIST + missionId;
        List<ReAndroidMissionDepth> depthList = cacheRedisTemplate_android.getObj(key);
        if(depthList == null || depthList.size() == 0) {
            depthList = reAndroidMissionDepthDAO.selectAllByMissionId(missionId);
            if(depthList != null) {
                updateDepthList(key, depthList);
            }
        }
        return depthList;
    }

    /**
     * 设置深度任务列表 缓存1天
     */
    public void updateDepthList(String key, List<ReAndroidMissionDepth> depthList) {
        cacheRedisTemplate_android.setex(key, depthList, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 附属任务列表
     *
     * @param missionId
     * @return
     */
    public List<ReAndroidAuxiliaryMission> selectAuxiliaryList(Long missionId) {
        String key = CacheConsts.AUX_LIST + missionId;
        List<ReAndroidAuxiliaryMission> auxiliaryList = cacheRedisTemplate_android.getObj(key);
        if(auxiliaryList == null || auxiliaryList.size() == 0) {
            auxiliaryList = reAndroidAuxiliaryMissionDAO.selectAllByMissionId(missionId);
            if(auxiliaryList != null) {
                updateAuxiliaryList(key, auxiliaryList);
            }
        }
        return auxiliaryList;
    }

    /**
     * 设置附属任务列表 缓存1天
     */
    public void updateAuxiliaryList(String key, List<ReAndroidAuxiliaryMission> auxiliaryList) {
        cacheRedisTemplate_android.setex(key, auxiliaryList, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 用户完成情况
     *
     * @param missionId
     * @param userId
     * @return
     */
    public ReAndroidMissionFinish selectUserFinishInfo(Long missionId, Integer userId) {
        String key = CacheConsts.FINISH_INFO + missionId + "_" + userId;
        ReAndroidMissionFinish info = cacheRedisTemplate_android.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reAndroidMissionFinishDAO.selectByPrimaryKey(missionId, userId);
            if(info != null) {
                updateUserFinishInfo(key, info);
            }
        }
        return info;
    }

    /**
     * 设置用户完成情况 缓存1天
     */
    public void updateUserFinishInfo(String key, ReAndroidMissionFinish info) {
        cacheRedisTemplate_android.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 用户完成附属任务情况
     *
     * @param missionId
     * @param userId
     * @return
     */
    public ReAndroidAuxiliaryMissionFinish selectUserFinishAuxiliaryInfo(Long missionId, Integer userId) {
        String key = CacheConsts.FINISH_AUX_INFO + missionId + "_" + userId;
        ReAndroidAuxiliaryMissionFinish info = cacheRedisTemplate_android.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reAndroidAuxiliaryMissionFinishDAO.selectByMissionIdAndUserId(missionId, userId);
            if(info != null) {
                updateUserFinishAuxiliaryInfo(key, info);
            }
        }
        return info;
    }

    /**
     * 设置用户完成附属任务情况 缓存1天
     */
    public void updateUserFinishAuxiliaryInfo(String key, ReAndroidAuxiliaryMissionFinish info) {
        cacheRedisTemplate_android.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 单个留存
     *
     * @param missionId
     * @param depthId
     * @return
     */
    public ReAndroidMissionDepth selectMissionDepth(Long missionId, Integer depthId) {
        String key = CacheConsts.DEPTH_INFO + missionId + "_" + depthId;
        ReAndroidMissionDepth info = cacheRedisTemplate_android.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reAndroidMissionDepthDAO.selectByPrimaryKey(missionId, depthId);
            if(info != null) {
                updateDepthInfo(key, info);
            }
        }
        return info;
    }

    /**
     * 设置单个留存 缓存1天
     */
    public void updateDepthInfo(String key, ReAndroidMissionDepth info) {
        cacheRedisTemplate_android.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 留存指标
     *
     * @param missionId
     * @param depthId
     * @param dataDay
     * @return
     */
    public ReAndroidMissionDepthKeep selectMissionDepthKeep(Long missionId, Integer depthId, String dataDay) {
        String key = CacheConsts.KEEP_INFO + missionId + "_" + dataDay;
        ReAndroidMissionDepthKeep info = cacheRedisTemplate_android.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reAndroidMissionDepthKeepDAO.selectByPrimaryKey(missionId, depthId, dataDay);
            if(info != null) {
                updateKeepInfo(key, info);
            }
        }
        return info;
    }

    /**
     * 设置指标 缓存1天
     */
    public void updateKeepInfo(String key, ReAndroidMissionDepthKeep info) {
        cacheRedisTemplate_android.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 附属任务详情
     *
     * @param missionId
     * @param missionNo
     * @return
     */
    public ReAndroidAuxiliaryMission selectAuxMission(Long missionId, Integer missionNo) {
        String key = CacheConsts.AUX_INFO + missionId + "_" + missionNo;
        ReAndroidAuxiliaryMission info = cacheRedisTemplate_android.getObj(key);
        if(info == null) { // 缓存中取不到,从数据库中取
            info = reAndroidAuxiliaryMissionDAO.selectByPrimaryKey(missionId, missionNo);
            if(info != null) {
                updateAuxMissionInfo(key, info);
            }
        }
        return info;
    }

    /**
     * 设置附属任务详情 缓存1天
     */
    public void updateAuxMissionInfo(String key, ReAndroidAuxiliaryMission info) {
        cacheRedisTemplate_android.setex(key, info, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 查询专属任务id列表
     *
     * @param userId
     * @param dayTime
     * @return
     */
    public List<Long> selectExclusiveId(Integer userId, String dayTime) {
        String key = CacheConsts.EXC_LIST + userId;
        List<Long> list = cacheRedisTemplate_android.getObj(key);
        if(list == null || list.size() == 0) {
            list = reAndroidMissionFinishDAO.selectExclusiveId(userId, dayTime);
            if(list != null) {
                updateExclusiveId(key, list);
            }
        }
        return list;
    }

    /**
     * 设置专属任务id列表 缓存1天
     */
    public void updateExclusiveId(String key, List<Long> list) {
        cacheRedisTemplate_android.setex(key, list, CacheConsts.SECONDS_OF_ONE_DAY);
    }

}
