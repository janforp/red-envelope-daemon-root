package com.hongbao.api.web.controller.rest.aesfull;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.HbFixedRedService;
import com.hongbao.api.service.HbReceiveService;
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
@RequestMapping(value = "/rest/a/f/r", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbReceiveController {

    @Autowired
    private HbFixedRedService hbFixedRedService;
    @Autowired
    private HbReceiveService hbReceiveService;


    @RequestMapping(value = "/countdown")
    @ResponseBody
    public String countdown(HttpServletRequest request,
                            @RequestParam(value = ParamConsts.redId, required = true) int redId) {
        return hbFixedRedService.getFixedRedCountdown(redId);
    }

    /**
     * 领取定时红包
     * @param request
     * @param type
     * @param redId
     * @return
     */
    @RequestMapping(value = "/rec")
    @ResponseBody
    public String receive(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.type, required = true) int type,
                          @RequestParam(value = ParamConsts.redId, required = true) int redId) {
        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return hbReceiveService.receiveRed(userId, type, redId);
    }


}
