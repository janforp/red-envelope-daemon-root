package com.hongbao.api.service;

import com.alibaba.fastjson.JSON;
import com.hongbao.api.config.Config;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.dao.*;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.*;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.model.vo.TrialVo;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.*;
import com.wujie.common.utils.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 2016/11/15.
 */
@Service
public class TrialTaskService {

    @Autowired
    private ReAppKeywordsDAO reAppKeywordsDAO;
    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReModuleUserInfoDAO reModuleUserInfoDAO;
    @Autowired
    private ReAndriodIntegralWallDAO reAndriodIntegralWallDAO;
    @Autowired
    private ReAndriodUserTaskDAO reAndriodUserTaskDAO;
    @Autowired
    private ReAndriodDeepMissionDAO reAndriodDeepMissionDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 完成试玩任务 (积分墙) 领取奖励
     *
     * @param request
     * @param missionId
     * @return
     */
    public String finish(HttpServletRequest request, Long missionId) {

        Map<String, Object> dataResult = new HashMap<>(2);

        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        ReAndriodIntegralWall reAndriodIntegralWall = reAndriodIntegralWallDAO.selectByPrimaryKey(missionId);
        BigDecimal money = reAndriodIntegralWall.getStepOneMoney();

        // 更新任务状态
        ReAndriodUserTask reAndriodUserTask = reAndriodUserTaskDAO.selectLockByUserIdAndWallId(userId, missionId);
        if(reAndriodUserTask == null) {
            dataResult.put("code", 0);
            dataResult.put("msg", "您已经领取过奖励了!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }
        reAndriodUserTask.setTaskStatus(1);
        reAndriodUserTask.setUpdateTime(nowTime);
        reAndriodUserTaskDAO.updateByPrimaryKeySelective(reAndriodUserTask);

        // 更新用户余额
        ReUser reUser = reUserDAO.selectLockByUserId(userId);
        reUser.setUserMoney(reUser.getUserMoney().add(money));
        reUser.setTodayMoney(reUser.getTodayMoney().add(money));
        reUser.setUpdateTime(now);
        reUserDAO.updateByPrimaryKeySelective(reUser);

        // 插入资金明细
        ReAccountDetail reAccountDetail = new ReAccountDetail();
        reAccountDetail.setUserId(userId);
        reAccountDetail.setAppId(reUser.getAppId());
        reAccountDetail.setAccountMoney(money);
        reAccountDetail.setDetailType(1);
        reAccountDetail.setMissionType(MissionType.demo_mission.val);
        reAccountDetail.setMissionSubtype(MissionSubtype.wall.val);
        reAccountDetail.setDetailContent("完成试玩任务["+reAndriodIntegralWall.getAppName()+"]");
        reAccountDetail.setDetailTime(nowTime);
        reAccountDetailDAO.insert(reAccountDetail);

        int dayNum = reAndriodIntegralWall.getStepTwoDay();
        if(dayNum > 0) { // 存在深度任务

            String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
            String deviceName = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_NAME);
            String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
            String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
            String appVersion = (String) request.getAttribute(RequestConsts.ATTR_VERSION);
            String userIp = RequestUtil.getClientIp(request);
            String userImsi = (String) request.getAttribute(RequestConsts.ATTR_IMSI);
            String userImei = (String) request.getAttribute(RequestConsts.ATTR_IMEI);
            String mobile = (String) request.getAttribute(RequestConsts.ATTR_MOBILE_NUM);
            String sim = (String) request.getAttribute(RequestConsts.ATTR_SIM_NUM);

            // 记录用户行为
            ReModuleUserInfo reModuleUserInfo = new ReModuleUserInfo();
            reModuleUserInfo.setMissionType(MissionType.deepness_mission.val);
            reModuleUserInfo.setMissionSubtype(MissionSubtype.other.val);
            reModuleUserInfo.setMissionId(missionId);
            reModuleUserInfo.setUserId(userId);
            reModuleUserInfo.setPlatfrom(1);
            reModuleUserInfo.setDeviceId(deviceId);
            reModuleUserInfo.setDeviceName(deviceName);
            reModuleUserInfo.setPackageName(packageName);
            reModuleUserInfo.setChannelName(channelName);
            reModuleUserInfo.setAppVersion(appVersion);
            reModuleUserInfo.setUserIp(userIp);
            reModuleUserInfo.setUserImsi(userImsi);
            reModuleUserInfo.setUserImei(userImei);
            reModuleUserInfo.setMobileNum(mobile);
            reModuleUserInfo.setSimNum(sim);
            reModuleUserInfo.setCreateTime(nowTime);
            reModuleUserInfoDAO.insert(reModuleUserInfo);

            // 创建深度任务明细
            ReAndriodDeepMission reAndriodDeepMission = new ReAndriodDeepMission();
            reAndriodDeepMission.setWallId(missionId);
            reAndriodDeepMission.setUserId(userId);
            reAndriodDeepMission.setInfoId(reModuleUserInfo.getInfoId());
            reAndriodDeepMission.setFinishTimes(0);
            reAndriodDeepMission.setStartTime(CommonMethod.fmtDay(now + 24 * 60 * 60 * 1000));
            reAndriodDeepMission.setEndTime(CommonMethod.fmtDay(now + dayNum * 24 * 60 * 60 * 1000));
            reAndriodDeepMission.setCreateTime(nowTime);
            reAndriodDeepMission.setUpdateTime(nowTime);
            reAndriodDeepMissionDAO.insertSelective(reAndriodDeepMission);

        }

        // 删除缓存
        userCacheService.removeUser(userId);

        dataResult.put("code", 1);
        dataResult.put("msg", "恭喜您获得"+money+"元奖励!");
        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 试玩任务列表 (andriod aso任务 积分墙)
     *
     * @param request
     * @param manufacturer
     * @param os
     * @param app
     * @return
     */
    public List<TrialModel> selectTrialList(HttpServletRequest request, String manufacturer, String os, String app, int sim) {

        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);

        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }


        List<TrialModel> modelList = new ArrayList<>();

        // 查询出来的app列表去重列表
        List<String> packageList = new ArrayList<>();
        // 用户的本地第三方app列表数据
        List<String> userAppList = JSON.parseArray(app, String.class);

        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());
        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        int underwayNum = 0;
        int nextNum = 0;

