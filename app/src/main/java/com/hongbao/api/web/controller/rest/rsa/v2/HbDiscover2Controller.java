package com.hongbao.api.web.controller.rest.rsa.v2;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.dto.ReDiscoverInfo;
import com.hongbao.api.model.dto.ReIntegralWallInfo;
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
 * Created by Summer on 16/8/12.
 */
@RequestMapping(value = "/rest/r/discover2", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbDiscover2Controller {

    @Autowired
    private HbDiscoverService hbDiscoverService;
    @Autowired
    private HbPackageChannelService hbPackageChannelService;

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

        // 发现列表
        List<ReDiscoverInfo> discoverList = hbDiscoverService.selectDiscoverList(platform, version, packageName, channelName);

        // 积分墙
        List<ReIntegralWallInfo> wallList = hbDiscoverService.selectWallList(platform, version, packageName, channelName, flag);

        Map<String, Object> dataResult = new HashMap<>(3);
        dataResult.put("bannerList", bannerList);
        dataResult.put("discoverList", discoverList);
        dataResult.put("wallList", wallList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 任务大厅 查看全部
     * @param request
     * @return
     */
    @RequestMapping(value = "/mission")
    @ResponseBody
    public String mission(HttpServletRequest request) {

        // 版本
        String version = (String) request.getAttribute(RequestConsts.ATTR_VERSION);
        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        // 平台 0-ios, 1-andriod
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        // 任务列表
        List<ReDiscoverInfo> discoverList = hbDiscoverService.selectDiscoverList(platform, version, packageName, channelName);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("discoverList", discoverList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


}
