package com.hongbao.api.web.controller.rest.aesfull.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.TrialTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2016/11/15.
 */
@RequestMapping(value = "/rest/a/f/trial", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class TrialTaskController {

    @Autowired
    private TrialTaskService trialTaskService;

    /**
     * 抢任务
     *
     * @param request
     * @param keywordId
     * @return
     */
    @RequestMapping(value = "/task")
    @ResponseBody
    public String task(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.keywordId, required = false)Long keywordId) {
        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return trialTaskService.getMission(userId, deviceId, keywordId);
    }

    /**
     * 放弃任务
     *
     * @param request
     * @param oldKeywordId 要放弃的任务的id(也有可能已经超时了)
     * @param newKeywordId 要放弃的任务的id(也有可能已经超时了)
     * @return
     */
    @RequestMapping(value = "/giveUp")
    @ResponseBody
    public String giveUp(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.oldKeywordId, required = false)Long oldKeywordId,
                         @RequestParam(value = ParamConsts.newKeywordId, required = false)Long newKeywordId){
        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return trialTaskService.giveUpOldMissionToGetNewMission(userId, deviceId, oldKeywordId, newKeywordId);
    }

    /**
     * 抢任务 (aso任务, 积分墙任务)
     *
     * @param request
     * @param missionType
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/task2")
    @ResponseBody
    public String task2(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.missionType, required = true)int missionType,
                        @RequestParam(value = ParamConsts.missionId, required = true)Long missionId) {
        return trialTaskService.getMission2(request, missionType, missionId);
    }

    /**
     * 放弃旧任务 抢新任务 (aso任务, 积分墙任务)
     *
     * @param request
     * @param oldMissionType
     * @param oldMissionId
     * @param newMissionType
     * @param newMissionId
     * @return
     */
    @RequestMapping(value = "/giveUp2")
    @ResponseBody
    public String giveUp2(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.oldMissionType, required = true)int oldMissionType,
                          @RequestParam(value = ParamConsts.oldMissionId, required = true)Long oldMissionId,
                          @RequestParam(value = ParamConsts.newMissionType, required = true)int newMissionType,
                          @RequestParam(value = ParamConsts.newMissionId, required = true)Long newMissionId){
        return trialTaskService.giveUpOldMissionToGetNewMission2(request, oldMissionType, oldMissionId, newMissionType, newMissionId);
    }

    /**
     * 完成试玩任务 (积分墙) 领取奖励
     *
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/finish")
    @ResponseBody
    public String finish(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.missionId, required = true)Long missionId){
        return trialTaskService.finish(request, missionId);
    }

}
