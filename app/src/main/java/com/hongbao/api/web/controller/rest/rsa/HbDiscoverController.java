package com.hongbao.api.web.controller.rest.rsa;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.ReDiscoverInfo;
import com.hongbao.api.service.HbDiscoverService;
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
@RequestMapping(value = "/rest/r/discover", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbDiscoverController {

    @Autowired
    private HbDiscoverService hbDiscoverService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request) {

        // 平台 0-ios, 1-andriod
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);
        // 版本
        String version = (String) request.getAttribute(RequestConsts.ATTR_VERSION);
        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);

        // 发现列表
        List<ReDiscoverInfo> discoverList = hbDiscoverService.selectDiscoverList(platform, version, packageName, channelName);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("discoverList", discoverList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

}
