package com.hongbao.api.web.controller.rest.aesfull.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.vo.IosFinishVo;
import com.hongbao.api.model.vo.IosMissionVo;
import com.hongbao.api.service.IosMissionService;
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
 * Created by Summer on 2017/1/10.
 */
@RequestMapping(value = "/rest/a/f/mission", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class IosMissionController {

    @Autowired
    private IosMissionService iosMissionService;

    /**
     * ios任务详情
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
            IosMissionVo detail = iosMissionService.detail(userId, missionId);
            dataResult.put("detail", detail);
        }else {
            IosMissionVo detail = iosMissionService.endDetail(userId, missionId);
            dataResult.put("detail", detail);
        }
        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 提交审核
     *
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public String submit(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.missionId, required = true) Long missionId,
                         @RequestParam(value = ParamConsts.stepId, required = true) int stepId,
                         @RequestParam(value = ParamConsts.requires, required = false) String requires,
                         @RequestParam(value = ParamConsts.imgs, required = false) String imgs) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return iosMissionService.submit(userId, missionId, stepId, requires, imgs);
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
        List<IosFinishVo> missionList = iosMissionService.selectMissionList(userId, time);
        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("missionList", missionList);
        return JsonUtil.buildSuccessDataJson(dataResult);
    }

}
