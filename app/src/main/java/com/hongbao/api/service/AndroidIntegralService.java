package com.hongbao.api.service;

import com.alibaba.fastjson.JSON;
import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.*;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.*;
import com.hongbao.api.model.dto.AndroidAuxiliaryInfo;
import com.hongbao.api.model.dto.AndroidDepthInfo;
import com.hongbao.api.model.dto.AndroidFinishInfo;
import com.hongbao.api.model.vo.AndroidFinishVo;
import com.hongbao.api.model.vo.AndroidIntegralVo;
import com.hongbao.api.service.redis.RedisAndroidService;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Summer on 2017/1/6.
 */
@Service
public class AndroidIntegralService {

    @Autowired
    private RedisAndroidService redisAndroidService;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReAndroidMissionDAO reAndroidMissionDAO;
    @Autowired
    private ReAndroidMissionFinishDAO reAndroidMissionFinishDAO;
    @Autowired
    private ReAndroidMissionFinishDetailDAO reAndroidMissionFinishDetailDAO;
    @Autowired
    private ReAndroidAuxiliaryMissionFinishDAO reAndroidAuxiliaryMissionFinishDAO;
    @Autowired
    private ReAndroidMissionInstallDAO reAndroidMissionInstallDAO;
    @Autowired
    private ReAndroidMissionDepthKeepDAO reAndroidMissionDepthKeepDAO;
    @Autowired
    private ReAndroidAuxiliaryMissionDAO reAndroidAuxiliaryMissionDAO;
    @Autowired
    private ReUserCommissionRecordDAO reUserCommissionRecordDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 积分墙详情(正常)
     *
     * @param userId
     * @param missionId
     * @return
     */
    public AndroidIntegralVo detail(Integer userId, long missionId) {

        // 激活任务详情
        ReAndroidMission reAndroidMission = redisAndroidService.selectAndroidMission(missionId);
        // 留存天数
        int keepDay = reAndroidMission.getKeepDay();
        // 附属任务个数
        int auxiliaryNum = reAndroidMission.getAuxiliaryNum();

        // 深度任务列表
        List<ReAndroidMissionDepth> depthList = null;
        if(keepDay > 0) {
            depthList = redisAndroidService.selectDepthList(missionId);
        }
        // 任务步骤
        List<AndroidDepthInfo> missionList = new ArrayList<>();

        // 附属任务列表
        List<ReAndroidAuxiliaryMission> androidAuxiliaryList = null;
        if(auxiliaryNum > 0) {
            androidAuxiliaryList = redisAndroidService.selectAuxiliaryList(missionId);
        }
        // 附属任务
        List<AndroidAuxiliaryInfo> auxiliaryList = new ArrayList<>();

        // 用户完成情况
        ReAndroidMissionFinish reAndroidMissionFinish = redisAndroidService.selectUserFinishInfo(missionId, userId);
        BigDecimal gainMoney = new BigDecimal("0.00");

        if(reAndroidMissionFinish == null) { // 还未激活
            // 判断是限量 0:否, 1:是
            int numLimit = reAndroidMission.getNumLimit();
            Integer leftNum = reAndroidMission.getLeftNum();
            if(numLimit == 0 || leftNum.intValue() > 0) { // 可做
                // 激活
                AndroidDepthInfo activate = new AndroidDepthInfo();
                activate.setDepthId(0);
                activate.setStepStatus(1);
                activate.setLabel("今日可做");
                activate.setTitle("首次安装使用");
                activate.setAward("+" + reAndroidMission.getActivateMoney() + "元");
                activate.setDesc(reAndroidMission.getActivateDesc());
                activate.setTime(reAndroidMission.getActivateTime());
                missionList.add(activate);
                // 明日可做
                if(keepDay > 0) {
                    ReAndroidMissionDepth depth = depthList.get(0);
                    AndroidDepthInfo tomorrow = tomorrow(depth);
                    missionList.add(tomorrow);
                }
                // 待解锁
                if(keepDay > 1 || auxiliaryNum > 0) {
                    AndroidDepthInfo wait = wait(keepDay, 1, depthList, auxiliaryNum, 0, androidAuxiliaryList);
                    missionList.add(wait);
                }
            }
        }else { // 已激活

            gainMoney = reAndroidMissionFinish.getTotalMoney();

            if(keepDay > 0) { // 有留存
                // 激活日期
                String startDay = reAndroidMissionFinish.getStartDay();
                long startDayTime = CommonMethod.get13Timestamp(startDay + " 00:00:00");
                long now = System.currentTimeMillis();
                // 激活后的第几天
                int day = (int) ((now - startDayTime) / (24 * 60 * 60 * 1000));
                if(day == 0) { // 激活当日
                    // 明日可做
                    ReAndroidMissionDepth depth = depthList.get(0);
                    AndroidDepthInfo tomorrow = tomorrow(depth);
                    missionList.add(tomorrow);
                    // 判断是否有附属任务
                    if(auxiliaryNum > 0) {
                        // 用户完成附属任务情况
                        ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                        if(aux == null) { // 未做过
                            // 0-激活当日可做 1-激活次日可做
                            int openType = reAndroidMission.getAuxiliaryTime();
                            if(openType == 0) { // 当日可做
                                ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, 1);
                                int leftNum = auxMission.getLeftNum();
                                if(leftNum > 0) { // 还有剩余
                                    // 今日可做
                                    AndroidAuxiliaryInfo auxInfo = today(auxMission);
                                    auxiliaryList.add(auxInfo);
                                    // 待解锁
                                    if(keepDay > 1 || auxiliaryNum > 1) {
                                        AndroidDepthInfo wait = wait(keepDay, 1, depthList, auxiliaryNum, 1, androidAuxiliaryList);
                                        missionList.add(wait);
                                    }
                                }else {
                                    // 待解锁
                                    if(keepDay > 1 || auxiliaryNum > 0) {
                                        AndroidDepthInfo wait = wait(keepDay, 1, depthList, auxiliaryNum, 0, androidAuxiliaryList);
                                        missionList.add(wait);
                                    }
                                }
                            }else { // 当日不可做
                                // 待解锁
                                if(keepDay > 1 || auxiliaryNum > 0) {
                                    AndroidDepthInfo wait = wait(keepDay, 1, depthList, auxiliaryNum, 0, androidAuxiliaryList);
                                    missionList.add(wait);
                                }
                            }
                        }else { // 已做过
                            // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
                            int missionStatus = aux.getMissionStatus();
                            // 附属任务编号
                            int missionNo = aux.getMissionNo();
                            if(missionNo == auxiliaryNum) { // 最后一个附属任务
                                ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                auxInfo.setMissionStatus(missionStatus);
                                if(missionStatus == 1) { // 已通过
                                    auxInfo.setMissionLabel("审核通过");
                                }else { // 其他状态
                                    if(missionStatus == 0) {
                                        auxInfo.setMissionLabel("等待审核");
                                    }else if(missionStatus == 3) {
                                        auxInfo.setMissionLabel("重新提交");
                                    }else if(missionStatus == 2) {
                                        auxInfo.setMissionLabel("审核未过");
                                    }
                                    auxInfo.setCheckText(aux.getCheckText());
                                    auxInfo.setMissionNo(auxMission.getMissionNo());
                                    String missionImg = auxMission.getMissionImg();
                                    if(!StringUtil.isEmpty(missionImg)) {
                                        List<String> img = JSON.parseArray(missionImg, String.class);
                                        auxInfo.setMissionImg(img);
                                    }
                                    auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                    auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                }
                                auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                auxiliaryList.add(auxInfo);
                                if(missionNo > 1) { // 有多个附属任务
                                    // 之前的附属任务都通过
                                    int num = missionNo - 2;
                                    for (int i = num; i >= 0; i--) {
                                        ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                        AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                        auxiliaryList.add(auxiliaryInfo);
                                    }
                                }
                                // 待解锁
                                if(keepDay > 1) {
                                    AndroidDepthInfo wait = wait(keepDay, 1, depthList, 0, 0, null);
                                    missionList.add(wait);
                                }
                            }else {  // 不是最后一个附属任务
                                if(missionStatus == 1) { // 已通过
                                    // 下一条附属任务
                                    ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, missionNo);
                                    int leftNum = auxMission.getLeftNum();
                                    int nextMissionNo = auxMission.getMissionNo();
                                    if(leftNum > 0) {
                                        // 今日可做
                                        AndroidAuxiliaryInfo auxInfo = today(auxMission);
                                        auxiliaryList.add(auxInfo);
                                        // 待解锁
                                        if(keepDay > 1 || auxiliaryNum > nextMissionNo) {
                                            AndroidDepthInfo wait = wait(keepDay, 1, depthList, auxiliaryNum, nextMissionNo, androidAuxiliaryList);
                                            missionList.add(wait);
                                        }
                                    }else {
                                        // 待解锁
                                        if(keepDay > 1 || auxiliaryNum > missionNo) {
                                            AndroidDepthInfo wait = wait(keepDay, 1, depthList, auxiliaryNum, missionNo, androidAuxiliaryList);
                                            missionList.add(wait);
                                        }
                                    }
                                    // 之前的附属任务都通过
                                    int num = missionNo - 1;
                                    for (int i = num; i >= 0; i--) {
                                        ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                        AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                        auxiliaryList.add(auxiliaryInfo);
                                    }
                                }else { // 其他状态
                                    ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                    AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                    auxInfo.setMissionStatus(missionStatus);
                                    if(missionStatus == 2) {
                                        auxInfo.setMissionLabel("审核未过");
                                    }else if(missionStatus == 0) {
                                        auxInfo.setMissionLabel("等待审核");
                                    }else if(missionStatus == 3) {
                                        auxInfo.setMissionLabel("重新提交");
                                    }
                                    auxInfo.setCheckText(aux.getCheckText());
                                    auxInfo.setMissionNo(auxMission.getMissionNo());
                                    auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                    auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                    auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                    auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                    String missionImg = auxMission.getMissionImg();
                                    if(!StringUtil.isEmpty(missionImg)) {
                                        List<String> img = JSON.parseArray(missionImg, String.class);
                                        auxInfo.setMissionImg(img);
                                    }
                                    auxiliaryList.add(auxInfo);
                                    if(missionNo > 1) { // 有多个附属任务
                                        // 之前的附属任务都通过
                                        int num = missionNo - 2;
                                        for (int i = num; i >= 0; i--) {
                                            ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                            AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                            auxiliaryList.add(auxiliaryInfo);
                                        }
                                    }
                                    // 待解锁
                                    if(keepDay > 1 || auxiliaryNum > missionNo) {
                                        AndroidDepthInfo wait = wait(keepDay, 1, depthList, auxiliaryNum, missionNo, androidAuxiliaryList);
                                        missionList.add(wait);
                                    }
                                }
                            }
                        }
                    }else { // 没有附属任务
                        if(keepDay > 1) { // 待解锁
                            AndroidDepthInfo wait = wait(keepDay, 1, depthList, 0, 0, null);
                            missionList.add(wait);
                        }
                    }
                }else { // 激活日之后
                    // 最后完成时的任务编号
                    int lastFinish = reAndroidMissionFinish.getMissionNo();
                    if(day == lastFinish) { // 当日已做留存任务
                        if(keepDay > day) { // 还有留存任务
                            // 明日可做
                            ReAndroidMissionDepth depth = depthList.get(day);
                            AndroidDepthInfo tomorrow = tomorrow(depth);
                            missionList.add(tomorrow);
                            // 后天对应的序号
                            int nextDay = day + 1;
                            // 判断是否有附属任务
                            if(auxiliaryNum > 0) {
                                // 用户完成附属任务情况
                                ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                if(aux == null) { // 未做过
                                    ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, 1);
                                    int leftNum = auxMission.getLeftNum();
                                    if(leftNum > 0) {
                                        // 今日可做
                                        AndroidAuxiliaryInfo auxInfo = today(auxMission);
                                        auxiliaryList.add(auxInfo);
                                        // 待解锁
                                        if(keepDay > nextDay || auxiliaryNum > 1) {
                                            AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, 1, androidAuxiliaryList);
                                            missionList.add(wait);
                                        }
                                    }else {
                                        // 待解锁
                                        if(keepDay > nextDay || auxiliaryNum > 0) {
                                            AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, 0, androidAuxiliaryList);
                                            missionList.add(wait);
                                        }
                                    }
                                }else { // 已做过
                                    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
                                    int missionStatus = aux.getMissionStatus();
                                    // 附属任务编号
                                    int missionNo = aux.getMissionNo();
                                    if(missionNo == auxiliaryNum) { // 最后一个附属任务
                                        ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                        AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                        auxInfo.setMissionStatus(missionStatus);
                                        if(missionStatus == 1) { // 已通过
                                            auxInfo.setMissionLabel("审核通过");
                                        }else { // 其他状态
                                            if(missionStatus == 0) {
                                                auxInfo.setMissionLabel("等待审核");
                                            }else if(missionStatus == 2) {
                                                auxInfo.setMissionLabel("审核未过");
                                            }else if(missionStatus == 3) {
                                                auxInfo.setMissionLabel("重新提交");
                                            }
                                            String missionImg = auxMission.getMissionImg();
                                            if(!StringUtil.isEmpty(missionImg)) {
                                                List<String> img = JSON.parseArray(missionImg, String.class);
                                                auxInfo.setMissionImg(img);
                                            }
                                            auxInfo.setCheckText(aux.getCheckText());
                                            auxInfo.setMissionNo(auxMission.getMissionNo());
                                            auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                            auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                        }
                                        auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                        auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                        auxiliaryList.add(auxInfo);
                                        if(missionNo > 1) { // 有多个附属任务
                                            // 之前的附属任务都通过
                                            int num = missionNo - 2;
                                            for (int i = num; i >= 0; i--) {
                                                ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                                AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                                auxiliaryList.add(auxiliaryInfo);
                                            }
                                        }
                                        if(keepDay > nextDay) { // 待解锁
                                            BigDecimal moreMoney = new BigDecimal("0.00");
                                            for (int i = nextDay; i < keepDay; i++) {
                                                moreMoney = moreMoney.add(depthList.get(i).getDepthMoney());
                                            }
                                            AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, 0, 0, null);
                                            missionList.add(wait);
                                        }
                                    }else {  // 不是最后一个附属任务
                                        if(missionStatus == 1) { // 已通过
                                            // 下一条附属任务
                                            ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, missionNo);
                                            int leftNum = auxMission.getLeftNum();
                                            int nextMissionNo = auxMission.getMissionNo();
                                            if(leftNum > 0) {
                                                // 今日可做
                                                AndroidAuxiliaryInfo auxInfo = today(auxMission);
                                                auxiliaryList.add(auxInfo);
                                                // 待解锁
                                                if(keepDay > nextDay || auxiliaryNum > nextMissionNo) {
                                                    AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, nextMissionNo, androidAuxiliaryList);
                                                    missionList.add(wait);
                                                }
                                            }else {
                                                // 待解锁
                                                if(keepDay > nextDay || auxiliaryNum > missionNo) {
                                                    AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, missionNo, androidAuxiliaryList);
                                                    missionList.add(wait);
                                                }
                                            }
                                            // 之前的附属任务都通过
                                            int num = missionNo - 1;
                                            for (int i = num; i >= 0; i--) {
                                                ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                                AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                                auxiliaryList.add(auxiliaryInfo);
                                            }
                                        }else { // 其他状态
                                            ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                            AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                            auxInfo.setMissionStatus(missionStatus);
                                            if(missionStatus == 0) {
                                                auxInfo.setMissionLabel("等待审核");
                                            }else if(missionStatus == 2) {
                                                auxInfo.setMissionLabel("审核未过");
                                            }else if(missionStatus == 3) {
                                                auxInfo.setMissionLabel("重新提交");
                                            }
                                            auxInfo.setCheckText(aux.getCheckText());
                                            auxInfo.setMissionNo(auxMission.getMissionNo());
                                            auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                            auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                            auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                            auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                            String missionImg = auxMission.getMissionImg();
                                            if(!StringUtil.isEmpty(missionImg)) {
                                                List<String> img = JSON.parseArray(missionImg, String.class);
                                                auxInfo.setMissionImg(img);
                                            }
                                            auxiliaryList.add(auxInfo);
                                            if(missionNo > 1) { // 有多个附属任务
                                                // 之前的附属任务都通过
                                                int num = missionNo - 2;
                                                for (int i = num; i >= 0; i--) {
                                                    ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                                    AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                                    auxiliaryList.add(auxiliaryInfo);
                                                }
                                            }
                                            // 待解锁
                                            if(keepDay > nextDay || auxiliaryNum > missionNo) {
                                                AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, missionNo, androidAuxiliaryList);
                                                missionList.add(wait);
                                            }
                                        }
                                    }
                                }
                            }else { // 没有附属任务
                                // 待解锁
                                if(keepDay > nextDay) {
                                    AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, 0, 0, null);
                                    missionList.add(wait);
                                }
                            }
                        }else { // 已完成最后一天留存
                            // 判断是否有附属任务
                            if(auxiliaryNum > 0) {
                                // 用户完成附属任务情况
                                ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                if(aux != null) { // 做过
                                    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
                                    int missionStatus = aux.getMissionStatus();
                                    // 附属任务编号
                                    int missionNo = aux.getMissionNo();
                                    if(missionStatus == 0 || missionStatus == 3) { // 展示
                                        ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                        AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                        auxInfo.setMissionStatus(missionStatus);
                                        if(missionStatus == 0) {
                                            auxInfo.setMissionLabel("等待审核");
                                        }else {
                                            auxInfo.setMissionLabel("重新提交");
                                        }
                                        auxInfo.setCheckText(aux.getCheckText());
                                        auxInfo.setMissionNo(auxMission.getMissionNo());
                                        auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                        auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                        auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                        auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                        String missionImg = auxMission.getMissionImg();
                                        if(!StringUtil.isEmpty(missionImg)) {
                                            List<String> img = JSON.parseArray(missionImg, String.class);
                                            auxInfo.setMissionImg(img);
                                        }
                                        auxiliaryList.add(auxInfo);
                                        // 之前的附属任务都通过
                                        int num = missionNo - 1;
                                        for (int i = num; i >= 0; i--) {
                                            ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                            AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                            auxiliaryList.add(auxiliaryInfo);
                                        }
                                    }
                                }
                            }
                        }
                    }else { // 未做
                        // 今天留存任务 数量
                        ReAndroidMissionDepth reAndroidMissionDepth = depthList.get(day-1);
                        String startTime = reAndroidMissionDepth.getDepthStartTime();
                        String nowTime = CommonMethod.fmtHMS(now);

                        if(startTime.compareTo(nowTime) > 0) { // 未开始
                            AndroidDepthInfo depthInfo = new AndroidDepthInfo();
                            depthInfo.setStepStatus(0);
                            depthInfo.setLabel("今日可做");
                            depthInfo.setTitle("打开体验");
                            depthInfo.setAward("+" + reAndroidMissionDepth.getDepthMoney() + "元");
                            depthInfo.setDesc("未到时间,今日"+startTime+"开抢");
                            missionList.add(depthInfo);
                        }else { // 开始
                            long depthMissionId = reAndroidMissionDepth.getMissionId();
                            ReAndroidMissionDepthKeep keep = redisAndroidService.selectMissionDepthKeep(depthMissionId, day, startDay);
                            int planNum = keep.getPlanNum();
                            int realNum = keep.getRealNum();
                            if(planNum <= realNum) { // 已抢光
                                AndroidDepthInfo depthInfo = new AndroidDepthInfo();
                                depthInfo.setStepStatus(0);
                                depthInfo.setLabel("已被抢光");
                                depthInfo.setTitle("打开体验");
                                depthInfo.setAward("+" + reAndroidMissionDepth.getDepthMoney() + "元");
                                depthInfo.setDesc("已抢光,明天早点来");
                                missionList.add(depthInfo);
                            }else { // 可做
                                AndroidDepthInfo depthInfo = new AndroidDepthInfo();
                                depthInfo.setDepthId(day);
                                depthInfo.setStepStatus(1);
                                depthInfo.setLabel("今日可做");
                                depthInfo.setTitle("打开体验");
                                depthInfo.setAward("+" + reAndroidMissionDepth.getDepthMoney() + "元");
                                depthInfo.setDesc(reAndroidMissionDepth.getDepthDesc());
                                depthInfo.setTime(reAndroidMissionDepth.getDepthTime());
                                missionList.add(depthInfo);
                            }
                        }

                        if(keepDay > day) { // 还有留存任务
                            // 明日可做
                            ReAndroidMissionDepth depth = depthList.get(day);
                            AndroidDepthInfo tomorrow = tomorrow(depth);
                            missionList.add(tomorrow);
                            // 后天对应的序号
                            int nextDay = day + 1;
                            // 判断是否有附属任务
                            if(auxiliaryNum > 0) {
                                // 用户完成附属任务情况
                                ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                if(aux == null) { // 未做过
                                    ReAndroidAuxiliaryMission auxMission = redisAndroidService.selectAuxMission(missionId, 1);
                                    int leftNum = auxMission.getLeftNum();
                                    if(leftNum > 0) {
                                        // 今日可做
                                        AndroidAuxiliaryInfo auxInfo = today(auxMission);
                                        auxiliaryList.add(auxInfo);
                                        // 待解锁
                                        if(keepDay > nextDay || auxiliaryNum > 1) {
                                            AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, 1, androidAuxiliaryList);
                                            missionList.add(wait);
                                        }
                                    }else {
                                        // 待解锁
                                        if(keepDay > nextDay || auxiliaryNum > 0) {
                                            AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, 0, androidAuxiliaryList);
                                            missionList.add(wait);
                                        }
                                    }
                                }else { // 已做过
                                    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
                                    int missionStatus = aux.getMissionStatus();
                                    // 附属任务编号
                                    int missionNo = aux.getMissionNo();
                                    if(missionNo == auxiliaryNum) { // 最后一个附属任务
                                        ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                        AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                        auxInfo.setMissionStatus(missionStatus);
                                        if(missionStatus == 1) { // 已通过
                                            auxInfo.setMissionLabel("审核通过");
                                        }else { // 其他状态
                                            if(missionStatus == 0) {
                                                auxInfo.setMissionLabel("等待审核");
                                            }else if(missionStatus == 2) {
                                                auxInfo.setMissionLabel("审核未过");
                                            }else if(missionStatus == 3) {
                                                auxInfo.setMissionLabel("重新提交");
                                            }
                                            auxInfo.setCheckText(aux.getCheckText());
                                            auxInfo.setMissionNo(auxMission.getMissionNo());
                                            auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                            auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                            String missionImg = auxMission.getMissionImg();
                                            if(!StringUtil.isEmpty(missionImg)) {
                                                List<String> img = JSON.parseArray(missionImg, String.class);
                                                auxInfo.setMissionImg(img);
                                            }
                                        }
                                        auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                        auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                        auxiliaryList.add(auxInfo);
                                        if(missionNo > 1) { // 有多个附属任务
                                            // 之前的附属任务都通过
                                            int num = missionNo - 2;
                                            for (int i = num; i >= 0; i--) {
                                                ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                                AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                                auxiliaryList.add(auxiliaryInfo);
                                            }
                                        }
                                        if(keepDay > nextDay) { // 待解锁
                                            AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, 0, 0, null);
                                            missionList.add(wait);
                                        }
                                    }else {  // 不是最后一个附属任务
                                        if(missionStatus == 1) { // 已通过
                                            // 显示下一条附属任务
                                            ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo);
                                            int leftNum = auxMission.getLeftNum();
                                            if(leftNum > 0) { // 有剩余
                                                int nextMissionNo = auxMission.getMissionNo();
                                                // 今日可做
                                                AndroidAuxiliaryInfo auxInfo = today(auxMission);
                                                auxiliaryList.add(auxInfo);
                                                // 待解锁
                                                if(keepDay > nextDay || auxiliaryNum > nextMissionNo) {
                                                    AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, nextMissionNo, androidAuxiliaryList);
                                                    missionList.add(wait);
                                                }
                                            }else {
                                                // 待解锁
                                                if(keepDay > nextDay || auxiliaryNum > missionNo) {
                                                    AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, missionNo, androidAuxiliaryList);
                                                    missionList.add(wait);
                                                }
                                            }
                                            // 之前的附属任务都通过
                                            int num = missionNo - 1;
                                            for (int i = num; i >= 0; i--) {
                                                ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                                AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                                auxiliaryList.add(auxiliaryInfo);
                                            }
                                        }else { // 其他状态
                                            ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                            AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                            auxInfo.setMissionStatus(missionStatus);
                                            if(missionStatus == 0) {
                                                auxInfo.setMissionLabel("等待审核");
                                            }else if(missionStatus == 2) {
                                                auxInfo.setMissionLabel("审核未过");
                                            }else if(missionStatus == 3) {
                                                auxInfo.setMissionLabel("重新提交");
                                            }
                                            String missionImg = auxMission.getMissionImg();
                                            if(!StringUtil.isEmpty(missionImg)) {
                                                List<String> img = JSON.parseArray(missionImg, String.class);
                                                auxInfo.setMissionImg(img);
                                            }
                                            auxInfo.setCheckText(aux.getCheckText());
                                            auxInfo.setMissionNo(auxMission.getMissionNo());
                                            auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                            auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                            auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                            auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                            auxiliaryList.add(auxInfo);
                                            if(missionNo > 1) { // 有多个附属任务
                                                // 之前的附属任务都通过
                                                int num = missionNo - 2;
                                                for (int i = num; i >= 0; i--) {
                                                    ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                                    AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                                    auxiliaryList.add(auxiliaryInfo);
                                                }
                                            }
                                            // 待解锁
                                            if(keepDay > nextDay || auxiliaryNum > missionNo) {
                                                AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, auxiliaryNum, missionNo, androidAuxiliaryList);
                                                missionList.add(wait);
                                            }
                                        }
                                    }
                                }
                            }else { // 没有附属任务
                                // 待解锁
                                if(keepDay > nextDay) {
                                    AndroidDepthInfo wait = wait(keepDay, nextDay, depthList, 0, 0, null);
                                    missionList.add(wait);
                                }
                            }
                        }else { // 已完成最后一天留存
                            // 判断是否有附属任务
                            if(auxiliaryNum > 0) {
                                // 用户完成附属任务情况
                                ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
                                if(aux != null) { // 做过
                                    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
                                    int missionStatus = aux.getMissionStatus();
                                    // 附属任务编号
                                    int missionNo = aux.getMissionNo();
                                    if(missionStatus == 0 || missionStatus == 3) { // 展示
                                        ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                                        AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                                        auxInfo.setMissionStatus(missionStatus);
                                        if(missionStatus == 0) {
                                            auxInfo.setMissionLabel("等待审核");
                                        }else {
                                            auxInfo.setMissionLabel("重新提交");
                                        }
                                        auxInfo.setCheckText(aux.getCheckText());
                                        auxInfo.setMissionNo(auxMission.getMissionNo());
                                        auxInfo.setMissionDesc(auxMission.getMissionDesc());
                                        auxInfo.setCheckRequire(auxMission.getCheckRequire());
                                        auxInfo.setMissionTitle(auxMission.getMissionTitle());
                                        auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                                        String missionImg = auxMission.getMissionImg();
                                        if(!StringUtil.isEmpty(missionImg)) {
                                            List<String> img = JSON.parseArray(missionImg, String.class);
                                            auxInfo.setMissionImg(img);
                                        }
                                        auxiliaryList.add(auxInfo);
                                        // 之前的附属任务都通过
                                        int num = missionNo - 1;
                                        for (int i = num; i >= 0; i--) {
                                            ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                                            AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                                            auxiliaryList.add(auxiliaryInfo);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        AndroidIntegralVo vo = new AndroidIntegralVo();
        vo.setMissionId(reAndroidMission.getMissionId());
        vo.setAppIcon(reAndroidMission.getAppIcon());
        vo.setAppName(reAndroidMission.getAppName());
        vo.setAppUrl(reAndroidMission.getAppUrl());
        vo.setAppPackage(reAndroidMission.getAppPackage());
        vo.setTotalMoney(reAndroidMission.getTotalMoney() + "元");
        vo.setGainMoney(gainMoney + "元");
        vo.setAppSize("大小: " + reAndroidMission.getAppSize() + "M");
        String appImg = reAndroidMission.getAppImg();
        if(!StringUtil.isEmpty(appImg)) {
            List<String> img = JSON.parseArray(appImg, String.class);
            vo.setAppImg(img);
        }
        vo.setAppIntroduce(reAndroidMission.getAppIntroduce());
        vo.setMissionList(missionList);
        vo.setAuxiliaryList(auxiliaryList);

        return vo;

    }

    /**
     * 下载完成 (记录当前状态, 表示任务正在进行中)
     *
     * @param userId
     * @param missionId
     * @return
     */
    public String down(Integer userId, Long missionId) {
        ReAndroidMissionInstall install = reAndroidMissionInstallDAO.selectByPrimaryKey(missionId, userId);
        if(install == null) {
            // 激活任务详情
            ReAndroidMission reAndroidMission = redisAndroidService.selectAndroidMission(missionId);
            String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());

            install = new ReAndroidMissionInstall();
            install.setMissionId(missionId);
            install.setUserId(userId);
            install.setAppPackage(reAndroidMission.getAppPackage());
            install.setMissionStatus(0);
            install.setCreateTime(nowTime);
            install.setUpdateTime(nowTime);
            reAndroidMissionInstallDAO.insert(install);

            // 更新缓存
            List<Long> installList = reAndroidMissionInstallDAO.selectUnderWayId(userId);
            String key = CacheConsts.USER_UNDERWAY_WALL + userId;
            redisAndroidService.updateUnderWayId(key, installList);
        }
        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("code", 0);
        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 完成体验
     *
     * @param userId
     * @param missionId
     * @param depthId
     * @return
     */
    public String finish(Integer userId, Long missionId, int depthId, String appName) {

        String msg;

        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);
        String today = CommonMethod.fmtDay(now);
        BigDecimal missionMoney;

        if(depthId == 0) { // 激活

            ReAndroidMission mission = redisAndroidService.selectAndroidMission(missionId);

            missionMoney = mission.getActivateMoney();

            int keepDay = mission.getKeepDay();
            int totalDay = keepDay + 1;
            String startDay = CommonMethod.fmtDay(now);
            String endDay = CommonMethod.fmtDay(now + keepDay * 24 * 60 * 60 * 1000);

            // 完成激活
            ReAndroidMissionFinish reAndroidMissionFinish = new ReAndroidMissionFinish();
            reAndroidMissionFinish.setMissionId(missionId);
            reAndroidMissionFinish.setUserId(userId);
            reAndroidMissionFinish.setAppPackage(mission.getAppPackage());
            reAndroidMissionFinish.setStartDay(startDay);
            reAndroidMissionFinish.setEndDay(endDay);
            reAndroidMissionFinish.setTotalDay(totalDay);
            reAndroidMissionFinish.setMissionNo(0);
            reAndroidMissionFinish.setTotalMoney(missionMoney);
            reAndroidMissionFinish.setCreateTime(nowTime);
            reAndroidMissionFinish.setUpdateTime(nowTime);
            reAndroidMissionFinishDAO.insert(reAndroidMissionFinish);

            // 记录明细
            ReAndroidMissionFinishDetail reAndroidMissionFinishDetail = new ReAndroidMissionFinishDetail();
            reAndroidMissionFinishDetail.setMissionId(missionId);
            reAndroidMissionFinishDetail.setUserId(userId);
            reAndroidMissionFinishDetail.setStartDay(startDay);
            reAndroidMissionFinishDetail.setMissionNo(0);
            reAndroidMissionFinishDetail.setMissionMoney(missionMoney);
            reAndroidMissionFinishDetail.setCreateTime(nowTime);
            reAndroidMissionFinishDetail.setUpdateTime(nowTime);
            reAndroidMissionFinishDetailDAO.insertSelective(reAndroidMissionFinishDetail);


            // 更新缓存
            redisAndroidService.updateUserFinishInfo(CacheConsts.FINISH_INFO + missionId + "_" + userId, reAndroidMissionFinish);

            // 更新专属任务id缓存列表
            List<Long> list = reAndroidMissionFinishDAO.selectExclusiveId(userId, startDay);
            redisAndroidService.updateExclusiveId(CacheConsts.EXC_LIST + userId, list);

            // 更新缓存中获取用户已做包名的列表
            List<String> appPackageList = reAndroidMissionFinishDAO.selectFinishAppPackageList(userId);
            redisAndroidService.updateFinishAppPackageList(CacheConsts.USER_WALL + userId, appPackageList);


            // 是否限量, 0:否, 1:是
            int numLimit = mission.getNumLimit();
            if(numLimit == 1) { // 修改剩余数量
                int leftNum = reAndroidMissionDAO.selectLeftNumLock(missionId) - 1;
                reAndroidMissionDAO.updateLeftNum(leftNum, missionId);
                // 更新缓存
                mission.setLeftNum(leftNum);
                redisAndroidService.updateAndroidMission(CacheConsts.MISSION_INFO + missionId, mission);
            }

            // 更新账户
            ReUser reUser = reUserDAO.selectUserLockByUserId(userId);
            reUser.setUserMoney(reUser.getUserMoney().add(missionMoney));
            reUser.setTodayMoney(reUser.getTodayMoney().add(missionMoney));
            reUser.setUpdateTime(now);
            reUserDAO.updateSelective(reUser);

            // 账户明细
            ReAccountDetail reAccountDetail = new ReAccountDetail();
            reAccountDetail.setUserId(userId);
            reAccountDetail.setAppId(reUser.getAppId());
            reAccountDetail.setAccountMoney(missionMoney);
            reAccountDetail.setDetailType(1);
            reAccountDetail.setMissionType(MissionType.android_integral.val);
            reAccountDetail.setMissionSubtype(MissionSubtype.android_activity.val);
            reAccountDetail.setDetailContent("激活["+appName+"]");
            reAccountDetail.setDetailTime(nowTime);
            reAccountDetailDAO.insert(reAccountDetail);

            // 删除下载临时表数据
            reAndroidMissionInstallDAO.deleteByPrimaryKey(missionId, userId);
            // 更新缓存
            List<Long> installList = reAndroidMissionInstallDAO.selectUnderWayId(userId);
            redisAndroidService.updateUnderWayId(CacheConsts.USER_UNDERWAY_WALL + userId, installList);

            msg = "获得" + missionMoney + "元奖励!";

        }else { // 留存

            // 查询留存信息
            ReAndroidMissionDepth depth = redisAndroidService.selectMissionDepth(missionId, depthId);
            missionMoney = depth.getDepthMoney();

            // 用户完成情况
            ReAndroidMissionFinish missionInfo = redisAndroidService.selectUserFinishInfo(missionId, userId);
            String dataDay = missionInfo.getStartDay();

            // 留存指标
            ReAndroidMissionDepthKeep keep = redisAndroidService.selectMissionDepthKeep(missionId, depthId, dataDay);

            // 实际量
            int realNum = reAndroidMissionDepthKeepDAO.selectRealNumLock(missionId, depthId, dataDay) + 1;
            keep.setRealNum(realNum);
            reAndroidMissionDepthKeepDAO.updateByPrimaryKeySelective(keep);
            // 更新缓存
            redisAndroidService.updateKeepInfo(CacheConsts.KEEP_INFO + missionId + "_" + dataDay, keep);

            missionInfo.setMissionNo(depthId);
            missionInfo.setTotalMoney(missionInfo.getTotalMoney().add(missionMoney));
            missionInfo.setUpdateTime(nowTime);
            reAndroidMissionFinishDAO.updateByPrimaryKey(missionInfo);

            // 记录明细
            ReAndroidMissionFinishDetail reAndroidMissionFinishDetail = new ReAndroidMissionFinishDetail();
            reAndroidMissionFinishDetail.setMissionId(missionId);
            reAndroidMissionFinishDetail.setUserId(userId);
            reAndroidMissionFinishDetail.setStartDay(missionInfo.getStartDay());
            reAndroidMissionFinishDetail.setMissionNo(depthId);
            reAndroidMissionFinishDetail.setMissionMoney(missionMoney);
            reAndroidMissionFinishDetail.setCreateTime(nowTime);
            reAndroidMissionFinishDetail.setUpdateTime(nowTime);
            reAndroidMissionFinishDetailDAO.insertSelective(reAndroidMissionFinishDetail);

            // 更新缓存
            redisAndroidService.updateUserFinishInfo(CacheConsts.FINISH_INFO + missionId + "_" + userId, missionInfo);

            // 更新账户
            ReUser reUser = reUserDAO.selectUserLockByUserId(userId);
            reUser.setUserMoney(reUser.getUserMoney().add(missionMoney));
            reUser.setTodayMoney(reUser.getTodayMoney().add(missionMoney));
            reUser.setUpdateTime(now);
            reUserDAO.updateSelective(reUser);

            // 账户明细
            ReAccountDetail reAccountDetail = new ReAccountDetail();
            reAccountDetail.setUserId(userId);
            reAccountDetail.setAppId(reUser.getAppId());
            reAccountDetail.setAccountMoney(missionMoney);
            reAccountDetail.setDetailType(1);
            reAccountDetail.setMissionType(MissionType.android_integral.val);
            reAccountDetail.setMissionSubtype(MissionSubtype.android_keep.val);
            reAccountDetail.setDetailContent("体验["+appName+"]");
            reAccountDetail.setDetailTime(nowTime);
            reAccountDetailDAO.insert(reAccountDetail);

            msg = "获得" + missionMoney + "元奖励!";

        }

        // 删除缓存
        userCacheService.removeUser(userId);

        // 记录佣金
        {
            // 邀请人id
            Integer user_id = userCacheService.selectInvitationUser(userId);
            if(user_id != null) {
                ReUserCommissionRecord record = reUserCommissionRecordDAO.selectByPrimaryKey(today, user_id, userId);
                if(record == null) {
                    record = new ReUserCommissionRecord();
                    record.setDataDay(today);
                    record.setUserId(user_id);
                    record.setInvitedUserId(userId);
                    record.setTotalMoney(missionMoney);
                    record.setGainMoney(new BigDecimal("0.00"));
                    record.setCreateTime(nowTime);
                    record.setUpdateTime(nowTime);
                    reUserCommissionRecordDAO.insert(record);
                }else {
                    record.setTotalMoney(record.getTotalMoney().add(missionMoney));
                    record.setUpdateTime(nowTime);
                    reUserCommissionRecordDAO.updateByPrimaryKey(record);
                }
            }
        }

        return JsonUtil.buildSuccessMsgJson(msg);

    }

    /**
     * 提交附属任务
     *
     * @param userId
     * @param missionId
     * @param missionNo
     * @param requires
     * @param imgs
     * @return
     */
    public String submit(Integer userId, Long missionId, int missionNo, String requires, String imgs) {

        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);

        ReAndroidAuxiliaryMissionFinish finish = reAndroidAuxiliaryMissionFinishDAO.selectByPrimaryKey(missionId, missionNo, userId);
        if(finish != null) { // 重新提交
            finish.setMissionStatus(0);
            finish.setCommitText(requires);
            finish.setCommitImg(imgs);
            finish.setUpdateTime(nowTime);
            reAndroidAuxiliaryMissionFinishDAO.updateByPrimaryKey(finish);
        }else { // 新提交
            finish = new ReAndroidAuxiliaryMissionFinish();
            finish.setMissionId(missionId);
            finish.setMissionNo(missionNo);
            finish.setDataDay(CommonMethod.fmtDay(now));
            finish.setUserId(userId);
            finish.setMissionStatus(0);
            finish.setCommitText(requires);
            finish.setCommitImg(imgs);
            finish.setCreateTime(nowTime);
            finish.setUpdateTime(nowTime);
            reAndroidAuxiliaryMissionFinishDAO.insertSelective(finish);

            // 更新剩余数量
            ReAndroidAuxiliaryMission auxiliaryMission = reAndroidAuxiliaryMissionDAO.selectLock(missionId, missionNo);
            auxiliaryMission.setLeftNum(auxiliaryMission.getLeftNum() - 1);
            auxiliaryMission.setUpdateTime(nowTime);
            reAndroidAuxiliaryMissionDAO.updateByPrimaryKeySelective(auxiliaryMission);

            // 更新缓存
            redisAndroidService.updateAuxMissionInfo(CacheConsts.AUX_INFO + missionId + "_" + missionNo, auxiliaryMission);

        }

        // 更新缓存
        redisAndroidService.updateUserFinishAuxiliaryInfo(CacheConsts.FINISH_AUX_INFO + missionId + "_" + userId, finish);

        return JsonUtil.buildSuccessMsgJson("提交成功!");

    }

    /**
     * 设置附属任务 今日可做
     *
     * @param auxMission
     * @return
     */
    private AndroidAuxiliaryInfo today(ReAndroidAuxiliaryMission auxMission) {
        AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
        auxInfo.setMissionNo(auxMission.getMissionNo());
        auxInfo.setMissionStatus(4);
        auxInfo.setMissionLabel("今日可做");
        auxInfo.setMissionTitle(auxMission.getMissionTitle());
        auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
        auxInfo.setMissionDesc(auxMission.getMissionDesc());
        auxInfo.setCheckRequire(auxMission.getCheckRequire());
        String missionImg = auxMission.getMissionImg();
        if(!StringUtil.isEmpty(missionImg)) {
            List<String> img = JSON.parseArray(missionImg, String.class);
            auxInfo.setMissionImg(img);
        }
        return auxInfo;
    }

    /**
     * 设置附属任务 审核通过
     *
     * @param auxiliaryMission
     * @return
     */
    private AndroidAuxiliaryInfo pass(ReAndroidAuxiliaryMission auxiliaryMission) {
        AndroidAuxiliaryInfo auxiliaryInfo = new AndroidAuxiliaryInfo();
        auxiliaryInfo.setMissionStatus(1);
        auxiliaryInfo.setMissionLabel("审核通过");
        auxiliaryInfo.setMissionTitle(auxiliaryMission.getMissionTitle());
        auxiliaryInfo.setMissionMoney("+" + auxiliaryMission.getMissionMoney() + "元");
        return auxiliaryInfo;
    }

    /**
     * 设置明日可做信息
     *
     * @param depth
     * @return
     */
    private AndroidDepthInfo tomorrow(ReAndroidMissionDepth depth) {
        AndroidDepthInfo tomorrow = new AndroidDepthInfo();
        tomorrow.setStepStatus(0);
        tomorrow.setLabel("明日可做");
        tomorrow.setTitle("明天再次使用");
        tomorrow.setAward("+" + depth.getDepthMoney() + "元");
        tomorrow.setDesc("未到时间,明天" + depth.getDepthStartTime() + "开抢");
        return tomorrow;
    }

    /**
     * 设置待解锁信息
     *
     * @param keepDay 留存天数
     * @param keepNo 留存任务列表索引
     * @param depthList 留存任务列表
     * @param auxiliaryNum 附属任务个数
     * @param auxiliaryNo 附属任务列表索引
     * @param androidAuxiliaryList 附属任务列表
     * @return
     */
    private AndroidDepthInfo wait(int keepDay, int keepNo, List<ReAndroidMissionDepth> depthList, int auxiliaryNum,
                                  int auxiliaryNo, List<ReAndroidAuxiliaryMission> androidAuxiliaryList) {
        BigDecimal moreMoney = new BigDecimal("0.00");
        if(keepDay > keepNo) {
            for (int i = keepNo; i < keepDay; i++) {
                moreMoney = moreMoney.add(depthList.get(i).getDepthMoney());
            }
        }
        if(auxiliaryNum > auxiliaryNo) {
            for (int i = auxiliaryNo; i < auxiliaryNum; i++) {
                moreMoney = moreMoney.add(androidAuxiliaryList.get(i).getMissionMoney());
            }
        }
        AndroidDepthInfo info = new AndroidDepthInfo();
        info.setStepStatus(0);
        info.setLabel("等待解锁");
        info.setTitle("更多任务");
        info.setAward("+" + moreMoney + "元");
        return info;
    }

    /**
     * 积分墙任务完成列表
     *
     * @param userId
     * @param updateTime
     * @return
     */
    public List<AndroidFinishVo> selectMissionList(Integer userId, String updateTime) {

        if(StringUtil.isEmpty(updateTime)) {
            updateTime = null;
        }

        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        List<AndroidFinishVo> list = new ArrayList<>();

        List<AndroidFinishInfo> infoList = reAndroidMissionFinishDAO.selectByUserIdAndTime(userId, updateTime);
        for (AndroidFinishInfo info : infoList) {

            // flag标志 0-进行中 1-已结束
            int flag = 0;
            String endDay = info.getEndDay();
            if(today.compareTo(endDay) > 0) {
                flag = 1;
            }

            AndroidFinishVo vo = new AndroidFinishVo();
            vo.setMissionIcon(info.getAppIcon());
            vo.setMissionTitle(info.getAppName());
            vo.setTotalMoney(info.getTotalMoney()+"元");
            vo.setGainMoney(info.getGainMoney()+"元");
            vo.setUpdateTime(info.getUpdateTime());
            vo.setMissionUrl("hongbao://JifenDetailActivity?missionId="+info.getMissionId()+"&flag="+flag);
            list.add(vo);
        }

        return list;

    }


    /**
     * 积分墙详情(已结束) 只显示附属任务详情
     *
     * @param userId
     * @param missionId
     * @return
     */
    public AndroidIntegralVo endDetail(Integer userId, long missionId) {

        // 激活任务详情
        ReAndroidMission reAndroidMission = redisAndroidService.selectAndroidMission(missionId);
        // 用户完成情况
        ReAndroidMissionFinish reAndroidMissionFinish = redisAndroidService.selectUserFinishInfo(missionId, userId);
        // 附属任务个数
        int auxiliaryNum = reAndroidMission.getAuxiliaryNum();
        // 附属任务
        List<AndroidAuxiliaryInfo> auxiliaryList = new ArrayList<>();

        // 附属任务列表
        List<ReAndroidAuxiliaryMission> androidAuxiliaryList = null;
        if(auxiliaryNum > 0) {
            androidAuxiliaryList = redisAndroidService.selectAuxiliaryList(missionId);
            // 用户完成附属任务情况
            ReAndroidAuxiliaryMissionFinish aux = redisAndroidService.selectUserFinishAuxiliaryInfo(missionId, userId);
            if(aux != null) {
                // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
                int missionStatus = aux.getMissionStatus();
                // 附属任务编号
                int missionNo = aux.getMissionNo();
                ReAndroidAuxiliaryMission auxMission = androidAuxiliaryList.get(missionNo - 1);
                AndroidAuxiliaryInfo auxInfo = new AndroidAuxiliaryInfo();
                auxInfo.setMissionStatus(missionStatus);
                if (missionStatus == 1) { // 已通过
                    auxInfo.setMissionLabel("审核通过");
                } else { // 其他状态
                    if (missionStatus == 0) {
                        auxInfo.setMissionLabel("等待审核");
                    } else if (missionStatus == 2) {
                        auxInfo.setMissionLabel("审核未过");
                    } else if (missionStatus == 3) {
                        auxInfo.setMissionLabel("重新提交");
                    }
                    String missionImg = auxMission.getMissionImg();
                    if (!StringUtil.isEmpty(missionImg)) {
                        List<String> img = JSON.parseArray(missionImg, String.class);
                        auxInfo.setMissionImg(img);
                    }
                    auxInfo.setCheckText(aux.getCheckText());
                    auxInfo.setMissionNo(auxMission.getMissionNo());
                    auxInfo.setMissionDesc(auxMission.getMissionDesc());
                    auxInfo.setCheckRequire(auxMission.getCheckRequire());
                }
                auxInfo.setMissionTitle(auxMission.getMissionTitle());
                auxInfo.setMissionMoney("+" + auxMission.getMissionMoney() + "元");
                auxiliaryList.add(auxInfo);
                if (missionNo > 1) { // 有多个附属任务
                    // 之前的附属任务都通过
                    int num = missionNo - 2;
                    for (int i = num; i >= 0; i--) {
                        ReAndroidAuxiliaryMission auxiliaryMission = androidAuxiliaryList.get(i);
                        AndroidAuxiliaryInfo auxiliaryInfo = pass(auxiliaryMission);
                        auxiliaryList.add(auxiliaryInfo);
                    }
                }
            }
        }

        AndroidIntegralVo vo = new AndroidIntegralVo();
        vo.setMissionId(reAndroidMission.getMissionId());
        vo.setAppIcon(reAndroidMission.getAppIcon());
        vo.setAppName(reAndroidMission.getAppName());
        vo.setAppUrl(reAndroidMission.getAppUrl());
        vo.setAppPackage(reAndroidMission.getAppPackage());
        vo.setTotalMoney(reAndroidMission.getTotalMoney() + "元");
        vo.setGainMoney(reAndroidMissionFinish.getTotalMoney() + "元");
        vo.setAppSize("大小: " + reAndroidMission.getAppSize() + "M");
        String appImg = reAndroidMission.getAppImg();
        if(!StringUtil.isEmpty(appImg)) {
            List<String> img = JSON.parseArray(appImg, String.class);
            vo.setAppImg(img);
        }
        vo.setAppIntroduce(reAndroidMission.getAppIntroduce());
        vo.setMissionList(null);
        vo.setAuxiliaryList(auxiliaryList);

        return vo;
    }

}
