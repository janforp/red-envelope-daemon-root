package com.hongbao.api.web.controller.rest.aesfull.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.vo.AndroidFinishVo;
import com.hongbao.api.model.vo.AndroidIntegralVo;
import com.hongbao.api.service.AndroidIntegralService;
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
 * Created by Summer on 2017/1/5.
 */
@RequestMapping(value = "/rest/a/f/wall", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class AndroidIntegralController {

    @Autowired
    private AndroidIntegralService androidIntegralService;

    /**
     * 积分墙详情
     *
     * @param request
     * @param missionId
     * @param flag 标志 0-进行中 1-已结束
     * @return
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public String detail(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.missionId, required = true) Long missionId,
                         @RequestParam(value = ParamConsts.flag, required = true) int flag) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Map<String, Object> dataResult = new HashMap<>(1);
        if(flag == 0) { // 进行中
            AndroidIntegralVo detail = androidIntegralService.detail(userId, missionId);
            dataResult.put("detail", detail);
        }else {
            AndroidIntegralVo detail = androidIntegralService.endDetail(userId, missionId);
            dataResult.put("detail", detail);
        }
        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 下载完成 (记录当前状态, 表示任务正在进行中)
     *
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/down")
    @ResponseBody
    public String down(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.missionId, required = true) Long missionId) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return androidIntegralService.down(userId, missionId);
    }

    /**
     * 完成体验
     *
     * @param request
     * @param missionId
     * @param depthId
     * @return
     */
    @RequestMapping(value = "/finish")
    @ResponseBody
    public String finish(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.missionId, required = true) Long missionId,
                         @RequestParam(value = ParamConsts.depthId, required = true) int depthId,
                         @RequestParam(value = ParamConsts.appName, required = true) String appName) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return androidIntegralService.finish(userId, missionId, depthId, appName);
    }

    /**
     * 附属任务提交审核
     *
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public String submit(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.missionId, required = true) Long missionId,
                         @RequestParam(value = ParamConsts.missionNo, required = true) int missionNo,
                         @RequestParam(value = ParamConsts.requires, required = false) String requires,
                         @RequestParam(value = ParamConsts.imgs, required = false) String imgs) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return androidIntegralService.submit(userId, missionId, missionNo, requires, imgs);
    }

    /**
     * 任务完成列表
     *
     * @param request
     * @param time
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.time, required = false) String time) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        List<AndroidFinishVo> missionList = androidIntegralService.selectMissionList(userId, time);
        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("missionList", missionList);
        return JsonUtil.buildSuccessDataJson(dataResult);
    }


}
