package com.hongbao.api.web.controller.rest.aesfull.v3;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.ReUserMoneyAndScore;
import com.hongbao.api.model.dto.WithdrawListInfo;
import com.hongbao.api.model.dto.WithdrawRecordInfo;
import com.hongbao.api.service.HbWithdrawService;
import com.hongbao.api.service.user.HbUserService;
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
 * Created by Summer on 16/10/25.
 */
@RequestMapping(value = "/rest/a/f/withdraw3", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbWithdraw3Controller {

    @Autowired
    private HbUserService hbUserService;
    @Autowired
    private HbWithdrawService hbWithdrawService;


    /**
     * 提现列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request) {

        // 平台 0-ios, 1-andriod
        int platform = (int) request.getAttribute(RequestConsts.ATTR_PLATFORM);
        // 版本
        String version = (String) request.getAttribute(RequestConsts.ATTR_VERSION);

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        // 用户余额积分
        ReUserMoneyAndScore moneyInfo = hbUserService.getUserMoneyAndScore(userId);
        // 提现页面列表
        List<WithdrawListInfo> withdrawList = hbWithdrawService.getWithdrawList3(platform, version);

        Map<String, Object> dataResult = new HashMap<>(2);
        dataResult.put("withdrawList", withdrawList);
        dataResult.put("moneyInfo", moneyInfo);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 提现详情
     * @param request
     * @param withdrawId
     * @return
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public String detail(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.id, required = true) int withdrawId) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbWithdrawService.getWithdrawSortDetail3(withdrawId, userId);

    }


    /**
     * 提现记录
     *
     * @param request
     * @param type 0-审核中 1-已完成 2-未通过 3-全部
     * @param id 默认传0
     * @return
     */
    @RequestMapping(value = "/record")
    @ResponseBody
    public String record(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.type, required = false) Integer type,
                         @RequestParam(value = ParamConsts.id, required = true) Long id) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        if(id == 0) {
            id = null;
        }

        if(type == 3) {
            type = null;
        }

        List<WithdrawRecordInfo> recordList = hbWithdrawService.getRecords(userId, type, id);

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("recordList", recordList);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }

}
