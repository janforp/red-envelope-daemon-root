package com.hongbao.api.service;

import com.alibaba.fastjson.JSON;
import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReIosMissionFinishDAO;
import com.hongbao.api.dao.ReIosMissionStepDAO;
import com.hongbao.api.dao.ReIosMissionUserSituationDAO;
import com.hongbao.api.model.*;
import com.hongbao.api.model.dto.IosMissionStep;
import com.hongbao.api.model.vo.IosFinishVo;
import com.hongbao.api.model.vo.IosMissionVo;
import com.hongbao.api.service.redis.RedisIosService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2017/1/10.
 */
@Service
public class IosMissionService {

    @Autowired
    private RedisIosService redisIosService;
    @Autowired
    private ReIosMissionStepDAO reIosMissionStepDAO;
    @Autowired
    private ReIosMissionFinishDAO reIosMissionFinishDAO;
    @Autowired
    private ReIosMissionUserSituationDAO reIosMissionUserSituationDAO;

    /**
     * ios任务详情
     *
     * @param userId
     * @param missionId
     * @return
     */
    public IosMissionVo detail(Integer userId, Long missionId) {

        List<IosMissionStep> stepList = new ArrayList<>();
        BigDecimal gainMoney = new BigDecimal("0.00");

        ReIosMission iosMission = redisIosService.selectIosMission(missionId);
        // 总步数
        int stepNum = iosMission.getStepNum();
        // 用户完成附属任务情况
        ReIosMissionFinish reIosMissionFinish = redisIosService.selectUserFinishInfo(missionId, userId);
        if(reIosMissionFinish == null) { // 未提交
            ReIosMissionStep reIosMissionStep = redisIosService.selectMissionStep(missionId, 1);
            int leftNum = reIosMissionStep.getLeftNum();
            if(leftNum > 0) { // 今日可做
                IosMissionStep iosMissionStep = new IosMissionStep();
                iosMissionStep.setStepId(1);
                iosMissionStep.setMissionTitle(reIosMissionStep.getMissionTitle());
                iosMissionStep.setMissionMoney("+"+reIosMissionStep.getMissionMoney()+"元");
                iosMissionStep.setMissionDesc(reIosMissionStep.getMissionDesc());
                iosMissionStep.setCheckRequire(reIosMissionStep.getCheckRequire());
                iosMissionStep.setIsBtn(reIosMissionStep.getIsBtn());
                iosMissionStep.setBtnTitle(reIosMissionStep.getBtnTitle());
                iosMissionStep.setBtnUrl(reIosMissionStep.getBtnUrl());
                iosMissionStep.setMissionStatus(4);
                String missionImg = reIosMissionStep.getMissionImg();
                if(!StringUtil.isEmpty(missionImg)) {
                    List<String> img = JSON.parseArray(missionImg, String.class);
                    iosMissionStep.setMissionImg(img);
                }
                stepList.add(iosMissionStep);
            }else { // 已抢完

            }
        }else { // 已提交过

            // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
            int missionStatus = reIosMissionFinish.getMissionStatus();
            // 步骤id
            int stepId = reIosMissionFinish.getStepId();
            // 步骤详情
            ReIosMissionStep reIosMissionStep = redisIosService.selectMissionStep(missionId, stepId);

            if (stepNum == stepId) { // 当前是最后一步

                IosMissionStep iosMissionStep = new IosMissionStep();
                iosMissionStep.setStepId(1);
                iosMissionStep.setMissionTitle(reIosMissionStep.getMissionTitle());
                iosMissionStep.setMissionMoney("+" + reIosMissionStep.getMissionMoney() + "元");
                iosMissionStep.setMissionDesc(reIosMissionStep.getMissionDesc());
                iosMissionStep.setCheckRequire(reIosMissionStep.getCheckRequire());
                iosMissionStep.setIsBtn(reIosMissionStep.getIsBtn());
                iosMissionStep.setBtnTitle(reIosMissionStep.getBtnTitle());
                iosMissionStep.setBtnUrl(reIosMissionStep.getBtnUrl());
                String missionImg = reIosMissionStep.getMissionImg();
                if (!StringUtil.isEmpty(missionImg)) {
                    List<String> img = JSON.parseArray(missionImg, String.class);
                    iosMissionStep.setMissionImg(img);
                }
                iosMissionStep.setMissionStatus(missionStatus);
                iosMissionStep.setCheckText(reIosMissionFinish.getCheckText());
                stepList.add(iosMissionStep);

                if (missionStatus == 1) { // 审核通过
                    gainMoney = gainMoney.add(reIosMissionStep.getMissionMoney());
                }

                if (stepId > 1) { // 以前的都通过
                    List<ReIosMissionStep> iosMissionStepList = redisIosService.selectIosMissionStepList(missionId);
                    int num = stepId - 2;
                    for (int i = num; i >= 0; i--) {
                        ReIosMissionStep missionStep = iosMissionStepList.get(i);
                        IosMissionStep step = new IosMissionStep();
                        step.setMissionTitle(missionStep.getMissionTitle());
                        step.setMissionMoney("+" + missionStep.getMissionMoney() + "元");
                        step.setMissionStatus(1);
                        stepList.add(step);
                        gainMoney = gainMoney.add(missionStep.getMissionMoney());
                    }

                }

            } else { // 不是最后一步

                if (missionStatus == 1) { // 已通过
                    // 下一步
                    int nextStepId = stepId + 1;
                    ReIosMissionStep missionStep = redisIosService.selectMissionStep(missionId, nextStepId);
                    int leftNum = missionStep.getLeftNum();
                    if (leftNum > 0) { // 今日可做
                        IosMissionStep iosMissionStep = new IosMissionStep();
                        iosMissionStep.setStepId(nextStepId);
                        iosMissionStep.setMissionTitle(missionStep.getMissionTitle());
                        iosMissionStep.setMissionMoney("+" + missionStep.getMissionMoney() + "元");
                        iosMissionStep.setMissionDesc(missionStep.getMissionDesc());
                        iosMissionStep.setCheckRequire(missionStep.getCheckRequire());
                        iosMissionStep.setIsBtn(missionStep.getIsBtn());
                        iosMissionStep.setBtnTitle(missionStep.getBtnTitle());
                        iosMissionStep.setBtnUrl(missionStep.getBtnUrl());
                        iosMissionStep.setMissionStatus(4);
                        String missionImg = missionStep.getMissionImg();
                        if (!StringUtil.isEmpty(missionImg)) {
                            List<String> img = JSON.parseArray(missionImg, String.class);
                            iosMissionStep.setMissionImg(img);
                        }
                        stepList.add(iosMissionStep);
                    }

                    // 这一步通过
                    IosMissionStep step = new IosMissionStep();
                    step.setMissionTitle(reIosMissionStep.getMissionTitle());
                    step.setMissionMoney("+" + reIosMissionStep.getMissionMoney() + "元");
                    step.setMissionStatus(1);
                    stepList.add(step);

                    gainMoney = gainMoney.add(reIosMissionStep.getMissionMoney());


                } else { // 其他状态
                    IosMissionStep iosMissionStep = new IosMissionStep();
                    iosMissionStep.setStepId(stepId);
                    iosMissionStep.setMissionTitle(reIosMissionStep.getMissionTitle());
                    iosMissionStep.setMissionMoney("+" + reIosMissionStep.getMissionMoney() + "元");
                    iosMissionStep.setMissionDesc(reIosMissionStep.getMissionDesc());
                    iosMissionStep.setCheckRequire(reIosMissionStep.getCheckRequire());
                    iosMissionStep.setIsBtn(reIosMissionStep.getIsBtn());
                    iosMissionStep.setBtnTitle(reIosMissionStep.getBtnTitle());
                    iosMissionStep.setBtnUrl(reIosMissionStep.getBtnUrl());
                    String missionImg = reIosMissionStep.getMissionImg();
                    if (!StringUtil.isEmpty(missionImg)) {
                        List<String> img = JSON.parseArray(missionImg, String.class);
                        iosMissionStep.setMissionImg(img);
                    }
                    iosMissionStep.setMissionStatus(missionStatus);
                    iosMissionStep.setCheckText(reIosMissionFinish.getCheckText());
                    stepList.add(iosMissionStep);
                }

                if (stepId > 1) { // 以前的都通过
                    List<ReIosMissionStep> iosMissionStepList = redisIosService.selectIosMissionStepList(missionId);
                    int num = stepId - 2;
                    for (int i = num; i >= 0; i--) {
                        ReIosMissionStep missionStep = iosMissionStepList.get(i);
                        IosMissionStep step = new IosMissionStep();
                        step.setMissionTitle(missionStep.getMissionTitle());
                        step.setMissionMoney("+" + missionStep.getMissionMoney() + "元");
                        step.setMissionStatus(1);
                        stepList.add(step);
                        gainMoney = gainMoney.add(missionStep.getMissionMoney());
                    }

                }

            }
        }

        IosMissionVo vo = new IosMissionVo();
        vo.setMissionId(missionId);
        vo.setAppIcon(iosMission.getAppIcon());
        vo.setAppName(iosMission.getAppName());
        vo.setAppSize("大小: " + iosMission.getAppSize() + "M");
        vo.setTotalMoney(iosMission.getTotalMoney() + "元");
        vo.setGainMoney(gainMoney + "元");
        String appImg = iosMission.getAppImg();
        if(!StringUtil.isEmpty(appImg)) {
            List<String> img = JSON.parseArray(appImg, String.class);
            vo.setAppImg(img);
        }
        vo.setAppIntroduce(iosMission.getAppIntroduce());
        vo.setStepList(stepList);

        return vo;

    }


