package com.hongbao.api.web.controller.rest.aesfull.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.NewcomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2016/11/16.
 */
@RequestMapping(value = "/rest/a/f/newcomer", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class NewcomerController {

    @Autowired
    private NewcomerService newcomerService;

    /**
     * 领取 新手任务 奖励
     * @param request
     * @param missionType
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/award")
    @ResponseBody
    public String award(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.type, required = true) int missionType,
                        @RequestParam(value = ParamConsts.id, required = true) long missionId) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return newcomerService.award(request,missionId, missionType, userId);

    }

    /**
     * 领取 新手任务 奖励
     * @param request
     * @param missionType
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/award2")
    @ResponseBody
    public String award2(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.type, required = true) int missionType,
                         @RequestParam(value = ParamConsts.id, required = true) long missionId) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return newcomerService.award2(request,missionId, missionType, userId);

    }


}
