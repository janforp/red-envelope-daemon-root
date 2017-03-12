package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.dao.ReFixedRedDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.model.ReFixedRed;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.vo.MissionNavigationVo;
import com.hongbao.api.model.vo.MissionVo;
import com.hongbao.api.model.vo.NavigationVo;
import com.hongbao.api.service.*;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.RandomUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Janita on 2016/12/22.
 */
@Controller
@RequestMapping(value = "/test", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
public class TestController {

    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private HbBannerService hbBannerService;
    @Autowired
    private HbIndexService hbIndexService;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private WelfareService welfareService;
    @Autowired
    private GreatMissionService greatMissionService;

    private static Logger selfLogger = LoggerFactory.getLogger(TestController.class);

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
        int platform = 0;
        // 版本
        String version = "5.0.0";
        // 包名
        String packageName = "com.wj.hongbao";
        // 渠道
        String channelName = "AppStore";
        // 版本号
        int versioncode = 1020;
        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        // true: 正常  false: 审核中
        boolean flag = hbPackageChannelService.getAppVersionStatus(packageName, channelName, versioncode, deviceId);

        // banner列表
        List<ReBannerInfo> bannerList = hbBannerService.getBannerList(platform, version, packageName, channelName, flag);
        // 入口导航
        List<NavigationVo> navigationList = navigationService.indexNavigationList(platform);
        // 滚动列表
        List<String> rollList = hbIndexService.indexRollList();
        // 任务导航
        MissionNavigationVo mission = navigationService.indexMissionNavigationList(flag, platform);
        // 精选福利
        MissionVo selection = welfareService.indexSelection(platform);

        Map<String, Object> dataResult = new HashMap<>(5);
        dataResult.put("bannerList", bannerList);
        dataResult.put("navigationList", navigationList);
        dataResult.put("rollList", rollList);
        dataResult.put("mission", mission);
        dataResult.put("selection", selection);


        selfLogger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        return JsonUtil.buildSuccessDataJson(dataResult);

    }
}