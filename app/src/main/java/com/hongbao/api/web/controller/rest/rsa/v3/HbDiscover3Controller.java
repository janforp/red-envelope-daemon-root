package com.hongbao.api.web.controller.rest.rsa.v3;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.service.HbCoinService;
import com.hongbao.api.service.HbDiscoverService;
import com.hongbao.api.service.HbPackageChannelService;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/10/25.
 */
@RequestMapping(value = "/rest/r/discover3", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbDiscover3Controller {

    @Autowired
    private HbDiscoverService hbDiscoverService;
    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private HbCoinService hbCoinService;

    /**
     * 发现页数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request) {

        // 平台 0-ios, 1-andriod
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);
        // 版本
        String version = (String) request.getAttribute(RequestConsts.ATTR_VERSION);
        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);

        // true: 正常  false: 审核中
        boolean flag = hbPackageChannelService.getFlag(version, packageName, channelName);

        // 发现banner
        List<ReBannerInfo> bannerList = hbDiscoverService.selectDiscoverBanner(platform, version, packageName, channelName, flag);
        // 小游戏
        ReDiscoverModel discoverModel = hbDiscoverService.selectDiscover(platform, version, packageName, channelName);
        // 任务中心
        ReIntegralWallModel wallModel = hbDiscoverService.selectWallModel(platform, version, packageName, channelName, flag);
        // 商城
        CoinItemModel itemModel = hbCoinService.selectItemModel();

        Map<String, Object> dataResult = new HashMap<>(4);
        dataResult.put("bannerList", bannerList);
        dataResult.put("discoverModel", discoverModel);
        dataResult.put("wallModel", wallModel);
        dataResult.put("itemModel", itemModel);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 查询积分墙列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wall")
    @ResponseBody
    public String wall(HttpServletRequest request) {

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

        // 积分墙
        List<ReIntegralWallInfo3> wallList = hbDiscoverService.selectWallList3(platform, version, packageName, channelName, flag);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("wallList", wallList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

}
