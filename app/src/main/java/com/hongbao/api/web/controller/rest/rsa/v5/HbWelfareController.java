package com.hongbao.api.web.controller.rest.rsa.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.MissionInfo;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.vo.MissionVo;
import com.hongbao.api.model.vo.NavigationVo;
import com.hongbao.api.service.*;
import com.hongbao.api.service.redis.RedisBannerService;
import com.hongbao.api.service.redis.RedisWelfareService;
import com.hongbao.api.util.JsonUtil;
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
@RequestMapping(value = "/rest/r/welfare", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbWelfareController {

    @Autowired
    private WelfareService welfareService;
    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private RedisBannerService redisBannerService;
    @Autowired
    private RedisWelfareService redisWelfareService;

    /**
     * 福利
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request) {

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
        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        // true: 正常  false: 审核中
        boolean flag = hbPackageChannelService.selectAppVersionStatus(packageName, channelName, versioncode, deviceId);

        // banner列表
        List<ReBannerInfo> bannerList = redisBannerService.selectWekfareBanner(platform, flag, versioncode, packageName, channelName);
        // 导航列表
        List<NavigationVo> navigationList = redisWelfareService.navigationList(platform, flag);
        // 最新福利
        MissionVo welfare = redisWelfareService.selectListByPlatform(platform, flag);

        Map<String, Object> dataResult = new HashMap<>(3);
        dataResult.put("bannerList", bannerList);
        dataResult.put("navigationList", navigationList);
        dataResult.put("welfare", welfare);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 福利 分页
     *
     * IOS审核未通过
     * 现在审核的时候只显示精选的福利
     * @param request
     * @param type      福利分类
     * @param id        翻页前的最后一个id
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.type, required = true) int type,
                       @RequestParam(value = ParamConsts.id, required = true) long id) {

        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        // 版本号
        String versioncodeString = (String) request.getAttribute(RequestConsts.HEADER_VERSION_CODE);
        int versioncode = Integer.valueOf(versioncodeString);
        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        Integer welfareType = null;
        if(type != 0) {
            welfareType = type;
        }

        Long welfareId = null;
        if(id != 0) {
            welfareId = id;
        }

        // true: 正常  false: 审核中
        boolean flag = hbPackageChannelService.selectAppVersionStatus(packageName, channelName, versioncode, deviceId);

        List<MissionInfo> welfareList = redisWelfareService.selectListByPlatform(platform, welfareId, welfareType, flag);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("welfareList", welfareList);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }


}
