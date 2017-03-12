package com.hongbao.api.web.controller.rest.aesfull.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.PasswordRedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/11/16.
 * 口令红包
 */
@RequestMapping(value = "/rest/a/f/password", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class PasswordRedController {

    @Autowired
    private PasswordRedService passwordRedService;


    /**
     * 领取口令红包
     * @param request
     * @param redPassword
     * @return
     */
    @RequestMapping(value = "/getRed")
    @ResponseBody
    public String getPasswordRed(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.redPassword, required = true) String redPassword){

        return passwordRedService.doGetPasswordRed(request, redPassword);
    }

}
