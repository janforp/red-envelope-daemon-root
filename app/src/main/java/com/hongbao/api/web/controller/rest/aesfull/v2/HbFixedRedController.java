package com.hongbao.api.web.controller.rest.aesfull.v2;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.HbFixedRedService;
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
@RequestMapping(value = "/rest/a/f/fixed", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbFixedRedController {

    @Autowired
    private HbFixedRedService hbFixedRedService;

    /**
     * 点击定时红包 查询用户是否领取
     * @param request
     * @param redId
     * @return
     */
    @RequestMapping(value = "/click")
    @ResponseBody
    public String click(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.redId, required = true) int redId) {
        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return hbFixedRedService.getUserIsReceived(userId, redId);
    }


}
