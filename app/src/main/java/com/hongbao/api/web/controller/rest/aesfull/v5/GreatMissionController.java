package com.hongbao.api.web.controller.rest.aesfull.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.MissionInfo;
import com.hongbao.api.service.GreatMissionService;
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
 * Created by Summer on 16/12/10.
 */
@RequestMapping(value = "/rest/a/f/great", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class GreatMissionController {

    @Autowired
    private GreatMissionService greatMissionService;

    /**
     * 高额任务列表
     * 翻页
     *
     * @param request
     * @param missionType 0-全部  1-进行中  2-审核中  3-已审核
     * @param missionId 默认传0
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.missionType, required = true) int missionType,
                       @RequestParam(value = ParamConsts.missionId, required = true) long missionId) {
        Long mission_id = null;
        if(missionId > 0) {
            mission_id = missionId;
        }

        Integer mission_type = null;
        if(missionType > 0) {
            mission_type = missionType;
        }

        List<MissionInfo> greatList = greatMissionService.myMissionList(request, mission_type, mission_id);
        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("greatList", greatList);
        return JsonUtil.buildSuccessDataJson(dataMap);
    }



}
