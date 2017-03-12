package com.hongbao.api.web.controller.rest.aesfull.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.DepthTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2016/12/5.
 */
@RequestMapping(value = "/rest/a/f/depth", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class DepthTaskController {

    @Autowired
    private DepthTaskService depthTaskService;

    /**
     * 完成试玩任务 (积分墙) 领取奖励
     *
     * @param request
     * @param missionId     积分墙任务ID
     * @return
     */
    @RequestMapping(value = "/finish")
    @ResponseBody
    public String finish(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.missionId, required = true)Long missionId){
        return depthTaskService.finish(request, missionId);
    }

}