package com.hongbao.api.web.controller.rest.rsa.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.ReLoadHtml;
import com.hongbao.api.service.LoadHtmlService;
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
 * Created by Summer on 2016/11/22.
 */
@RequestMapping(value = "/rest/r/html", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class LoadHtmlController {

    @Autowired
    private LoadHtmlService loadHtmlService;


    /**
     * 网页链接列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request) {

        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        List<ReLoadHtml> htmlList = loadHtmlService.selectHtmlList(deviceId);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("htmlList", htmlList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 网页打开成功
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/success")
    @ResponseBody
    public String success(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.htmlId, required = true) long htmlId) {

        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        return loadHtmlService.success(deviceId, htmlId);

    }


}