    /**
     * 提交附属任务
     *
     * @param userId
     * @param missionId
     * @param stepId
     * @param requires
     * @param imgs
     * @return
     */
    public String submit(Integer userId, Long missionId, int stepId, String requires, String imgs) {

        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());

        ReIosMission reIosMission = redisIosService.selectIosMission(missionId);
        ReIosMissionFinish finish = reIosMissionFinishDAO.selectByPrimaryKey(missionId, stepId, userId);

        if(finish != null) { // 重新提交
            finish.setCommitText(requires);
            finish.setMissionStatus(0);
            finish.setCommitImg(imgs);
            finish.setUpdateTime(nowTime);
            reIosMissionFinishDAO.updateByPrimaryKey(finish);
        }else { // 新提交
            finish = new ReIosMissionFinish();
            finish.setMissionId(missionId);
            finish.setStepId(stepId);
            finish.setUserId(userId);
            finish.setMissionStatus(0);
            finish.setCommitText(requires);
            finish.setCommitImg(imgs);
            finish.setCreateTime(nowTime);
            finish.setUpdateTime(nowTime);
            reIosMissionFinishDAO.insertSelective(finish);

            // 更新剩余数量
            ReIosMissionStep step = reIosMissionStepDAO.selectLock(missionId, stepId);
            step.setLeftNum(step.getLeftNum() - 1);
            step.setUpdateTime(nowTime);
            reIosMissionStepDAO.updateByPrimaryKeySelective(step);

            // 更新缓存
            redisIosService.updateMissionStep(CacheConsts.STEP_INFO + missionId + "_" + stepId, step);
        }

