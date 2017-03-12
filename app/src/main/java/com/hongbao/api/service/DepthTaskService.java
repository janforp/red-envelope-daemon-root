package com.hongbao.api.service;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.dao.*;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.*;
import com.hongbao.api.model.dto.MoneyAndCountInfo;
import com.hongbao.api.model.vo.DepthVo;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.RequestUtil;
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
 * Created by Summer on 2016/12/5.
 */
@Service
public class DepthTaskService {

    @Autowired
    private ReAndriodDeepMissionDAO reAndriodDeepMissionDAO;
    @Autowired
    private ReAndriodIntegralWallDAO reAndriodIntegralWallDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReModuleUserInfoDAO reModuleUserInfoDAO;
    @Autowired
    private UserCacheService userCacheService;


    /**
     * 查询深度任务 个数 金额
     *
     * @param userId
     * @return
     */
    public MoneyAndCountInfo selectDepth(Integer userId) {

        MoneyAndCountInfo info = new MoneyAndCountInfo();

        int count = 0;
        BigDecimal money = new BigDecimal("0.00");

        if(userId != null) {
            List<ReAndriodIntegralWall> wallList = reAndriodDeepMissionDAO.selectUnderwayByUserId(userId, CommonMethod.fmtDay(System.currentTimeMillis()));
            if(wallList != null) {
                count = wallList.size();
                for (ReAndriodIntegralWall wall : wallList) {
                    money = money.add(wall.getStepTwoMoney());
                }
            }
        }

        if(count == 0) {
            info.setCount("");
            info.setMoney("");
        }else {
            info.setMoney("+" + money + "元");
            info.setCount("" + count);
        }

        return info;

    }


    /**
     * 深度任务列表
     *
     * @param userId
     * @return
     */
    public List<DepthVo> selectDepthList(String userId) {
        List<DepthVo> list = new ArrayList<>();
        if(!StringUtil.isEmpty(userId)) {
            // 查询可做的深度任务
            List<ReAndriodIntegralWall> wallList = reAndriodDeepMissionDAO.selectUnderwayByUserId(Integer.valueOf(userId), CommonMethod.fmtDay(System.currentTimeMillis()));
            for (ReAndriodIntegralWall wall : wallList) {
                DepthVo vo = new DepthVo();
                vo.setMissionId(wall.getWallId());
                vo.setMissionTitle(wall.getAppName());
                vo.setMissionIcon(wall.getAppIcon());
                vo.setAppPackage(wall.getAppPackage());
                vo.setAppUrl(wall.getAppUrl());
                vo.setOpenSecond(wall.getStepTwoSecond());
                vo.setMoney(wall.getStepTwoMoney());
                list.add(vo);
            }
        }
        return list;
    }

    /**
     * 完成深度任务 领取奖励
     *
     * @param request
     * @param missionId
     * @return
     */
    public String finish(HttpServletRequest request, Long missionId) {

        Map<String, Object> dataResult = new HashMap<>(2);

        long now = System.currentTimeMillis();
        String nowDay =  CommonMethod.fmtDay(now);
        String nowTime = CommonMethod.fmtTime(now);

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

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        ReAndriodIntegralWall reAndriodIntegralWall = reAndriodIntegralWallDAO.selectByPrimaryKey(missionId);
        BigDecimal money = reAndriodIntegralWall.getStepTwoMoney();

        // 更新任务
        ReAndriodDeepMission reAndriodDeepMission = reAndriodDeepMissionDAO.selectLockByWallId(userId, missionId, nowDay);
        if(reAndriodDeepMission == null) {
            dataResult.put("code", 0);
            dataResult.put("msg", "您已经领取过奖励了!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }
        reAndriodDeepMission.setFinishTimes(reAndriodDeepMission.getFinishTimes() + 1);
        reAndriodDeepMission.setLastFinishTime(nowDay);
        reAndriodDeepMission.setUpdateTime(nowTime);
        reAndriodDeepMissionDAO.updateByPrimaryKeySelective(reAndriodDeepMission);

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
        reAccountDetail.setMissionType(MissionType.deepness_mission.val);
        reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
        reAccountDetail.setDetailContent("完成深度任务["+reAndriodIntegralWall.getAppName()+"]");
        reAccountDetail.setDetailTime(nowTime);
        reAccountDetailDAO.insert(reAccountDetail);

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

        dataResult.put("code", 1);
        dataResult.put("msg", "恭喜您获得"+money+"元奖励!");

        // 删除缓存
        userCacheService.removeUser(userId);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }



}
