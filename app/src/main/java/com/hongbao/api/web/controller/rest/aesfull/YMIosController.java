package com.hongbao.api.web.controller.rest.aesfull;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.YMIosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 16/8/18.
 */
@RequestMapping(value = "/rest/a/f/ym", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class YMIosController {

    @Autowired
    private YMIosService ymIosService;

    /**
     * 获取 有米 积分墙url
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/url")
    @ResponseBody
    public String url(HttpServletRequest request,
                      @RequestParam(value = ParamConsts.ver, required = true) String version) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return ymIosService.getYMUrl(userId, version);

    }

}
