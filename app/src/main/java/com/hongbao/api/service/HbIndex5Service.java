package com.hongbao.api.service;

import com.alibaba.fastjson.JSON;
import com.hongbao.api.config.Config;
import com.hongbao.api.model.*;
import com.hongbao.api.model.dto.Index5MissionInfo;
import com.hongbao.api.model.vo.IndexMissionVo;
import com.hongbao.api.service.redis.RedisAndroidService;
import com.hongbao.api.service.redis.RedisIosService;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2017/1/5.
 */
@Service
public class HbIndex5Service {

    @Autowired
    private RedisAndroidService redisAndroidService;
    @Autowired
    private RedisIosService redisIosService;


    /**
     * 专属任务
     *
     * @param userId
     * @param platform
     * @param flag
     * @return
     */
    public IndexMissionVo selectExclusive(Integer userId, int platform, boolean flag) {

        IndexMissionVo vo = new IndexMissionVo();

        if(flag && userId != null) {

            long now = System.currentTimeMillis();
            String dayTime = CommonMethod.fmtDay(now);

            List<Index5MissionInfo> data = new ArrayList<>();

            if(platform == 0) { // ios

                List<Long> missionIdList = redisIosService.selectFinishMissionList(userId);

                if(missionIdList != null) {

                    for (Long missionId : missionIdList) {
                        // 用户完成附属任务情况
                        ReIosMissionFinish reIosMissionFinish = redisIosService.selectUserFinishInfo(missionId, userId);
                        // 步骤id
                        int stepId = reIosMissionFinish.getStepId();
                        // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
                        int missionStatus = reIosMissionFinish.getMissionStatus();
                        if(missionStatus == 1) { // 已通过
                            // 任务详情
                            ReIosMission mission = redisIosService.selectIosMission(missionId);
                            // 总步数
                            int stepNum = mission.getStepNum();
                            if(stepNum > stepId) { // 查看下一步数据
                                ReIosMissionStep missionStep = redisIosService.selectMissionStep(missionId, stepId + 1);
                                int leftNum = missionStep.getLeftNum();
                                if(leftNum > 0) { // 可做
                                    Index5MissionInfo info = new Index5MissionInfo();
                                    info.setIcon(mission.getAppIcon());
                                    info.setTitle(mission.getAppName());
                                    info.setUrl("hongbao://FRHighDetailViewController?missionId="+mission.getMissionId()+"&flag=0");
                                    info.setAward("+" + missionStep.getMissionMoney() + "元");
                                    info.setDesc(missionStep.getMissionLabel());
                                    info.setStatus(0);
                                    data.add(info);
                                }
                            }

                        }else if(missionStatus == 2) { // 未通过

                        }else {  // 审核中 重新提交
                            // 任务详情
                            ReIosMission mission = redisIosService.selectIosMission(missionId);
                            // 查询某一步详情
                            ReIosMissionStep missionStep = redisIosService.selectMissionStep(missionId, stepId);

                            Index5MissionInfo info = new Index5MissionInfo();
                            info.setIcon(mission.getAppIcon());
                            info.setTitle(mission.getAppName());
                            info.setUrl("hongbao://FRHighDetailViewController?missionId="+mission.getMissionId()+"&flag=0");
                            info.setAward("+" + missionStep.getMissionMoney() + "元");
                            info.setDesc(missionStep.getMissionLabel());
                            info.setStatus(0);
                            data.add(info);
                        }
                    }

                }

            }else { // android

                List<Index5MissionInfo> normalList = new ArrayList<>();
                List<Index5MissionInfo> other = new ArrayList<>();

                List<Long> exclusiveId = redisAndroidService.selectExclusiveId(userId, dayTime);

                if(exclusiveId != null) {

                    for (Long missionId : exclusiveId) {

                        // 任务详情
                        ReAndroidMission mission = redisAndroidService.selectAndroidMission(missionId);

                        Index5MissionInfo info = new Index5MissionInfo();
                        info.setIcon(mission.getAppIcon());
                        info.setTitle(mission.getAppName());
                        info.setUrl("hongbao://JifenDetailActivity?missionId="+mission.getMissionId()+"&flag=0");

                        // 附属任务个数
                        int auxiliaryNum = mission.getAuxiliaryNum();

                        // 留存天数
                        int keepDay = mission.getKeepDay();
                        ReAndroidMissionFinish finish = redisAndroidService.selectUserFinishInfo(missionId, userId);
                        int missionNo = finish.getMissionNo();

                        String startDay = finish.getStartDay();
                        long startDayTime = CommonMethod.get13Timestamp(startDay + " 00:00:00");

                        // 激活后的第几天
                        int day = (int) ((now - startDayTime) / (24 * 60 * 60 * 1000));

                        if(missionNo == day) { // 已做今日留存

                            if(keepDay == missionNo) { // 已做完最后的留存

                                // 判断附属任务 如果有审核中或者重新提交的,那就继续显示
                                if(auxiliaryNum > 0) {
                                    // 用户完成附属任务情况
                                    ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                    if(aux != null) {
                                        int auxNo = aux.getMissionNo();
                                        int auxStatus = aux.getMissionStatus();
                                        if(auxStatus == 0 || auxStatus == 3) {
                                            ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, auxNo);
                                            info.setDesc(auxMission.getMissionLabel());
                                            info.setAward("+" + auxMission.getMissionMoney() +"元");
                                            info.setStatus(0);
                                            normalList.add(info);
                                        }
                                    }
                                }

                            }else { // 明天还有留存任务

                                if(auxiliaryNum > 0) { // 有附属任务

                                    // 用户完成附属任务情况
                                    ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                    if(aux == null) { // 未做过
                                        ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, 1);
                                        if(day == 0) { // 激活当天
                                            // 0-激活当日可做 1-激活次日可做
                                            int openType = mission.getAuxiliaryTime();
                                            if(openType == 0) { // 当日可做
                                                int leftNum = auxMission.getLeftNum();
                                                if(leftNum > 0) { // 还有剩余
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(0);
                                                    normalList.add(info);
                                                }else { // 已抢光
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(2);
                                                    other.add(info);
                                                }
                                            }
                                        }else {
                                            int leftNum = auxMission.getLeftNum();
                                            if(leftNum > 0) { // 还有剩余
                                                info.setDesc(auxMission.getMissionLabel());
                                                info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                info.setStatus(0);
                                                normalList.add(info);
                                            }else { // 已抢光
                                                info.setDesc(auxMission.getMissionLabel());
                                                info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                info.setStatus(2);
                                                other.add(info);
                                            }
                                        }

                                    }else { // 已做过

                                        int auxNo = aux.getMissionNo();
                                        int auxStatus = aux.getMissionStatus();
                                        if(auxStatus == 1) { // 已通过
                                            if(auxiliaryNum > auxNo) { // 还有附属任务
                                                int nextAuxNo = auxNo + 1;
                                                ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, nextAuxNo);
                                                int leftNum = auxMission.getLeftNum();
                                                if(leftNum > 0) { // 还有剩余
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(0);
                                                    normalList.add(info);
                                                }else { // 已抢光
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(2);
                                                    other.add(info);
                                                }
                                            }
                                        }else {
                                            ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, auxNo);
                                            info.setDesc(auxMission.getMissionLabel());
                                            info.setAward("+" + auxMission.getMissionMoney() +"元");
                                            info.setStatus(0);
                                            normalList.add(info);
                                        }
                                    }
                                }
                            }
                        }else { // 未做今日留存

                            ReAndroidMissionDepthKeep keep = redisAndroidService.selectMissionDepthKeep(missionId, day, startDay);
                            ReAndroidMissionDepth depth = redisAndroidService.selectMissionDepth(missionId, day);
                            int planNum = keep.getPlanNum();
                            int realNum = keep.getRealNum();
                            BigDecimal depthMoney = depth.getDepthMoney();
                            String startTime = depth.getDepthStartTime();
                            String nowTime = CommonMethod.fmtHMS(now);

                            if(startTime.compareTo(nowTime) > 0) { // 未开始

                                info.setDesc("体验");
                                info.setAward("+" + depthMoney +"元");
                                info.setStatus(1);

                                if(auxiliaryNum > 0) { // 有附属任务
                                    // 用户完成附属任务情况
                                    ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                    if(aux == null) { // 未做过
                                        ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, 1);
                                        int leftNum = auxMission.getLeftNum();
                                        if(leftNum > 0) { // 还有剩余
                                            info.setDesc(auxMission.getMissionLabel());
                                            info.setAward("+" + auxMission.getMissionMoney() +"元");
                                            info.setStatus(0);
                                            normalList.add(info);
                                        }
                                    }else {
                                        int auxNo = aux.getMissionNo();
                                        int auxStatus = aux.getMissionStatus();
                                        if(auxStatus == 1) { // 已通过
                                            if(auxiliaryNum > auxNo) { // 还有附属任务
                                                int nextAuxNo = auxNo + 1;
                                                ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, nextAuxNo);
                                                int leftNum = auxMission.getLeftNum();
                                                if(leftNum > 0) { // 还有剩余
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(0);
                                                    normalList.add(info);
                                                }
                                            }
                                        }else {
                                            ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, auxNo);
                                            info.setDesc(auxMission.getMissionLabel());
                                            info.setAward("+" + auxMission.getMissionMoney() +"元");
                                            info.setStatus(0);
                                            normalList.add(info);
                                        }
                                    }
                                }else {
                                    other.add(info);
                                }

                            }else { // 已开始

                                if(realNum >= planNum) { // 达到预期量

                                    if(day == keepDay) { // 最后一天

                                        // 判断附属任务 如果有审核中或者重新提交的,那就继续显示
                                        if(auxiliaryNum > 0) {
                                            // 用户完成附属任务情况
                                            ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                            if(aux != null) {
                                                int auxNo = aux.getMissionNo();
                                                int auxStatus = aux.getMissionStatus();
                                                if(auxStatus == 0 || auxStatus == 3) {
                                                    ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, auxNo);
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(0);
                                                    normalList.add(info);
                                                }
                                            }
                                        }

                                    }else {

                                        info.setDesc("体验");
                                        info.setAward("+" + depthMoney +"元");
                                        info.setStatus(2);

                                        if(auxiliaryNum > 0) { // 有附属任务
                                            // 用户完成附属任务情况
                                            ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                            if(aux == null) { // 未做过
                                                ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, 1);
                                                int leftNum = auxMission.getLeftNum();
                                                if(leftNum > 0) { // 还有剩余
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(0);
                                                    normalList.add(info);
                                                }else { // 已抢光
                                                    BigDecimal auxMoney = auxMission.getMissionMoney();
                                                    if(auxMoney.compareTo(depthMoney) > 0) {
                                                        info.setDesc(auxMission.getMissionLabel());
                                                        info.setAward("+" + auxMoney +"元");
                                                        info.setStatus(2);
                                                        other.add(info);
                                                    }
                                                }
                                            }else {
                                                int auxNo = aux.getMissionNo();
                                                int auxStatus = aux.getMissionStatus();
                                                if(auxStatus == 1) { // 已通过
                                                    if(auxiliaryNum > auxNo) { // 还有附属任务
                                                        int nextAuxNo = auxNo + 1;
                                                        ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, nextAuxNo);
                                                        int leftNum = auxMission.getLeftNum();
                                                        if(leftNum > 0) { // 还有剩余
                                                            info.setDesc(auxMission.getMissionLabel());
                                                            info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                            info.setStatus(0);
                                                            normalList.add(info);
                                                        }else { // 已抢光
                                                            BigDecimal auxMoney = auxMission.getMissionMoney();
                                                            if(auxMoney.compareTo(depthMoney) > 0) {
                                                                info.setDesc(auxMission.getMissionLabel());
                                                                info.setAward("+" + auxMoney +"元");
                                                                info.setStatus(2);
                                                                other.add(info);
                                                            }
                                                        }
                                                    }
                                                }else {
                                                    ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, auxNo);
                                                    info.setDesc(auxMission.getMissionLabel());
                                                    info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                    info.setStatus(0);
                                                    normalList.add(info);
                                                }
                                            }
                                        }else {
                                            other.add(info);
                                        }
                                    }

                                }else { // 可做

                                    info.setDesc("体验");
                                    info.setAward("+" + depthMoney +"元");
                                    info.setStatus(0);

                                    if(auxiliaryNum > 0) { // 有附属任务
                                        // 用户完成附属任务情况
                                        ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                        if(aux == null) { // 未做过
                                            ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, 1);
                                            int leftNum = auxMission.getLeftNum();
                                            if(leftNum > 0) { // 还有剩余
                                                info.setDesc(auxMission.getMissionLabel());
                                                info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                info.setStatus(0);
                                                normalList.add(info);
                                            }
                                        }else {
                                            int auxNo = aux.getMissionNo();
                                            int auxStatus = aux.getMissionStatus();
                                            if(auxStatus == 1) { // 已通过
                                                if(auxiliaryNum > auxNo) { // 还有附属任务
                                                    int nextAuxNo = auxNo + 1;
                                                    ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, nextAuxNo);
                                                    int leftNum = auxMission.getLeftNum();
                                                    if(leftNum > 0) { // 还有剩余
                                                        info.setDesc(auxMission.getMissionLabel());
                                                        info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                        info.setStatus(0);
                                                        normalList.add(info);
                                                    }
                                                }
                                            }else {
                                                ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, auxNo);
                                                info.setDesc(auxMission.getMissionLabel());
                                                info.setAward("+" + auxMission.getMissionMoney() +"元");
                                                info.setStatus(0);
                                                normalList.add(info);
                                            }
                                        }
                                    }else {
                                        normalList.add(info);
                                    }
                                }
                            }
                        }
                    }
                }

                if(normalList.size() > 0) {
                    data.addAll(normalList);
                }
                if(other.size() > 0) {
                    data.addAll(other);
                }

            }

            if(data.size() > 0) {
                vo.setTitle("专属任务");
                vo.setData(data);
            }

        }

        return vo;

    }

    /**
     * 热门任务
     *
     * @param userId        啃根为null，是否登录显示的数据不同
     * @param platform
     * @param flag
     * @param manufacturer
     * @param os
     * @param app
     * @param sim
     * @return
     */
    public IndexMissionVo selectHot(Integer userId, int platform, boolean flag, String manufacturer, String os,
                                    String app, Integer sim) {

        IndexMissionVo vo = new IndexMissionVo();

        if(flag) {

            long now = System.currentTimeMillis();
            String nowTime = CommonMethod.fmtTime(now);
            String nowHMS = nowTime.substring(11);

            List<Index5MissionInfo> data = new ArrayList<>();

            // 邀请任务
            Index5MissionInfo invite = new Index5MissionInfo();
            invite.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/invite.png");
            invite.setTitle("邀请好友");
            invite.setDesc("邀请好友来赚钱");
            invite.setAward("");
            invite.setStatus(0);
            invite.setUrl(Config.getRedBaseUrl() + "/c/p/invitation/invitationCommissionMenu");
            data.add(invite);

            if(platform == 0) { // ios

                List<ReIosMission> iosMissionList = redisIosService.selectIosMissionList();
                // ios任务
                if(userId == null) { // 未登录
                    for (ReIosMission mission : iosMissionList) {
                        Index5MissionInfo info = hotMissionInfo(mission);
                        data.add(info);
                    }
                }else { // 已登录
                    List<Long> finishMissionList = redisIosService.selectFinishMissionList(userId);
                    for (ReIosMission mission : iosMissionList) {
                        long missionId = mission.getMissionId();
                        if(!finishMissionList.contains(missionId)) {
                            ReIosMissionStep missionStep = redisIosService.selectMissionStep(missionId, 1);
                            int leftNum = missionStep.getLeftNum();
                            if(leftNum > 0) {
                                Index5MissionInfo info = hotMissionInfo(mission);
                                data.add(info);
                            }
                        }
                    }
                }

                // 分享任务
                Index5MissionInfo share = new Index5MissionInfo();
                share.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/share.png");
                share.setTitle("分享文章");
                share.setDesc("阅读分享都有奖");
                share.setAward("");
                share.setStatus(0);
                share.setUrl("hongbao://FRShareMissionViewController");
                data.add(share);

                // 关注任务
                Index5MissionInfo attention = new Index5MissionInfo();
                attention.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/attention.png");
                attention.setTitle("关注任务");
                attention.setDesc("关注公众号赚钱");
                attention.setAward("");
                attention.setStatus(0);
                attention.setUrl("hongbao://FRAttentionMissionViewController");
                data.add(attention);

                // 联盟任务
                Index5MissionInfo alliance = new Index5MissionInfo();
                alliance.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/alliance.png");
                alliance.setTitle("联盟任务");
                alliance.setDesc("任务多更新快");
                alliance.setAward("");
                alliance.setStatus(0);
                alliance.setUrl("hongbao://FRAllianceMissionViewController");
                data.add(alliance);

            }else { // android

                // 试玩任务
                Index5MissionInfo trial = new Index5MissionInfo();
                trial.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/trial.png");
                trial.setTitle("试玩任务");
                trial.setDesc("简单几步下载赚钱");
                trial.setAward("");
                trial.setStatus(0);
                trial.setUrl("hongbao://NewTrialsActivity");
                data.add(trial);

                // 用户的本地第三方app列表数据
                List<String> userAppList = JSON.parseArray(app, String.class);
                // 积分墙
                {
                    //所有当前可做的，先取出来，再去重
                    List<ReAndroidMission> androidIntegralWallList = redisAndroidService.selectAndroidIntegralWall();
                    if(userId == null) { // 未登录
                        for (ReAndroidMission mission : androidIntegralWallList) {
                            // 包名
                            String appPackage = mission.getAppPackage();
                            // 去重
                            if(!userAppList.contains(appPackage)) {
                                // 开始时间点 如09:00:00
                                String startTime = mission.getActivateStartTime();
                                // 结束时间点 如09:00:00
                                String endTime = mission.getActivateEndTime();
                                if(startTime.compareTo(nowHMS) <= 0 && endTime.compareTo(nowHMS) >= 0) { // 判断时间
                                    Index5MissionInfo info = hotMissionInfo(mission);
                                    data.add(info);
                                }
                            }
                        }
                    }else { // 已登录
                        List<Long> underWayIdList = redisAndroidService.selectUnderWayId(userId);
                        List<String> finishAppList = redisAndroidService.selectFinishAppPackageList(userId);
                        for (ReAndroidMission mission : androidIntegralWallList) {
                            long missionId = mission.getMissionId();
                            if(underWayIdList.contains(missionId)) { // 是否正在进行中
                                Index5MissionInfo info = hotMissionInfo(mission);
                                data.add(info);
                            }else {
                                // 包名
                                String appPackage = mission.getAppPackage();
                                // 去重
                                if(!userAppList.contains(appPackage) && !finishAppList.contains(appPackage)) {
                                    // 开始时间点 如09:00:00
                                    String startTime = mission.getActivateStartTime();
                                    // 结束时间点 如09:00:00
                                    String endTime = mission.getActivateEndTime();
                                    //当前时间不再激活时间范围的app不显示
                                    if(startTime.compareTo(nowHMS) <= 0 && endTime.compareTo(nowHMS) >= 0) { // 判断时间
                                        if(sim == 1) { // 有sim卡
                                            Index5MissionInfo info = hotMissionInfo(mission);
                                            data.add(info);
                                        }else {
                                            // 是否需要sim卡
                                            int simLimit = mission.getSimLimit();
                                            if(simLimit == 0) { // 不需要sim卡
                                                Index5MissionInfo info = hotMissionInfo(mission);
                                                data.add(info);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // 分享任务
                Index5MissionInfo share = new Index5MissionInfo();
                share.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/share.png");
                share.setTitle("分享文章");
                share.setDesc("阅读分享都有奖");
                share.setAward("");
                share.setStatus(0);
                share.setUrl("hongbao://ForwardingTaskActivity");
                data.add(share);

                // 关注任务
                Index5MissionInfo attention = new Index5MissionInfo();
                attention.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/attention.png");
                attention.setTitle("关注任务");
                attention.setDesc("关注公众号赚钱");
                attention.setAward("");
                attention.setStatus(0);
                attention.setUrl("hongbao://AttentionTaskActivity");
                data.add(attention);

                // 联盟任务
                Index5MissionInfo alliance = new Index5MissionInfo();
                alliance.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/alliance.png");
                alliance.setTitle("联盟任务");
                alliance.setDesc("任务多更新快");
                alliance.setAward("");
                alliance.setStatus(0);
                alliance.setUrl("hongbao://JifenWallActivity");
                data.add(alliance);


            }

            // 贷款中心
            Index5MissionInfo loan = new Index5MissionInfo();
            loan.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/loan.png");
            loan.setTitle("贷款中心");
            loan.setDesc("利息低额度高");
            loan.setAward("");
            loan.setStatus(0);
            loan.setUrl(Config.getRedBaseUrl()+"/c/p/loan/mall");
            data.add(loan);

            // 理财中心
            Index5MissionInfo financial = new Index5MissionInfo();
            financial.setIcon("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/financial.png");
            financial.setTitle("理财中心");
            financial.setDesc("让钱生钱更容易");
            financial.setAward("");
            financial.setStatus(0);
            financial.setUrl(Config.getRedBaseUrl()+"/c/p/financial/mall");
            data.add(financial);


            vo.setTitle("热门任务");
            vo.setData(data);

        }

        return vo;

    }

    /**
     * 设置android Index5MissionInfo
     *
     * @param mission
     * @return
     */
    public Index5MissionInfo hotMissionInfo(ReAndroidMission mission) {
        Index5MissionInfo info = new Index5MissionInfo();
        info.setIcon(mission.getAppIcon());
        info.setTitle(mission.getAppName());
        info.setDesc(mission.getAppLabel());
        info.setAward("+"+mission.getTotalMoney()+"元");
        info.setStatus(0);
        // flag标志 0-进行中 1-已结束
        info.setUrl("hongbao://JifenDetailActivity?missionId="+mission.getMissionId()+"&flag=0");
        return info;
    }

    /**
     * 设置ios Index5MissionInfo
     *
     * @param mission
     * @return
     */
    public Index5MissionInfo hotMissionInfo(ReIosMission mission) {
        Index5MissionInfo info = new Index5MissionInfo();
        info.setTitle(mission.getAppName());
        info.setIcon(mission.getAppIcon());
        info.setDesc(mission.getAppLabel());
        info.setAward("+"+mission.getTotalMoney()+"元");
        info.setStatus(0);
        info.setUrl("hongbao://FRHighDetailViewController?missionId="+mission.getMissionId()+"&flag=0");
        return info;
    }

}
