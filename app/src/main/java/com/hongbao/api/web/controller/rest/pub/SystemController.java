package com.hongbao.api.web.controller.rest.pub;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.ReStartAdInfo;
import com.hongbao.api.service.HbStartAdService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuqiang on 15-8-4.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/rest/p/sys", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class SystemController {

    @Autowired
    private HbStartAdService hbStartAdService;

    /**
     * 当前服务器时间
     * @return
     */
    @RequestMapping(value = "/getTimeMillis")
    @ResponseBody
    public String getTimeMillis() {
        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("currentTimeMillis", String.valueOf(System.currentTimeMillis()));
        return JsonUtil.buildSuccessDataJson(dataMap);
    }


    /**
     * APP启动时要调用此结果，获取信息： 1：APP启动图片
     */
    @RequestMapping(value = "/startup")
    @ResponseBody
    public String startup(HttpServletRequest request) {

        // 平台 0-ios, 1-andriod
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        ReStartAdInfo ad = hbStartAdService.getStartAd(platform);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("ad", ad);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


}