        // 正在进行
        {

            TrialModel underway = new TrialModel();
            underway.setTrialTitle("正在进行");
            underway.setTrialType(0);

            List<TrialVo> underwayData = new ArrayList<>();

            if(userId != null) {

                // 查询正在进行中的 积分墙任务
                ReAndriodUserTask andriodUserTask = reAndriodUserTaskDAO.selectByUserIdAndStatus(userId);

                if(andriodUserTask != null) { // 存在

                    TrialInfo trialInfo = reAndriodIntegralWallDAO.selectByWallId(andriodUserTask.getWallId());
                    TrialVo trialVo = underwayVo(trialInfo, 0);
                    underwayData.add(trialVo);
                    packageList.add(trialInfo.getAppPackage());

                }else { // 不存在

                    // 查询正在进行中的 aso任务
                    ReAppTask reAppTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);
                    if(reAppTask != null) { // 有进行中的
                        TrialInfo trialInfo = reAppKeywordsDAO.selectTrialByKeywordId(reAppTask.getKeywordId());
                        TrialVo trialVo = underwayVo(trialInfo, 0);
                        underwayData.add(trialVo);
                        packageList.add(trialInfo.getAppPackage());
                    }

                }

            }

            // 查出所有的可以做的任务(1.已经发放且还有剩余, 2.该用户手机上有的app排除, 3.一个app只查出一条word)
            List<TrialInfo> trialList = reAndriodIntegralWallDAO.selectAllByLeftAndTime(nowTime, userId, sim);

            for (TrialInfo trialInfo : trialList) {

                int missionType = trialInfo.getMissionType();

                if(missionType == 0) { // ASO任务

                    long appId = trialInfo.getAppId();
                    // 判断此用户,是否已经做过或在做此appId的任务,若有,则过滤掉
                    ReAppTask task = reAppTaskDAO.selectByUserIdAndAppId(userId, appId);
                    if(task == null) { // 未下过此app

                        String appMarket = trialInfo.getAppMarket();
                        String appPackage = trialInfo.getAppPackage();

                        if(!packageList.contains(appPackage) && !userAppList.contains(appPackage)) {

                            if(appMarket.contains("小米")) {
                                // 若是小米市场的任务,则只能由小米手机做。若不是小米手机,则不显示小米市场任务
                                if("xiaomi".equals(os)) {
                                    TrialVo trialVo = underwayVo(trialInfo, 2);
                                    underwayData.add(trialVo);
                                    packageList.add(appPackage);
                                }

                            }else if(appMarket.contains("魅族")){
                                //若是魅族市场的任务,则只能由魅族手机做。若不是魅族手机,则不显示魅族市场任务
                                if("meizu".equals(os)) {
                                    TrialVo trialVo = underwayVo(trialInfo, 2);
                                    underwayData.add(trialVo);
                                    packageList.add(appPackage);
                                }

                            }else {
                                //其他市场的任务,所有安卓机器都可以做
                                TrialVo trialVo = underwayVo(trialInfo, 2);
                                underwayData.add(trialVo);
                                packageList.add(appPackage);
                            }

                        }

                    }


                }else if(missionType == 1) { // 积分墙任务

                    long wallId = trialInfo.getMissionId();
                    // 判断此用户是否做过这个积分墙任务
                    ReAndriodUserTask reAndriodUserTask = reAndriodUserTaskDAO.selectByUserIdAndWallIdAndStatus(userId, wallId, 1);
                    if(reAndriodUserTask == null) { // 未做过
                        String appPackage = trialInfo.getAppPackage();
                        if(!packageList.contains(appPackage) && !userAppList.contains(appPackage)) {
                            TrialVo trialVo = underwayVo(trialInfo, 2);
                            underwayData.add(trialVo);
                            packageList.add(appPackage);
                        }
                    }

                }

            }

            if(underwayData.size() > 0) {
                underway.setTrialData(underwayData);
                modelList.add(underway);
            }

