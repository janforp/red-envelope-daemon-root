package com.hongbao.api.web.controller.rest.rsa.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.model.vo.IndexMissionVo;
import com.hongbao.api.model.vo.MissionNavigationVo;
import com.hongbao.api.model.vo.NavigationVo;
import com.hongbao.api.model.vo.MissionVo;
import com.hongbao.api.service.*;
import com.hongbao.api.service.redis.RedisBannerService;
import com.hongbao.api.service.redis.RedisIndexNavigationService;
import com.hongbao.api.service.redis.RedisWelfareService;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/12/10.
 */
@RequestMapping(value = "/rest/r/index5", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbIndex5Controller {

    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private RedisBannerService redisBannerService;
    @Autowired
    private HbIndexService hbIndexService;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private RedisWelfareService redisWelfareService;
    @Autowired
    private GreatMissionService greatMissionService;
    @Autowired
    private HbIndex5Service hbIndex5Service;
    @Autowired
    private RedisIndexNavigationService redisIndexNavigationService;
    @Autowired
    private AndroidAsoService androidAsoService;


    /**
     * 首页数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request) {

        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);
        // 版本
        String version = (String) request.getAttribute(RequestConsts.ATTR_VERSION);
        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        // 版本号
        String versioncodeString = (String) request.getAttribute(RequestConsts.HEADER_VERSION_CODE);
        int versioncode = Integer.valueOf(versioncodeString);

        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        // true: 正常  false: 审核中
        boolean flag = hbPackageChannelService.selectAppVersionStatus(packageName, channelName, versioncode, deviceId);

        // 首页banner列表
        List<ReBannerInfo> bannerList = redisBannerService.selectIndexBanner(platform, flag, versioncode, packageName, channelName);
        // 入口导航
        List<NavigationVo> navigationList = navigationService.indexNavigationList(platform);
        // 滚动列表
        List<String> rollList = hbIndexService.indexRollRedis();
        // 任务导航
        MissionNavigationVo mission = redisIndexNavigationService.indexMissionNavigationList(flag, platform);
        // 精选福利
        MissionVo selection = redisWelfareService.indexSelection(platform);

        Map<String, Object> dataResult = new HashMap<>(5);
        dataResult.put("bannerList", bannerList);
        dataResult.put("navigationList", navigationList);
        dataResult.put("rollList", rollList);
        dataResult.put("mission", mission);
        dataResult.put("selection", selection);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 高额任务列表
     *
     * @param request
     * @param missionId 最后一条记录的id
     * @return
     */
    @RequestMapping(value = "/great")
    @ResponseBody
    public String great(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.missionId, required = true) long missionId) {

        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        // 最后一条记录的id
        Long id = missionId;
        if(missionId == 0) {
            id = null;
        }

        List<MissionInfo> greatList = greatMissionService.greatList(platform, userId, id);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("greatList", greatList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 首页数据2
     * 版本5.1.0
     *
     * @param request
     * @param manufacturer  设备厂商
     * @param os            操作系统
     * @param app           用户app列表json
     * @param sim           0-没有sim卡，1-有sim卡
     * @return
     */
    @RequestMapping(value = "/index2")
    @ResponseBody
    public String index2(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.manufacturer, required = false) String manufacturer,
                         @RequestParam(value = ParamConsts.os, required = false) String os,
                         @RequestParam(value = ParamConsts.app, required = false) String app,
                         @RequestParam(value = ParamConsts.sim, required = false) Integer sim) {

        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);
        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        // 版本号
        String versioncodeString = (String) request.getAttribute(RequestConsts.HEADER_VERSION_CODE);
        int versioncode = Integer.valueOf(versioncodeString);
        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        // true: 正常  false: 审核中
        boolean flag = hbPackageChannelService.selectAppVersionStatus(packageName, channelName, versioncode, deviceId);

        // 首页banner列表
        List<ReBannerInfo> bannerList = redisBannerService.selectIndexBanner(platform, flag, versioncode, packageName, channelName);
        // 入口导航
        List<NavigationVo> navigationList = navigationService.indexNavigationList2(platform, flag);
        // 滚动列表
        List<String> rollList = hbIndexService.indexRollRedis();
        // 专属任务
        IndexMissionVo exclusive = hbIndex5Service.selectExclusive(userId, platform, flag);
        // 热门任务
        IndexMissionVo hot = hbIndex5Service.selectHot(userId, platform, flag, manufacturer, os, app, sim);
        // 精选福利
        MissionVo selection = redisWelfareService.indexSelectionV5_1(platform, flag);


        Map<String, Object> dataResult = new HashMap<>(6);
        dataResult.put("bannerList", bannerList);
        dataResult.put("navigationList", navigationList);
        dataResult.put("rollList", rollList);
        dataResult.put("exclusive", exclusive);
        dataResult.put("hot", hot);
        dataResult.put("selection", selection);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 试玩任务列表 (android aso任务)
     *
     * @param request
     * @param manufacturer
     * @param os
     * @return
     */
    @RequestMapping(value = "/trial")
    @ResponseBody
    public String trial(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.manufacturer, required = true) String manufacturer,
                        @RequestParam(value = ParamConsts.os, required = true) String os,
                        @RequestParam(value = ParamConsts.app, required = true) String app) {

        List<TrialModel> trialList = androidAsoService.selectTrialList(request, manufacturer, os, app);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("trialList", trialList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

}
