package com.hongbao.api.web.controller.rest.aesfull.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.vo.RedDetailVo;
import com.hongbao.api.service.user.UserRedService;
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
import java.util.Map;

/**
 * Created by Summer on 2016/12/23.
 */
@RequestMapping(value = "/rest/a/f/red", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class UserRedController {

    @Autowired
    private UserRedService userRedService;

    /**
     * 用户发红包
     *
     * @param request
     * @param redNum        红包总数量
     * @param singleMoney   单个红包金额
     * @param redContent    文字描述
     * @return
     */
    @RequestMapping(value = "/send")
    @ResponseBody
    public String send(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.redNum, required = true) Integer redNum,
                       @RequestParam(value = ParamConsts.singleMoney, required = true) String singleMoney,
                       @RequestParam(value = ParamConsts.redContent, required = true) String redContent) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return userRedService.send(userId, redNum, new BigDecimal(singleMoney), redContent);
    }


    /**
     * 点击红包
     *
     * @param request
     * @param redId
     * @return
     */
    @RequestMapping(value = "/click")
    @ResponseBody
    public String click(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.redId, required = true) long redId) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return userRedService.click(redId, userId);
    }


    /**
     * 领取红包
     *
     * @param request
     * @param redId
     * @return
     */
    @RequestMapping(value = "/receive")
    @ResponseBody
    public String receive(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.redId, required = true) long redId) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return userRedService.receive(redId, userId);
    }


    /**
     * 领取红包详情
     *
     * @param request
     * @param redId
     * @return
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public String detail(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.redId, required = true) long redId) {
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        RedDetailVo detail = userRedService.detail(redId, userId);
        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("detail", detail);
        return JsonUtil.buildSuccessDataJson(dataMap);
    }


}



