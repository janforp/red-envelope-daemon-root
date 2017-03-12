package com.hongbao.api.web.controller.rest.rsa.v3;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.service.*;
import com.hongbao.api.service.user.HbUserService;
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
 * Created by Summer on 16/10/25.
 */
@RequestMapping(value = "/rest/r/index3", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbIndex3Controller {

    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private HbBannerService hbBannerService;
    @Autowired
    private HbNavigationService hbNavigationService;
    @Autowired
    private HbFixedRedService hbFixedRedService;
    @Autowired
    private HbIndexService hbIndexService;
    @Autowired
    private HbUserService hbUserService;
    @Autowired
    private HbRecommendService hbRecommendService;

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
        // 今日收益
        ReUserMoneyAndScore income = hbUserService.getTodayIncome(userId);
        // 滚动列表
        List<RollContent> rollList = hbIndexService.selectRollList();
        // 导航列表
        List<ReNavigationInfo> navigationList = hbNavigationService.getNavigationList(platform, version, packageName, channelName, flag);
        // 推荐任务
        List<RecommendInfo> recommendList = hbRecommendService.selectRecommendList(platform, flag, userId);
        // 定时红包
        List<ReFixedRedInfo> fixedRedList = hbFixedRedService.getFixedRedList(platform);
        // 理财红包
        List<ReCodeRedInfo> missionList = hbIndexService.selectMissionList(platform, flag);

        Map<String, Object> dataResult = new HashMap<>(7);
        dataResult.put("bannerList", bannerList);
        dataResult.put("income", income);
        dataResult.put("rollList", rollList);
        dataResult.put("navigationList", navigationList);
        dataResult.put("recommendList", recommendList);
        dataResult.put("fixedRedList", fixedRedList);
        dataResult.put("missionList", missionList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

}
