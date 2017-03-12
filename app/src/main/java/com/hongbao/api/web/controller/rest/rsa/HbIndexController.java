package com.hongbao.api.web.controller.rest.rsa;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.service.*;
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
@RequestMapping(value = "/rest/r/index", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbIndexController {

    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private HbBannerService hbBannerService;
    @Autowired
    private HbNavigationService hbNavigationService;
    @Autowired
    private HbFixedRedService hbFixedRedService;
    @Autowired
    private HbIndexSortService hbIndexSortService;
    @Autowired
    private HbIndexService hbIndexService;

    /**
     * 首页数据
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

        // banner列表
        List<ReBannerInfo> bannerList = hbBannerService.getBannerList(platform, version, packageName, channelName, flag);
        // 导航列表
        List<ReNavigationInfo> navigationList = hbNavigationService.getNavigationList(platform, version, packageName, channelName, flag);
        // 定时红包
        List<ReFixedRedInfo> fixedRedList = hbFixedRedService.getFixedRedList(platform);
        // 分类列表
        List<ReIndexSortInfo> sortList = hbIndexSortService.getIndexSortList(platform, version, packageName, channelName);
        // 滚动列表
        List<RollInfo> rollList = hbIndexService.selectRollInfoList();

        Map<String, Object> dataResult = new HashMap<>(5);
        dataResult.put("bannerList", bannerList);
        dataResult.put("navigationList", navigationList);
        dataResult.put("fixedRedList", fixedRedList);
        dataResult.put("sortList", sortList);
        dataResult.put("rollList", rollList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

}
