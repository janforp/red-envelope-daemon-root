package com.hongbao.api.web.controller.rest.rsa.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.ClickShareMissionListVo;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.model.vo.ArticleVo;
import com.hongbao.api.model.vo.DepthVo;
import com.hongbao.api.model.vo.PasswordRedIntroduction;
import com.hongbao.api.service.*;
import com.hongbao.api.service.redis.RedisBannerService;
import com.hongbao.api.service.user.HbUserService;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/11/14.
 */
@RequestMapping(value = "/rest/r/index4", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbIndex4Controller {

    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private RedisBannerService redisBannerService;
    @Autowired
    private HbUserService hbUserService;
    @Autowired
    private HbIndexService hbIndexService;
    @Autowired
    private TrialTaskService trialTaskService;
    @Autowired
    private AppClickShareService appClickShareService;
    @Autowired
    private HbRecommendService hbRecommendService;
    @Autowired
    private PasswordRedService passwordRedService;
    @Autowired
    private NewcomerService newcomerService;
    @Autowired
    private HbFixedRedService hbFixedRedService;
    @Autowired
    private DepthTaskService depthTaskService;

    /**
     * 首页数据
     *
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.userId, required = true) int userId) {

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

        // 收益
        ReUserMoneyInfo money = hbUserService.getUserMoney(userId);
        // 滚动列表
        List<String> rollList = hbIndexService.indexRollRedis();
        // 任务入口
        List<IndexMissionInfo> missionList = hbIndexService.indexMissionList(platform, flag, userId);
        // banner列表
        List<ReBannerInfo> bannerList = redisBannerService.selectIndexBanner(platform, flag, versioncode, packageName, channelName);
        // 服务入口
        List<IndexServiceInfo> serviceList = hbIndexService.indexServiceList(platform, flag);

        Map<String, Object> dataResult = new HashMap<>(5);
        dataResult.put("money", money);
        dataResult.put("rollList", rollList);
        dataResult.put("missionList", missionList);
        dataResult.put("bannerList", bannerList);
        dataResult.put("serviceList", serviceList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 首页数据2
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index2")
    @ResponseBody
    public String index2(HttpServletRequest request) {

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

        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        // 收益
        ReUserMoneyInfo money = hbUserService.getUserMoney2(userId);
        // 滚动列表
        List<String> rollList = hbIndexService.indexRollRedis();
        // 任务入口
        List<IndexMissionInfo> missionList = hbIndexService.indexMissionList2(platform, flag, userId);
        // banner列表
        List<ReBannerInfo> bannerList = redisBannerService.selectIndexBanner(platform, flag, versioncode, packageName, channelName);
        // 服务入口
        List<IndexServiceInfo> serviceList = hbIndexService.indexServiceList(platform, flag);

        Map<String, Object> dataResult = new HashMap<>(5);
        dataResult.put("money", money);
        dataResult.put("rollList", rollList);
        dataResult.put("missionList", missionList);
        dataResult.put("bannerList", bannerList);
        dataResult.put("serviceList", serviceList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 首页收益
     *
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/money")
    @ResponseBody
    public String money(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.userId, required = true) int userId) {

        // 收益
        ReUserMoneyInfo money = hbUserService.getUserMoney(userId);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("money", money);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 试玩任务列表 (android aso任务)
     *
     * @param request
     * @param userId
     * @param manufacturer
     * @param os
     * @return
     */
    @RequestMapping(value = "/trial")
    @ResponseBody
    public String trial(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.userId, required = true) int userId,
                        @RequestParam(value = ParamConsts.manufacturer, required = true) String manufacturer,
                        @RequestParam(value = ParamConsts.os, required = true) String os,
                        @RequestParam(value = ParamConsts.app, required = true) String app) {

        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        Integer user = userId;
        if(userId == 0) {
            user = null;
        }

        List<TrialTaskModel> trialList = trialTaskService.selectTrialList(user, manufacturer, os, deviceId, app);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("trialList", trialList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 试玩任务列表 (andriod aso任务 积分墙)
     *
     * @param request
     * @param manufacturer  设备厂商
     * @param os            操作系统
     * @param app           用户app列表json
     * @param sim           0-没有sim卡，1-有sim卡
     * @return
     */
    @RequestMapping(value = "/trial2")
    @ResponseBody
    public String trial2(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.manufacturer, required = true) String manufacturer,
                         @RequestParam(value = ParamConsts.os, required = true) String os,
                         @RequestParam(value = ParamConsts.app, required = true) String app,
                         @RequestParam(value = ParamConsts.sim, required = true) int sim) {

        List<TrialModel> trialList = trialTaskService.selectTrialList(request, manufacturer, os, app, sim);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("trialList", trialList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 深度任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/depth")
    @ResponseBody
    public String depth(HttpServletRequest request) {

        // 用户id
        String userId = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);

        List<DepthVo> depthList = depthTaskService.selectDepthList(userId);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("depthList", depthList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 分享任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/share")
    @ResponseBody
    public String share(HttpServletRequest request) {

        List<ClickShareMissionListVo> shareList = appClickShareService.getList();

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("shareList", shareList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 分享任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/share2")
    @ResponseBody
    public String share2(HttpServletRequest request) {

        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        // 累计收益
        BigDecimal money = appClickShareService.selectTotalMoney(userId);
        // 进行中的文章列表
        List<ArticleVo> articleList = appClickShareService.selectByArticleId(null);

        Map<String, Object> dataResult = new HashMap<>(3);
        dataResult.put("banner", "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/share_banner.png");
        dataResult.put("money", money + "元");
        dataResult.put("articleList", articleList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 关注任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/attention")
    @ResponseBody
    public String attention(HttpServletRequest request,
                            @RequestParam(value = ParamConsts.userId, required = true) int userId) {

        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        Integer user = userId;
        if(userId == 0) {
            user = null;
        }

        List<AttentionInfo> attentionList = hbRecommendService.selectAttentionList(platform, user);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("attentionList", attentionList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 获取口令红包玩法介绍的数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/getIntroduction")
    @ResponseBody
    public String getIntroduction(HttpServletRequest request){

        PasswordRedIntroduction introduction = passwordRedService.getIntroduction();

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("introduction", introduction);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }


    /**
     * 高额任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/great")
    @ResponseBody
    public String great(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.userId, required = true) int userId) {

        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        Integer user = userId;
        if(userId == 0) {
            user = null;
        }

        List<GreatInfo> greatList = hbRecommendService.selectGreatList(platform, user);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("greatList", greatList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 新手任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/newcomer")
    @ResponseBody
    public String newcomer(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.userId, required = true) int userId) {

        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        Integer user = userId;
        if(userId == 0) {
            user = null;
        }

        List<NewcomerInfo> missionList = newcomerService.selectMissionList(platform, user);

        Map<String, Object> dataResult = new HashMap<>(2);

        if(platform == 0) { // ios
            dataResult.put("img", "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/ibanner.png");
        }else { // andriod
            dataResult.put("img", "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/abanner.png");
        }

        dataResult.put("missionList", missionList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 定时红包列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/fix")
    @ResponseBody
    public String fixedRed(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.userId, required = true) int userId){

        Integer user = userId;
        if(userId == 0) {
            user = null;
        }

        List<ReFixedRed4Info> fixed = hbFixedRedService.getFixRed4List(user);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("fixedRedList", fixed);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 优惠生活列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/convenient")
    @ResponseBody
    public String convenientLife(HttpServletRequest request){

        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        // 优惠生活
        List<ReCodeRedInfo> missionList = hbIndexService.selectLifeList(platform);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("missionList", missionList);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

}
