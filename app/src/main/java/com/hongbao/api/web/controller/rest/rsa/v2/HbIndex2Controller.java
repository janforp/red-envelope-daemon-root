package com.hongbao.api.web.controller.rest.rsa.v2;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.service.*;
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
 * 2.0 版本首页
 */
@RequestMapping(value = "/rest/r/index2", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbIndex2Controller {

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
    @Autowired
    private HbCoinService hbCoinService;
    @Autowired
    private HbPackageChannelService hbPackageChannelService;

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
        // 滚动列表
        List<RollContent> rollList = hbIndexService.selectRollList();
        // 定时红包
        List<ReFixedRedInfo> fixedRedList = hbFixedRedService.getFixedRedList(platform);
        // 分类列表
        List<ReIndexSortInfo2> sortList = hbIndexSortService.getIndexSortList2(platform, version, packageName, channelName);
        // 商品列表
        List<CoinItemInfo> itemList = hbCoinService.selectItemList();

        Map<String, Object> dataResult = new HashMap<>(6);
        dataResult.put("bannerList", bannerList);
        dataResult.put("navigationList", navigationList);
        dataResult.put("rollList", rollList);
        dataResult.put("fixedRedList", fixedRedList);
        dataResult.put("sortList", sortList);
        dataResult.put("itemList", itemList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 查询分类数据
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/sort")
    @ResponseBody
    public String sort(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.id, required = true) int id) {

        // 平台 0-ios, 1-andriod
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        // 查询分类数据
        List<ReCodeRedInfo> redList = hbIndexSortService.getRedList(platform, id);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("redList", redList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

}