        ReIosMissionUserSituation situation = reIosMissionUserSituationDAO.selectByPrimaryKey(missionId, userId);
        if(situation == null) { // 新增记录
            situation = new ReIosMissionUserSituation();
            situation.setMissionId(missionId);
            situation.setUserId(userId);
            situation.setAppName(reIosMission.getAppName());
            situation.setAppIcon(reIosMission.getAppIcon());
            situation.setStepId(stepId);
            situation.setMissionStatus(0);
            situation.setTotalMoney(reIosMission.getTotalMoney());
            situation.setGainMoney(new BigDecimal("0.00"));
            situation.setEndTime(reIosMission.getEndTime());
            situation.setCreateTime(nowTime);
            situation.setUpdateTime(nowTime);
            reIosMissionUserSituationDAO.insert(situation);
        }else { // 修改记录
            situation.setStepId(stepId);
            situation.setMissionStatus(0);
            situation.setTotalMoney(reIosMission.getTotalMoney());
            situation.setUpdateTime(nowTime);
            reIosMissionUserSituationDAO.updateByPrimaryKey(situation);
        }

        // 更新缓存
        List<Long> finishMissionList = reIosMissionFinishDAO.selectFinishMissionList(userId);
        redisIosService.updateFinishMissionList(CacheConsts.USER_IOS + userId, finishMissionList);

        // 更新缓存
        redisIosService.updateUserFinishInfo(CacheConsts.FINISH_IOS_INFO + missionId + "_" + userId, finish);

