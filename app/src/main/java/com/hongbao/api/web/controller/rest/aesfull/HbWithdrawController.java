package com.hongbao.api.web.controller.rest.aesfull;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.dto.ReUserMoneyAndScore;
import com.hongbao.api.model.dto.WithdrawListInfo;
import com.hongbao.api.service.HbWithdrawService;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/8/19.
 */
@RequestMapping(value = "/rest/a/f/withdraw", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbWithdrawController {

    @Autowired
    private HbWithdrawService hbWithdrawService;
    @Autowired
    private UserCacheService userCacheService;

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

        UserInfo userInfo = userCacheService.getUser(userId);

        // 用户余额 金币
        ReUserMoneyAndScore moneyInfo = new ReUserMoneyAndScore();
        moneyInfo.setUserMoney(userInfo.getUserMoney());
        moneyInfo.setUserScore(userInfo.getUserScore());

        // 提现页面列表
        List<WithdrawListInfo> withdrawList = hbWithdrawService.getWithdrawList(platform, version);

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

        return hbWithdrawService.getWithdrawSortDetail(withdrawId, userId);

    }

    /**
     * 申请提现
     * @param request
     * @return
     */
    @RequestMapping(value = "/apply")
    @ResponseBody
    public String apply(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.type, required = true) String withdrawType,
                        @RequestParam(value = ParamConsts.account, required = true) String account,
                        @RequestParam(value = ParamConsts.applyMoney, required = true) BigDecimal applyMoney,
                        @RequestParam(value = ParamConsts.withdrawMoney, required = true) BigDecimal withdrawMoney,
                        @RequestParam(value = ParamConsts.realName, required = false) String realName) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbWithdrawService.apply(userId, withdrawType, account, applyMoney, withdrawMoney, realName);

    }

}
