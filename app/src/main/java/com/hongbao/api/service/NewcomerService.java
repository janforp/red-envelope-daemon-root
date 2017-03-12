package com.hongbao.api.service;

import com.hongbao.api.dao.*;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.enums.NewcomerType;
import com.hongbao.api.model.*;
import com.hongbao.api.model.dto.NewcomerInfo;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 2016/11/16.
 */
@Service
public class NewcomerService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReNewcomerMissionDAO reNewcomerMissionDAO;
    @Autowired
    private ReNewcomerMissionDetailDAO reNewcomerMissionDetailDAO;
    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReCodeExchangeDetailDAO reCodeExchangeDetailDAO;
    @Autowired
    private ReShareMissionDetailDAO reShareMissionDetailDAO;
    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;
    @Autowired
    private ReArticleClickDetailDAO reArticleClickDetailDAO;
    @Autowired
    private ReAndriodUserTaskDAO reAndriodUserTaskDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 查询新手任务列表
     *
     * @param platform
     * @param userId
     * @return
     */
    public List<NewcomerInfo> selectMissionList(int platform, Integer userId) {

        List<NewcomerInfo> missionList = new ArrayList<>();

        // 查询新手任务列表
        List<ReNewcomerMission> reNewcomerMissionList = reNewcomerMissionDAO.selectByPlatform(platform);
        for (ReNewcomerMission mission : reNewcomerMissionList) {

            NewcomerInfo info = new NewcomerInfo();
            info.setMissionId(mission.getMissionId());
            info.setMissionContent(mission.getMissionName());
            info.setMissionAward("(+"+mission.getMissionAward()+"元)");
            info.setMissionType(mission.getMissionType());
            if(userId == null) {
                info.setMissionStatus(0);
            }else {
                // 查询新手任务是否完成
                ReNewcomerMissionDetail detail = reNewcomerMissionDetailDAO.selectByPrimaryKey(mission.getMissionId(), userId);
                if(detail == null) { // 未完成
                    info.setMissionStatus(0);
                }else { // 已完成
                    info.setMissionStatus(1);
                }
            }
            missionList.add(info);
        }
        return missionList;
    }

    /**
     * 领取新手任务奖励
     *
     * @param missionId     任务id
     * @param missionType   任务类型
     * @param userId        用户id
     * @return
     */
    public String award(HttpServletRequest request,long missionId, int missionType, int userId) {

        boolean isIos = CommonMethod.isRequestPlatformIos(request);

        Map<String, Object> dataResult = new HashMap<>(2);

        // 查询是否已经领取过奖励
        ReNewcomerMissionDetail detail = reNewcomerMissionDetailDAO.selectByPrimaryKey(missionId, userId);
        if(detail != null) { // 已领取过奖励
            dataResult.put("code", 2);
            dataResult.put("msg", "已领取过奖励");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        // 判断是否完成任务
        boolean flag = false;

        if(missionType == NewcomerType.newcomer.val) { // 新手见面红包

            flag = true;

        }else if(missionType == NewcomerType.userInfo.val) { // 完善个人信息

            ReUser reUser = reUserDAO.selectByPrimaryKey(userId);
            // 姓名
            String realName = reUser.getRealName();
            // 性别
            int gender = reUser.getGender();
            // 职业
            String profession = reUser.getProfession();
            // 地址
            String address = reUser.getAddress();
            // 头像
            String portrait = reUser.getPortrait();
            // 昵称
            String nickname = reUser.getNickname();
            String mobile = reUser.getMobile();
            String name = mobile.substring(0,3) + "****" + mobile.substring(7,11);

            if(StringUtil.isEmpty(realName)
                                    || gender == 0
                                    || StringUtil.isEmpty(profession)
                                    || StringUtil.isEmpty(address)
                                    || portrait.contains("/portraits/")
                                    || nickname.equals(name)){

                if (isIos){
                    dataResult.put("url","hongbao://FREditTableViewController");
                }else {
                    dataResult.put("url","hongbao://PersonEditorActivity");
                }
            }else {
                flag = true;
            }

        }else if(missionType == NewcomerType.trial.val) { // 完成一次试玩任务

            ReAppTask task = reAppTaskDAO.selectFinishByUserId(userId);
            ReAndriodUserTask reAndriodUserTask = reAndriodUserTaskDAO.selectFinishByUserId(userId);

            if(task != null || reAndriodUserTask != null) { // 已完成
                flag = true;
            }else {

                dataResult.put("url","hongbao://TrialAccountActivity");
            }

        }else if(missionType == NewcomerType.attention.val) { // 完成一次关注任务

            ReCodeExchangeDetail reCodeExchangeDetail = reCodeExchangeDetailDAO.selectByUserId(userId);
            if(reCodeExchangeDetail != null) { // 已完成
                flag = true;
            }else {
                if (isIos){
                    dataResult.put("url","hongbao://FRAttentionMissionViewController");
                }else {
                    dataResult.put("url","hongbao://AttentionTaskActivity");
                }
            }

        }else if(missionType == NewcomerType.share.val) { // 完成一次分享任务

            ReShareMissionDetail reShareMissionDetail = reShareMissionDetailDAO.selectByUserId(userId);
            if(reShareMissionDetail != null) { // 已完成
                flag = true;
            }else {
                if (isIos){
                    dataResult.put("url","hongbao://FRShareMissionViewController");
                }else {
                    dataResult.put("url","hongbao://ForwardingTaskActivity");
                }
            }

        }else if(missionType == NewcomerType.great.val) { // 完成一次高额任务

            ReRecommendTask reRecommendTask = reRecommendTaskDAO.selectOneByUserId(userId);
            if(reRecommendTask != null) { // 已完成
                flag = true;
            }else {
                if (isIos){
                    dataResult.put("url","hongbao://FRHighMissionViewController");
                }else {
                    dataResult.put("url","hongbao://HighMissionActivity");
                }
            }
        }

        if(!flag) { // 未完成

            dataResult.put("code", 0);
            dataResult.put("msg", "还未完成相应任务,快去体验吧");
            return JsonUtil.buildSuccessDataJson(dataResult);

        }

        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);

        // 查询任务
        ReNewcomerMission reNewcomerMission = reNewcomerMissionDAO.selectByPrimaryKey(missionId);
        BigDecimal award = reNewcomerMission.getMissionAward();

        // 记录领取明细
        ReNewcomerMissionDetail missionDetail = new ReNewcomerMissionDetail();
        missionDetail.setMissionId(missionId);
        missionDetail.setUserId(userId);
        missionDetail.setMoney(award);
        missionDetail.setDrawTime(nowTime);
        reNewcomerMissionDetailDAO.insert(missionDetail);

        // 修改账户金额
        ReUser user = reUserDAO.selectLockByUserId(userId);
        user.setUserMoney(user.getUserMoney().add(award));
        user.setTodayMoney(user.getTodayMoney().add(award));
        user.setUpdateTime(now);
        reUserDAO.updateByPrimaryKeySelective(user);

        // 记录账户明细
        ReAccountDetail reAccountDetail = new ReAccountDetail();
        reAccountDetail.setUserId(userId);
        reAccountDetail.setAppId(user.getAppId());
        reAccountDetail.setAccountMoney(award);
        reAccountDetail.setDetailType(1);
        reAccountDetail.setMissionType(MissionType.other_mission.val);
        reAccountDetail.setMissionSubtype(MissionSubtype.newcomer.val);
        reAccountDetail.setDetailContent("完成新手任务["+reNewcomerMission.getMissionName()+"]");
        reAccountDetail.setDetailTime(nowTime);
        reAccountDetailDAO.insert(reAccountDetail);

        // 删除缓存
        userCacheService.removeUser(userId);

        dataResult.put("code", 1);
        dataResult.put("msg", award);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 领取新手任务奖励
     *
     * @param missionId
     * @param missionType   任务类型
     * @param userId
     * @return
     */
    public String award2(HttpServletRequest request,long missionId, int missionType, int userId) {

        boolean isIos = CommonMethod.isRequestPlatformIos(request);

        Map<String, Object> dataResult = new HashMap<>(2);

        // 查询是否已经领取过奖励
        ReNewcomerMissionDetail detail = reNewcomerMissionDetailDAO.selectByPrimaryKey(missionId, userId);
        if(detail != null) { // 已领取过奖励
            dataResult.put("code", 2);
            dataResult.put("msg", "已领取过奖励");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        // 判断是否完成任务
        boolean flag = false;

        if(missionType == NewcomerType.newcomer.val) { // 新手见面红包

            flag = true;

        }else if(missionType == NewcomerType.userInfo.val) { // 完善个人信息

            ReUser reUser = reUserDAO.selectByPrimaryKey(userId);
            // 姓名
            String realName = reUser.getRealName();
            // 性别
            int gender = reUser.getGender();
            // 职业
            String profession = reUser.getProfession();
            // 地址
            String address = reUser.getAddress();
            // 头像
            String portrait = reUser.getPortrait();
            // 昵称
            String nickname = reUser.getNickname();
            String mobile = reUser.getMobile();
            String name = mobile.substring(0,3) + "****" + mobile.substring(7,11);

            if(StringUtil.isEmpty(realName)
                    || gender == 0
                    || StringUtil.isEmpty(profession)
                    || StringUtil.isEmpty(address)
                    || portrait.contains("/portraits/")
                    || nickname.equals(name)){

                if (isIos){
                    dataResult.put("url","hongbao://FREditTableViewController");
                }else {
                    dataResult.put("url","hongbao://PersonEditorActivity");
                }
            }else {
                flag = true;
            }

        }else if(missionType == NewcomerType.trial.val) { // 完成一次试玩任务

            ReAppTask task = reAppTaskDAO.selectFinishByUserId(userId);
            ReAndriodUserTask reAndriodUserTask = reAndriodUserTaskDAO.selectFinishByUserId(userId);

            if(task != null || reAndriodUserTask != null) { // 已完成
                flag = true;
            }else {
                dataResult.put("url","hongbao://NewTrialsActivity");
            }

        }else if(missionType == NewcomerType.attention.val) { // 完成一次关注任务

            ReCodeExchangeDetail reCodeExchangeDetail = reCodeExchangeDetailDAO.selectByUserId(userId);
            if(reCodeExchangeDetail != null) { // 已完成
                flag = true;
            }else {
                if (isIos){
                    dataResult.put("url","hongbao://FRAttentionMissionViewController");
                }else {
                    dataResult.put("url","hongbao://AttentionTaskActivity");
                }
            }

        }else if(missionType == NewcomerType.share.val) { // 完成一次分享任务

//            ReShareMissionDetail reShareMissionDetail = reShareMissionDetailDAO.selectByUserId(userId);
            ReArticleClickDetail clickDetail = reArticleClickDetailDAO.selectByUserId(userId);
            if(clickDetail != null) { // 已完成
                flag = true;
            }else {
                if (isIos){
                    dataResult.put("url","hongbao://FRShareMissionViewController");
                }else {
                    dataResult.put("url","hongbao://ForwardingTaskActivity");
                }
            }

        }else if(missionType == NewcomerType.great.val) { // 完成一次高额任务

            ReRecommendTask reRecommendTask = reRecommendTaskDAO.selectOneByUserId(userId);
            if(reRecommendTask != null) { // 已完成
                flag = true;
            }else {
                if (isIos){
                    dataResult.put("url","hongbao://FRHighMissionViewController");
                }else {
                    dataResult.put("url","hongbao://HighMissionActivity");
                }
            }
        }

        if(!flag) { // 未完成

            dataResult.put("code", 0);
            dataResult.put("msg", "还未完成相应任务,快去体验吧");
            return JsonUtil.buildSuccessDataJson(dataResult);

        }

        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);

        // 查询任务
        ReNewcomerMission reNewcomerMission = reNewcomerMissionDAO.selectByPrimaryKey(missionId);
        BigDecimal award = reNewcomerMission.getMissionAward();

        // 记录领取明细
        ReNewcomerMissionDetail missionDetail = new ReNewcomerMissionDetail();
        missionDetail.setMissionId(missionId);
        missionDetail.setUserId(userId);
        missionDetail.setMoney(award);
        missionDetail.setDrawTime(nowTime);
        reNewcomerMissionDetailDAO.insert(missionDetail);

        // 修改账户金额
        ReUser user = reUserDAO.selectLockByUserId(userId);
        user.setUserMoney(user.getUserMoney().add(award));
        user.setTodayMoney(user.getTodayMoney().add(award));
        user.setUpdateTime(now);
        reUserDAO.updateByPrimaryKeySelective(user);

        // 记录账户明细
        ReAccountDetail reAccountDetail = new ReAccountDetail();
        reAccountDetail.setUserId(userId);
        reAccountDetail.setAppId(user.getAppId());
        reAccountDetail.setAccountMoney(award);
        reAccountDetail.setDetailType(1);
        reAccountDetail.setMissionType(MissionType.other_mission.val);
        reAccountDetail.setMissionSubtype(MissionSubtype.newcomer.val);
        reAccountDetail.setDetailContent("完成新手任务["+reNewcomerMission.getMissionName()+"]");
        reAccountDetail.setDetailTime(nowTime);
        reAccountDetailDAO.insert(reAccountDetail);

        // 删除缓存
        userCacheService.removeUser(userId);

        dataResult.put("code", 1);
        dataResult.put("msg", award);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

}