        return JsonUtil.buildSuccessMsgJson("提交成功!");

    }


    /**
     * ios任务完成列表
     *
     * @param userId
     * @param updateTime
     * @return
     */
    public List<IosFinishVo> selectMissionList(Integer userId, String updateTime) {

        if(StringUtil.isEmpty(updateTime)) {
            updateTime = null;
        }

        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());

        List<IosFinishVo> list = new ArrayList<>();

        List<ReIosMissionUserSituation> situationList = reIosMissionUserSituationDAO.selectByUserIdAndTime(userId, updateTime);
        for (ReIosMissionUserSituation situation : situationList) {

            // flag标志 0-进行中 1-已结束
            int flag = 0;
            String endTime = situation.getEndTime();
            if(nowTime.compareTo(endTime) > 0) {
                flag = 1;
            }

            IosFinishVo vo = new IosFinishVo();
            vo.setMissionIcon(situation.getAppIcon());
            vo.setMissionTitle(situation.getAppName());
            vo.setTotalMoney(situation.getTotalMoney()+"元");
            vo.setGainMoney(situation.getGainMoney()+"元");
            vo.setUpdateTime(situation.getUpdateTime());
            vo.setMissionUrl("hongbao://FRHighDetailViewController?missionId="+situation.getMissionId()+"&flag="+flag);
            list.add(vo);

        }

        return list;

    }


    /**
     * ios任务详情
     *
     * @param userId
     * @param missionId
     * @return
     */
    public IosMissionVo endDetail(Integer userId, Long missionId) {

        List<IosMissionStep> stepList = new ArrayList<>();
        BigDecimal gainMoney = new BigDecimal("0.00");

        ReIosMission iosMission = redisIosService.selectIosMission(missionId);
        // 用户完成附属任务情况
        ReIosMissionFinish reIosMissionFinish = redisIosService.selectUserFinishInfo(missionId, userId);

        // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
        int missionStatus = reIosMissionFinish.getMissionStatus();
        // 步骤id
        int stepId = reIosMissionFinish.getStepId();
        // 步骤详情
        ReIosMissionStep reIosMissionStep = redisIosService.selectMissionStep(missionId, stepId);

        IosMissionStep iosMissionStep = new IosMissionStep();
        iosMissionStep.setStepId(stepId);
        iosMissionStep.setMissionTitle(reIosMissionStep.getMissionTitle());
        iosMissionStep.setMissionMoney("+" + reIosMissionStep.getMissionMoney() + "元");
        iosMissionStep.setMissionDesc(reIosMissionStep.getMissionDesc());
        iosMissionStep.setCheckRequire(reIosMissionStep.getCheckRequire());
        iosMissionStep.setIsBtn(reIosMissionStep.getIsBtn());
        iosMissionStep.setBtnTitle(reIosMissionStep.getBtnTitle());
        iosMissionStep.setBtnUrl(reIosMissionStep.getBtnUrl());
        String missionImg = reIosMissionStep.getMissionImg();
        if (!StringUtil.isEmpty(missionImg)) {
            List<String> img = JSON.parseArray(missionImg, String.class);
            iosMissionStep.setMissionImg(img);
        }
        iosMissionStep.setMissionStatus(missionStatus);
        iosMissionStep.setCheckText(reIosMissionFinish.getCheckText());
        stepList.add(iosMissionStep);

        if (missionStatus == 1) { // 审核通过
            gainMoney = gainMoney.add(reIosMissionStep.getMissionMoney());
        }

        if (stepId > 1) { // 以前的都通过
            List<ReIosMissionStep> iosMissionStepList = redisIosService.selectIosMissionStepList(missionId);
            int num = stepId - 2;
            for (int i = num; i >= 0; i--) {
                ReIosMissionStep missionStep = iosMissionStepList.get(i);
                gainMoney = gainMoney.add(missionStep.getMissionMoney());
                IosMissionStep step = new IosMissionStep();
                step.setMissionTitle(missionStep.getMissionTitle());
                step.setMissionMoney("+" + missionStep.getMissionMoney() + "元");
                step.setMissionStatus(1);
                stepList.add(step);
            }

        }

        IosMissionVo vo = new IosMissionVo();
        vo.setMissionId(missionId);
        vo.setAppIcon(iosMission.getAppIcon());
        vo.setAppName(iosMission.getAppName());
        vo.setAppSize("大小: " + iosMission.getAppSize() + "M");
        vo.setTotalMoney(iosMission.getTotalMoney() + "元");
        vo.setGainMoney(gainMoney + "元");
        String appImg = iosMission.getAppImg();
        if(!StringUtil.isEmpty(appImg)) {
            List<String> img = JSON.parseArray(appImg, String.class);
            vo.setAppImg(img);
        }
        vo.setAppIntroduce(iosMission.getAppIntroduce());
        vo.setStepList(stepList);

        return vo;

    }

}
