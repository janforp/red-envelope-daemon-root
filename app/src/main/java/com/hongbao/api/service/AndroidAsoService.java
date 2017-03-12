package com.hongbao.api.service;

import com.alibaba.fastjson.JSON;
import com.hongbao.api.config.Config;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.dao.ReAppKeywordsDAO;
import com.hongbao.api.dao.ReAppTaskDAO;
import com.hongbao.api.model.ReAppTask;
import com.hongbao.api.model.dto.TaskInfo;
import com.hongbao.api.model.dto.TrialInfo;
import com.hongbao.api.model.dto.TrialModel;
import com.hongbao.api.model.vo.TrialVo;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2017/1/11.
 */
@Service
public class AndroidAsoService {

    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReAppKeywordsDAO reAppKeywordsDAO;


    /**
     * 试玩任务列表 (andriod aso任务)
     *
     * @param request
     * @param manufacturer
     * @param os
     * @param app
     * @return
     */
    public List<TrialModel> selectTrialList(HttpServletRequest request, String manufacturer, String os, String app) {

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
                // 查询正在进行中的 aso任务
                ReAppTask reAppTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);
                if(reAppTask != null) { // 有进行中的
                    TrialInfo trialInfo = reAppKeywordsDAO.selectTrialByKeywordId(reAppTask.getKeywordId());
                    TrialVo trialVo = underwayVo(trialInfo, 0);
                    underwayData.add(trialVo);
                    packageList.add(trialInfo.getAppPackage());
                }
            }

            // 查出所有的可以做的任务(1.已经发放且还有剩余, 2.该用户手机上有的app排除, 3.一个app只查出一条word)
            List<TrialInfo> trialList = reAppKeywordsDAO.selectAllByTime(nowTime, userId);
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

}