            underwayNum = underwayData.size();

        }


        // 即将开始
        {

            TrialModel next = new TrialModel();
            next.setTrialTitle("即将开始");
            next.setTrialType(1);

            List<TrialVo> nextData = new ArrayList<>();

            // 查询今天即将开始 (1.该用户手机上有的app排除, 2.一个app只查出一条word)
            List<TaskInfo> taskList = reAppKeywordsDAO.selectNextByTime(nowTime, today, userId);
            for (TaskInfo taskInfo : taskList) {
                long appId = taskInfo.getAppId();
                // 判断此用户,是否已经做过或在做此appId的任务,若有,则过滤掉
                ReAppTask task = reAppTaskDAO.selectByUserIdAndAppId(userId, appId);
                if(task == null) { // 未下过此app

                    String appPackage = taskInfo.getAppPackage();
                    String appMarket = taskInfo.getAppMarket();

                    if(!packageList.contains(appPackage) && !userAppList.contains(appPackage)) {

                        if(appMarket.contains("小米")) {
                            // 若是小米市场的任务,则只能由小米手机做。若不是小米手机,则不显示小米市场任务
                            if("xiaomi".equals(os)) {
                                TrialVo vo = nextVo(taskInfo);
                                nextData.add(vo);
                                packageList.add(appPackage);
                            }

                        }else if(appMarket.contains("魅族")){
                            //若是魅族市场的任务,则只能由魅族手机做。若不是魅族手机,则不显示魅族市场任务
                            if("meizu".equals(os)) {
                                TrialVo vo = nextVo(taskInfo);
                                nextData.add(vo);
                                packageList.add(appPackage);
                            }

                        }else {
                            //其他市场的任务,所有安卓机器都可以做
                            TrialVo vo = nextVo(taskInfo);
                            nextData.add(vo);
                            packageList.add(appPackage);
                        }

                    }

                }

            }

            if(nextData.size() == 0) { // 今日没有了

                String tomorrow = CommonMethod.fmtDay(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

                // 查询明天 (1.该用户手机上有的app排除, 2.一个app只查出一条word)
                List<TaskInfo> tomorrowTaskList = reAppKeywordsDAO.selectNextByTime(nowTime, tomorrow, userId);
                for (TaskInfo taskInfo : tomorrowTaskList) {

                    long appId = taskInfo.getAppId();

                    // 判断此用户,是否已经做过或在做此appId的任务,若有,则过滤掉
                    ReAppTask task = reAppTaskDAO.selectByUserIdAndAppId(userId, appId);

                    if(task == null) { // 未下过此app

                        String appPackage = taskInfo.getAppPackage();
                        String appMarket = taskInfo.getAppMarket();

                        if(!packageList.contains(appPackage) && !userAppList.contains(appPackage)) {

                            if(appMarket.contains("小米")) {
                                // 若是小米市场的任务,则只能由小米手机做。若不是小米手机,则不显示小米市场任务
                                if("xiaomi".equals(os)) {
                                    packageList.add(appPackage);
                                    TrialVo vo = nextVo(taskInfo);
                                    nextData.add(vo);
                                }

                            }else if(appMarket.contains("魅族")){
                                //若是魅族市场的任务,则只能由魅族手机做。若不是魅族手机,则不显示魅族市场任务
                                if("meizu".equals(os)) {
                                    packageList.add(appPackage);
                                    TrialVo vo = nextVo(taskInfo);
                                    nextData.add(vo);
                                }

                            }else {
                                //其他市场的任务,所有安卓机器都可以做
                                packageList.add(appPackage);
                                TrialVo vo = nextVo(taskInfo);
                                nextData.add(vo);
                            }

                        }

                    }

                }

            }

            if(nextData.size() > 0) {
                next.setTrialData(nextData);
                modelList.add(next);
            }

            nextNum = nextData.size();

        }


        if(userId != null)  {  // 已完成

            TrialModel finish = new TrialModel();
            finish.setTrialTitle("已完成");
            finish.setTrialType(2);

            List<TrialVo> finishData = new ArrayList<>();

            int totalNum = underwayNum + nextNum;

            int limitSize = 0;

            if(totalNum >= 15) { // 查询最近完成的2条记录
                limitSize = 2;
            }else { // 查询最近完成的10条记录
                limitSize = 10;
            }

            List<TaskInfo> finishList = reAppTaskDAO.selectFinishByUserIdAndSize(userId, limitSize);
            for (TaskInfo taskInfo : finishList) {
                TrialVo vo = finishVo(taskInfo);
                finishData.add(vo);
            }

            if(finishData.size() > 0) {
                finish.setTrialData(finishData);
                modelList.add(finish);
            }

        }

        return modelList;

    }

    /**
     * 设置正在进行中的 TrialVo
     * @param trialInfo
     * @param status
     * @return
     */
    private TrialVo underwayVo(TrialInfo trialInfo, int status) {

        TrialVo vo = new TrialVo();

        vo.setMissionId(trialInfo.getMissionId());
        vo.setMissionIcon(trialInfo.getMissionIcon());
        vo.setAppPackage(trialInfo.getAppPackage());
        vo.setMissionTitle(trialInfo.getMissionTitle());
        vo.setMissionStatus(status);
        vo.setMissionDesc(trialInfo.getMissionDesc());
        if(status == 0) { // 进行中
            vo.setStatusContent("进行中");
        }else if(status == 1) {  // 已完成
            vo.setStatusContent("已完成");
        }else {
            vo.setStatusContent(""+trialInfo.getMissionMoney());
        }

        List<String> appLabel = new ArrayList<>();
        String label = trialInfo.getAppLabel();
        int missionType = trialInfo.getMissionType();
        vo.setMissionType(missionType);
        if(missionType == 0) { // ASO任务

            vo.setMarketUrl(trialInfo.getMarketUrl());
            vo.setMarketPackage(trialInfo.getMarketPackage());

            String appMarket = trialInfo.getAppMarket();
            vo.setAppMarket(appMarket);

            appLabel.add(appMarket);
            if(!StringUtil.isEmpty(label)) {
                String[] labels = label.split(",");
                for (String lab : labels) {
                    appLabel.add(lab);
                }
            }
            appLabel.add("剩" + trialInfo.getLeftNum() + "份");
            vo.setAppLabel(appLabel);


            String detailUrl = Config.getRedBaseUrl() + "/c/p/a/task/timedDetail?keywordId=" + trialInfo.getMissionId();
            vo.setDetailUrl(detailUrl);


        }else if(missionType == 1) { // 积分墙任务

            if(!StringUtil.isEmpty(label)) {
                String[] labels = label.split(",");
                for (String lab : labels) {
                    appLabel.add(lab);
                }
            }
            Integer leftNum = trialInfo.getLeftNum();
            if(leftNum != null) {
                appLabel.add("剩" + trialInfo.getLeftNum() + "份");
            }
            vo.setAppLabel(appLabel);

            String trialHint = "首次安装注册并体验"+trialInfo.getOpenSecond()+"秒,奖励"+trialInfo.getStepOneMoney()+"元";

            String detailUrl = "hongbao://TrialAccountWebActivity?" +
                    "htmlUrl="+Config.getRedBaseUrl()+"/c/p/a/selfIntegral/detail/"+trialInfo.getMissionId()+
                    "&apkUrl="+trialInfo.getAppUrl()+
                    "&apkName="+trialInfo.getMissionTitle()+
                    "&packageName="+trialInfo.getAppPackage()+
                    "&second="+trialInfo.getOpenSecond()+
                    "&missionId="+trialInfo.getMissionId()+
                    "&trialHint=" + trialHint;
            vo.setDetailUrl(detailUrl);

        }

        return vo;

    }

    /**
     * 设置即将开始的 TrialVo
     *
     * @param taskInfo
     * @return
     */
    private TrialVo nextVo(TaskInfo taskInfo) {

        String today = CommonMethod.fmtDay(System.currentTimeMillis());
        String tomorrow = CommonMethod.fmtDay(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        String appMarket = taskInfo.getAppMarket();

        TrialVo vo = new TrialVo();
        vo.setMissionId(taskInfo.getKeywordId());
        vo.setMissionDesc(taskInfo.getMissionDesc());
        vo.setMissionIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/guess.png");
        vo.setAppPackage(taskInfo.getAppPackage());
        String title = taskInfo.getKeyword();
        if(!StringUtil.isEmpty(title)) {
            title = title.substring(0, 1)+"***";
        }else {
            title = "****";
        }
        vo.setMissionTitle(title);
        vo.setAppMarket(appMarket);
        vo.setMarketUrl(taskInfo.getMarketUrl());
        vo.setMarketPackage(taskInfo.getMarketPackage());

        String releaseTime = taskInfo.getReleaseTime();
        String day = releaseTime.substring(0, 10);
        String time = releaseTime.substring(11, 16);

        List<String> appLabel = new ArrayList<>();
        if(today.equals(day)) {
            appLabel.add("今日"+time);
        }else if(tomorrow.equals(day)) {
            appLabel.add("明日"+time);
        }
        appLabel.add(appMarket);
        appLabel.add("共" + taskInfo.getLeftNum() + "份");
        vo.setAppLabel(appLabel);

        vo.setMissionStatus(2);
        vo.setStatusContent(""+taskInfo.getMoney());

        String detailUrl = Config.getRedBaseUrl() + "/c/p/a/task/timedDetail?keywordId=" + taskInfo.getKeywordId();
        vo.setDetailUrl(detailUrl);

        return vo;

    }

    /**
     * 设置完成的 TrialVo
     *
     * @param taskInfo
     * @return
     */
    private TrialVo finishVo(TaskInfo taskInfo) {

        String appMarket = taskInfo.getAppMarket();

        TrialVo vo = new TrialVo();
        vo.setMissionIcon(taskInfo.getAppIcon());
        vo.setMissionTitle(taskInfo.getKeyword());
        vo.setMissionDesc(taskInfo.getMissionDesc());

        List<String> appLabel = new ArrayList<>();
        appLabel.add(appMarket);

        String label = taskInfo.getTaskLabel();
        if(!StringUtil.isEmpty(label)) {
            String[] labels = label.split(",");
            for (String lab : labels) {
                appLabel.add(lab);
            }
        }
        vo.setAppLabel(appLabel);

        vo.setMissionStatus(1);
        vo.setStatusContent("已完成");

        return vo;

    }

    /**
     * 抢任务
     *
     * @param request
     * @param missionType
     * @param missionId
     * @return
     */
    public String getMission2(HttpServletRequest request, int missionType, Long missionId) {

        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        String deviceName = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_NAME);
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        String appVersion = (String) request.getAttribute(RequestConsts.ATTR_VERSION);
        String userIp = RequestUtil.getClientIp(request);
        String userImsi = (String) request.getAttribute(RequestConsts.ATTR_IMSI);
        String userImei = (String) request.getAttribute(RequestConsts.ATTR_IMEI);
        String mobile = (String) request.getAttribute(RequestConsts.ATTR_MOBILE_NUM);
        String sim = (String) request.getAttribute(RequestConsts.ATTR_SIM_NUM);

        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        // 记录用户行为
        ReModuleUserInfo reModuleUserInfo = new ReModuleUserInfo();
        reModuleUserInfo.setMissionType(MissionType.demo_mission.val);
        if(missionType == 0) { // ASO任务
            reModuleUserInfo.setMissionSubtype(MissionSubtype.other.val);
        }else if(missionType == 1) { // 积分墙任务
            reModuleUserInfo.setMissionSubtype(MissionSubtype.wall.val);
        }
        reModuleUserInfo.setMissionId(missionId);
        reModuleUserInfo.setUserId(userId);
        reModuleUserInfo.setPlatfrom(1);
        reModuleUserInfo.setDeviceId(deviceId);
        reModuleUserInfo.setDeviceName(deviceName);
        reModuleUserInfo.setPackageName(packageName);
        reModuleUserInfo.setChannelName(channelName);
        reModuleUserInfo.setAppVersion(appVersion);
        reModuleUserInfo.setUserIp(userIp);
        reModuleUserInfo.setUserImsi(userImsi);
        reModuleUserInfo.setUserImei(userImei);
        reModuleUserInfo.setMobileNum(mobile);
        reModuleUserInfo.setSimNum(sim);
        reModuleUserInfo.setCreateTime(nowTime);
        reModuleUserInfoDAO.insert(reModuleUserInfo);


        Map<String, Object> dataResult = new HashMap<>(2);
        if(missionType == 0) { // ASO任务

            ReAppKeywords word = reAppKeywordsDAO.selectLockByKeywordId(missionId, nowTime);
            if(word == null) {
                dataResult.put("code", 0);
                dataResult.put("msg", "任务还没开始!");
            }else {
                int leftNum = word.getLeftNum();
                if (leftNum <= 0){
                    dataResult.put("code", 0);
                    dataResult.put("msg", "手慢了,没抢到任务!");
                }else {
                    word.setLeftNum(leftNum - 1);
                    reAppKeywordsDAO.updateByPrimaryKeySelective(word);

                    // 添加一条reAppTask任务,状态是进行中
                    ReAppTask task = new ReAppTask();
                    task.setUserId(userId);
                    task.setDeviceId(deviceId);
                    task.setKeywordId(missionId);
                    task.setTaskStep(1);
                    task.setAppId(word.getAppId());
                    task.setTaskStatus(0);
                    task.setCreateTime(System.currentTimeMillis());
                    task.setUpdateTime(System.currentTimeMillis());
                    reAppTaskDAO.insert(task);

                    dataResult.put("code", 1);
                    dataResult.put("msg", "任务领取成功!");
                }
            }

        }else if(missionType == 1) { // 积分墙任务

            ReAndriodIntegralWall reAndriodIntegralWall = reAndriodIntegralWallDAO.selectLockByWallId(missionId, nowTime);
            if(reAndriodIntegralWall == null) {
                dataResult.put("code", 0);
                dataResult.put("msg", "任务还没开始!");
            }else {

                int limitNum = reAndriodIntegralWall.getIsLimitNum();
                if(limitNum == 1) { // 限量
                    int leftNum = reAndriodIntegralWall.getLeftNum();
                    if (leftNum <= 0){
                        dataResult.put("code", 0);
                        dataResult.put("msg", "手慢了,没抢到任务!");
                        return JsonUtil.buildSuccessDataJson(dataResult);
                    }
                    reAndriodIntegralWall.setLeftNum(leftNum - 1);
                    reAndriodIntegralWallDAO.updateByPrimaryKeySelective(reAndriodIntegralWall);
                }

                // 任务领取半小时后过期
                String releaseTime = CommonMethod.fmtTime(now + 30 * 60 * 1000);

                // 判断是否领取过
                ReAndriodUserTask reAndriodUserTask = reAndriodUserTaskDAO.selectByUserIdAndWallIdAndStatus(userId, missionId, null);
                if(reAndriodUserTask == null) {
                    reAndriodUserTask = new ReAndriodUserTask();
                    reAndriodUserTask.setWallId(missionId);
                    reAndriodUserTask.setUserId(userId);
                    reAndriodUserTask.setInfoId(reModuleUserInfo.getInfoId());
                    reAndriodUserTask.setTaskStatus(0);
                    reAndriodUserTask.setCreateTime(nowTime);
                    reAndriodUserTask.setUpdateTime(nowTime);
                    reAndriodUserTask.setReleaseTime(releaseTime);
                    reAndriodUserTaskDAO.insert(reAndriodUserTask);
                }else {
                    reAndriodUserTask.setInfoId(reModuleUserInfo.getInfoId());
                    reAndriodUserTask.setTaskStatus(0);
                    reAndriodUserTask.setUpdateTime(nowTime);
                    reAndriodUserTask.setReleaseTime(releaseTime);
                    reAndriodUserTaskDAO.updateByPrimaryKeySelective(reAndriodUserTask);
                }

                dataResult.put("code", 1);
                dataResult.put("msg", "任务领取成功!");

            }

        }

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 放弃任务:先抢新的任务,再放弃之前的任务,因为若先放弃,新任务被抢完了,这样用户就一个任务都没了
     *
     * @param request
     * @param oldMissionType
     * @param oldMissionId
     * @param newMissionType
     * @param newMissionId
     * @return
     */
    public String giveUpOldMissionToGetNewMission2(HttpServletRequest request, int oldMissionType, Long oldMissionId, int newMissionType, Long newMissionId) {

        Map<String, Object> dataResult = new HashMap<>(2);

        String getNewMission = getMission2(request, newMissionType, newMissionId);
        Result result = FastJsonUtil.parseObject(getNewMission, Result.class);

        if (result.getData().getCode() == 1){// 新的任务抢成功了,才能放弃之前的任务

            String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
            long now = System.currentTimeMillis();
            String nowTime = CommonMethod.fmtTime(now);

            Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

            // 先查此人进行中的任务
            if(oldMissionType == 0) { // ASO任务

                // 把旧任务状态设为'放弃'
                ReAppTask ingTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);
                ingTask.setTaskStatus(2);
                ingTask.setUpdateTime(now);
                reAppTaskDAO.updateByPrimaryKeySelective(ingTask);

                // 旧任务
                ReAppKeywords wordIng = reAppKeywordsDAO.selectLockByKeywordId(oldMissionId, nowTime);
                Integer leftNum = wordIng.getLeftNum();
                wordIng.setLeftNum(leftNum + 1);
                reAppKeywordsDAO.updateByPrimaryKeySelective(wordIng);

            }else if(oldMissionType == 1) { // 积分墙任务

                // 把旧任务状态设为'放弃'
                ReAndriodUserTask reAndriodUserTask = reAndriodUserTaskDAO.selectLockByUserIdAndWallId(userId, oldMissionId);
                reAndriodUserTask.setTaskStatus(2);
                reAndriodUserTask.setUpdateTime(nowTime);
                reAndriodUserTaskDAO.updateByPrimaryKeySelective(reAndriodUserTask);

                // 旧任务
                ReAndriodIntegralWall reAndriodIntegralWall = reAndriodIntegralWallDAO.selectLockByWallId(oldMissionId, nowTime);
                int limitNum = reAndriodIntegralWall.getIsLimitNum();
                if(limitNum == 1) { // 限量
                    int leftNum = reAndriodIntegralWall.getLeftNum();
                    reAndriodIntegralWall.setLeftNum(leftNum + 1);
                    reAndriodIntegralWall.setUpdateTime(nowTime);
                    reAndriodIntegralWallDAO.updateByPrimaryKeySelective(reAndriodIntegralWall);
                }

            }

            dataResult.put("code", 1);
            dataResult.put("msg", "任务领取成功");

            return JsonUtil.buildSuccessDataJson(dataResult);

        }else {
            return getNewMission;
        }

    }




    /**
     * 试玩任务列表
     *
     *
     * @param userId
     * @param manufacturer
     * @param os
     * @param deviceId
     * @param app
     * @return
     */
    public List<TrialTaskModel> selectTrialList(Integer userId, String manufacturer, String os, String deviceId, String app) {

        List<TrialTaskModel> modelList = new ArrayList<>();

        // 查询出来的app列表去重列表
        List<String> packageList = new ArrayList<>();

        // 用户的本地第三方app列表数据
        List<String> userAppList = JSON.parseArray(app, String.class);

        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());
        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        int underwayNum = 0;
        int nextNum = 0;

        // 正在进行
        {

            TrialTaskModel underway = new TrialTaskModel();
            underway.setTitle("正在进行");
            underway.setType(1);

            List<TrialTaskInfo> underwayData = new ArrayList<>();

            if(userId != null) {

                // 查询是否有正在进行中的任务
                ReAppTask reAppTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);
                if(reAppTask != null) { // 有进行中的
                    TaskInfo taskInfo = reAppKeywordsDAO.selectByKeywordId(reAppTask.getKeywordId());
                    TrialTaskInfo trialTaskInfo = underwayInfo(taskInfo, 0);
                    underwayData.add(trialTaskInfo);
                    packageList.add(taskInfo.getAppPackage());
                }

            }

            // 查出所有的可以做的任务(1.已经发放且还有剩余, 2.该用户手机上有的app排除, 3.一个app只查出一条word)
            List<TaskInfo> taskList = reAppKeywordsDAO.selectAllByLeftAndTime(nowTime, userId);
            for (TaskInfo taskInfo : taskList) {
                long appId = taskInfo.getAppId();
                // 判断此用户,是否已经做过或在做此appId的任务,若有,则过滤掉
                ReAppTask task = reAppTaskDAO.selectByUserIdAndAppId(userId, appId);
                if(task == null) { // 未下过此app

                    String appMarket = taskInfo.getAppMarket();
                    String appPackage = taskInfo.getAppPackage();

                    if(!packageList.contains(appPackage) && !userAppList.contains(appPackage)) {

                        if(appMarket.contains("小米")) {
                            // 若是小米市场的任务,则只能由小米手机做。若不是小米手机,则不显示小米市场任务
                            if("xiaomi".equals(os)) {
                                TrialTaskInfo trialTaskInfo = underwayInfo(taskInfo, 2);
                                underwayData.add(trialTaskInfo);
                                packageList.add(appPackage);
                            }

                        }else if(appMarket.contains("魅族")){
                            //若是魅族市场的任务,则只能由魅族手机做。若不是魅族手机,则不显示魅族市场任务
                            if("meizu".equals(os)) {
                                TrialTaskInfo trialTaskInfo = underwayInfo(taskInfo, 2);
                                underwayData.add(trialTaskInfo);
                                packageList.add(appPackage);
                            }

                        }else {
                            //其他市场的任务,所有安卓机器都可以做
                            TrialTaskInfo trialTaskInfo = underwayInfo(taskInfo, 2);
                            underwayData.add(trialTaskInfo);
                            packageList.add(appPackage);
                        }

                    }

                }

            }

            if(underwayData.size() > 0) {
                underway.setData(underwayData);
                modelList.add(underway);
            }

            underwayNum = underwayData.size();

        }


        // 即将开始
        {

            TrialTaskModel next = new TrialTaskModel();
            next.setTitle("即将开始");
            next.setType(0);

            List<TrialTaskInfo> nextData = new ArrayList<>();

            // 查询今天即将开始 (1.该用户手机上有的app排除, 2.一个app只查出一条word)
            List<TaskInfo> taskList = reAppKeywordsDAO.selectNextByTime(nowTime, today, userId);
            for (TaskInfo taskInfo : taskList) {
                long appId = taskInfo.getAppId();
                // 判断此用户,是否已经做过或在做此appId的任务,若有,则过滤掉
                ReAppTask task = reAppTaskDAO.selectByUserIdAndAppId(userId, appId);
                if(task == null) { // 未下过此app

                    String appMarket = taskInfo.getAppMarket();
                    String appPackage = taskInfo.getAppPackage();

                    if(!packageList.contains(appPackage) && !userAppList.contains(appPackage)) {

                        if(appMarket.contains("小米")) {
                            // 若是小米市场的任务,则只能由小米手机做。若不是小米手机,则不显示小米市场任务
                            if("xiaomi".equals(os)) {
                                TrialTaskInfo trialTaskInfo = nextInfo(taskInfo);
                                nextData.add(trialTaskInfo);
                                packageList.add(appPackage);
                            }

                        }else if(appMarket.contains("魅族")){
                            //若是魅族市场的任务,则只能由魅族手机做。若不是魅族手机,则不显示魅族市场任务
                            if("meizu".equals(os)) {
                                TrialTaskInfo trialTaskInfo = nextInfo(taskInfo);
                                nextData.add(trialTaskInfo);
                                packageList.add(appPackage);
                            }

                        }else {
                            //其他市场的任务,所有安卓机器都可以做
                            TrialTaskInfo trialTaskInfo = nextInfo(taskInfo);
                            nextData.add(trialTaskInfo);
                            packageList.add(appPackage);
                        }

                    }

                }

            }

            if(nextData.size() == 0) { // 今日没有了

                String tomorrow = CommonMethod.fmtDay(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

                // 查询明天 (1.该用户手机上有的app排除, 2.一个app只查出一条word)
                List<TaskInfo> tomorrowTaskList = reAppKeywordsDAO.selectNextByTime(nowTime, tomorrow, userId);
                for (TaskInfo taskInfo : tomorrowTaskList) {

                    long appId = taskInfo.getAppId();

                    // 判断此用户,是否已经做过或在做此appId的任务,若有,则过滤掉
                    ReAppTask task = reAppTaskDAO.selectByUserIdAndAppId(userId, appId);

                    if(task == null) { // 未下过此app

                        String appPackage = taskInfo.getAppPackage();
                        String appMarket = taskInfo.getAppMarket();

                        if(!packageList.contains(appPackage) && !userAppList.contains(appPackage)) {

                            if(appMarket.contains("小米")) {
                                // 若是小米市场的任务,则只能由小米手机做。若不是小米手机,则不显示小米市场任务
                                if("xiaomi".equals(os)) {
                                    packageList.add(appPackage);
                                    TrialTaskInfo trialTaskInfo = nextInfo(taskInfo);
                                    nextData.add(trialTaskInfo);
                                }

                            }else if(appMarket.contains("魅族")){
                                //若是魅族市场的任务,则只能由魅族手机做。若不是魅族手机,则不显示魅族市场任务
                                if("meizu".equals(os)) {
                                    packageList.add(appPackage);
                                    TrialTaskInfo trialTaskInfo = nextInfo(taskInfo);
                                    nextData.add(trialTaskInfo);
                                }

                            }else {
                                //其他市场的任务,所有安卓机器都可以做
                                packageList.add(appPackage);
                                TrialTaskInfo trialTaskInfo = nextInfo(taskInfo);
                                nextData.add(trialTaskInfo);
                            }

                        }

                    }

                }

            }

            if(nextData.size() > 0) {
                next.setData(nextData);
                modelList.add(next);
            }

            nextNum = nextData.size();

        }


        if(userId != null)  {  // 已完成

            TrialTaskModel finish = new TrialTaskModel();
            finish.setTitle("已完成");
            finish.setType(0);

            List<TrialTaskInfo> finishData = new ArrayList<>();

            int totalNum = underwayNum + nextNum;

            int limitSize = 0;

            if(totalNum >= 15) { // 查询最近完成的2条记录
                limitSize = 2;
            }else { // 查询最近完成的10条记录
                limitSize = 10;
            }

            List<TaskInfo> finishList = reAppTaskDAO.selectFinishByUserIdAndSize(userId, limitSize);
            for (TaskInfo taskInfo : finishList) {
                TrialTaskInfo trialTaskInfo = finishInfo(taskInfo);
                finishData.add(trialTaskInfo);
            }

            if(finishData.size() > 0) {
                finish.setData(finishData);
                modelList.add(finish);
            }

        }

        return modelList;

    }

    /**
     * 设置正在进行中的 TrialTaskInfo
     *
     * @param taskInfo
     * @return
     */
    private TrialTaskInfo underwayInfo(TaskInfo taskInfo, int status) {

        String appMarket = taskInfo.getAppMarket();

        TrialTaskInfo trialTaskInfo = new TrialTaskInfo();
        trialTaskInfo.setKeywordId(taskInfo.getKeywordId());
        trialTaskInfo.setAppIcon(taskInfo.getAppIcon());
        trialTaskInfo.setAppPackage(taskInfo.getAppPackage());
        trialTaskInfo.setKeyword(taskInfo.getKeyword());
        trialTaskInfo.setAppMarket(appMarket);
        trialTaskInfo.setMarketUrl(taskInfo.getMarketUrl());
        trialTaskInfo.setMarketPackage(taskInfo.getMarketPackage());

        List<String> appLabel = new ArrayList<>();
        appLabel.add(appMarket);
        appLabel.add(taskInfo.getTaskLabel());
        appLabel.add("剩" + taskInfo.getLeftNum() + "份");
        trialTaskInfo.setAppLabel(appLabel);

        trialTaskInfo.setStatus(status);
        if(status == 0) { // 进行中
            trialTaskInfo.setDesc("进行中");
        }else if(status == 1) {  // 已完成
            trialTaskInfo.setDesc("已完成");
        }else {
            trialTaskInfo.setDesc(""+taskInfo.getMoney());
        }

        String detailUrl = Config.getRedBaseUrl() + "/c/p/a/task/timedDetail?keywordId=" + taskInfo.getKeywordId();
        trialTaskInfo.setDetailUrl(detailUrl);

        return trialTaskInfo;

    }


    /**
     * 设置即将开始的 TrialTaskInfo
     *
     * @param taskInfo
     * @return
     */
    private TrialTaskInfo nextInfo(TaskInfo taskInfo) {

        String today = CommonMethod.fmtDay(System.currentTimeMillis());
        String tomorrow = CommonMethod.fmtDay(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        String appMarket = taskInfo.getAppMarket();

        TrialTaskInfo trialTaskInfo = new TrialTaskInfo();
        trialTaskInfo.setKeywordId(taskInfo.getKeywordId());
        trialTaskInfo.setAppIcon(taskInfo.getAppIcon());
        trialTaskInfo.setAppPackage(taskInfo.getAppPackage());
        trialTaskInfo.setKeyword(taskInfo.getKeyword());
        trialTaskInfo.setAppMarket(appMarket);
        trialTaskInfo.setMarketUrl(taskInfo.getMarketUrl());
        trialTaskInfo.setMarketPackage(taskInfo.getMarketPackage());

        String releaseTime = taskInfo.getReleaseTime();
        String day = releaseTime.substring(0, 10);
        String time = releaseTime.substring(11, 16);

        List<String> appLabel = new ArrayList<>();
        if(today.equals(day)) {
            appLabel.add("今日"+time);
        }else if(tomorrow.equals(day)) {
            appLabel.add("明日"+time);
        }
        appLabel.add(appMarket);
        appLabel.add("共" + taskInfo.getLeftNum() + "份");
        trialTaskInfo.setAppLabel(appLabel);

        trialTaskInfo.setStatus(2);
        trialTaskInfo.setDesc(""+taskInfo.getMoney());

        String detailUrl = Config.getRedBaseUrl() + "/c/p/a/task/timedDetail?keywordId=" + taskInfo.getKeywordId();
        trialTaskInfo.setDetailUrl(detailUrl);

        return trialTaskInfo;

    }

    /**
     * 设置完成的 TrialTaskInfo
     *
     * @param taskInfo
     * @return
     */
    private TrialTaskInfo finishInfo(TaskInfo taskInfo) {

        String appMarket = taskInfo.getAppMarket();

        TrialTaskInfo trialTaskInfo = new TrialTaskInfo();
        trialTaskInfo.setAppIcon(taskInfo.getAppIcon());
        trialTaskInfo.setKeyword(taskInfo.getKeyword());

        List<String> appLabel = new ArrayList<>();
        appLabel.add(appMarket);
        appLabel.add(taskInfo.getTaskLabel());
        trialTaskInfo.setAppLabel(appLabel);

        trialTaskInfo.setStatus(1);
        trialTaskInfo.setDesc("已完成");

        return trialTaskInfo;

    }


    /**
     * 抢任务
     *
     * @param userId
     * @param deviceId
     * @param keywordId
     * @return
     */
    public String getMission(int userId, String deviceId, Long keywordId){

        Map<String, Object> dataResult = new HashMap<>(2);

        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());
        ReAppKeywords word = reAppKeywordsDAO.selectLockByKeywordId(keywordId, nowTime);

        if(word == null) {
            dataResult.put("code", 0);
            dataResult.put("msg", "任务还没开始!");
        }else {
            int leftNum = word.getLeftNum();
            if (leftNum <= 0){
                dataResult.put("code", 0);
                dataResult.put("msg", "手慢了,没抢到任务!");
            }else {
                word.setLeftNum(leftNum - 1);
                reAppKeywordsDAO.updateByPrimaryKeySelective(word);

                // 添加一条reAppTask任务,状态是进行中
                ReAppTask task = new ReAppTask();
                task.setUserId(userId);
                task.setDeviceId(deviceId);
                task.setKeywordId(keywordId);
                task.setAppId(word.getAppId());
                task.setTaskStep(1);
                task.setTaskStatus(0);
                task.setCreateTime(System.currentTimeMillis());
                task.setUpdateTime(System.currentTimeMillis());
                reAppTaskDAO.insert(task);

                dataResult.put("code", 1);
                dataResult.put("msg", "任务领取成功!");
            }
        }

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 放弃任务:先抢新的任务,再放弃之前的任务,因为若先放弃,新任务被抢完了,这样用户就一个任务都没了
     * @param userId
     * @param deviceId
     * @param oldKeywordId  要放弃的任务
     * @param newKeyWordId  新抢任务
     * @return
     */
    public String  giveUpOldMissionToGetNewMission(Integer userId, String deviceId, Long oldKeywordId, Long newKeyWordId){

        Map<String, Object> dataResult = new HashMap<>(2);

        String getNewMission = getMission(userId, deviceId, newKeyWordId);

        Result result = FastJsonUtil.parseObject(getNewMission, Result.class);

        if (result.getData().getCode() == 1){//新的任务抢成功了,才能放弃之前的任务

            //先查此人进行中的任务
            ReAppTask ingTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);

            //把旧任务状态设为'放弃'
            ingTask.setTaskStatus(2);
            ingTask.setUpdateTime(System.currentTimeMillis());
            reAppTaskDAO.updateByPrimaryKeySelective(ingTask);

            //旧任务
            ReAppKeywords wordIng = reAppKeywordsDAO.selectLockByKeywordId(oldKeywordId, CommonMethod.fmtTime(System.currentTimeMillis()));
            Integer leftNum = wordIng.getLeftNum();
            wordIng.setLeftNum(leftNum+1);
            reAppKeywordsDAO.updateByPrimaryKeySelective(wordIng);

            dataResult.put("code",1);
            dataResult.put("msg","任务领取成功");

            return JsonUtil.buildSuccessDataJson(dataResult);

        }else {
            return  getNewMission;
        }

    }
}
