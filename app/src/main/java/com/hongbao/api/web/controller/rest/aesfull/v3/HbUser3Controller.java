package com.hongbao.api.web.controller.rest.aesfull.v3;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReScoreDetail;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.dto.MyMissionInfo;
import com.hongbao.api.model.dto.ReUserMoneyInfo;
import com.hongbao.api.model.dto.ReUserScoreInfo;
import com.hongbao.api.service.HbRecommendService;
import com.hongbao.api.service.user.HbUserService;
import com.hongbao.api.service.user.UserCacheService;
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
@RequestMapping(value = "/rest/a/f/user3", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbUser3Controller {

    @Autowired
    private HbUserService hbUserService;
    @Autowired
    private HbRecommendService hbRecommendService;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 查询资金明细 下拉刷新
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMoney")
    @ResponseBody
    public String getMoney(HttpServletRequest request) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        UserInfo userInfo = userCacheService.getUser(userId);

        // 资金明细
        ReUserMoneyInfo moneyInfo = new ReUserMoneyInfo();
        moneyInfo.setTodayMoney(userInfo.getTodayMoney());
        moneyInfo.setTotalMoney(userInfo.getUserMoney());

        // 资金明细列表
        List<ReAccountDetail> list = hbUserService.getAccountDetailList(userId, null);

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("moneyInfo", moneyInfo);
        dataMap.put("moneyList", list);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }


    /**
     * 查询资金明细 分页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMoneyPaging")
    @ResponseBody
    public String getMoneyPaging(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.id, required = true) Long id) {

        if(id == 0) {
            id = null;
        }

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        List<ReAccountDetail> list = hbUserService.getAccountDetailList(userId, id);

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("moneyList", list);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }


    /**
     * 查询金币明细 下拉刷新
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getScore")
    @ResponseBody
    public String getScore(HttpServletRequest request) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        // 金币明细
        ReUserScoreInfo scoreInfo = hbUserService.getUserScore(userId);

        // 金币明细列表
        List<ReScoreDetail> list = hbUserService.getScoreDetailList(userId, null);

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("scoreInfo", scoreInfo);
        dataMap.put("scoreList", list);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }


    /**
     * 查询金币明细 分页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getScorePaging")
    @ResponseBody
    public String getScorePaging(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.id, required = true) Integer id) {

        if(id == 0) {
            id = null;
        }

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        // 金币明细列表
        List<ReScoreDetail> list = hbUserService.getScoreDetailList(userId, id);

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("scoreList", list);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }


    /**
     * 我的任务
     *
     * @param request
     * @param type  0-全部  1-进行中  2-审核中  3-已完成  对应状态值 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
     * @param id 默认传0
     * @return
     */
    @RequestMapping(value = "/mission")
    @ResponseBody
    public String record(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.type, required = false) Integer type,
                         @RequestParam(value = ParamConsts.id, required = true) Long id) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        if(id == 0) {
            id = null;
        }

        Integer taskStatus;

        if(type == 0) {
            // 全部
            taskStatus = null;
        }else if(type == 1) {
            // 进行中
            taskStatus = 0;
        }else if(type == 2) {
            // 审核中
            taskStatus = 1;
        }else {
            // 已完成
            taskStatus = 3;
        }

        List<MyMissionInfo> missionList = hbUserService.selectMissionList(userId, taskStatus, id);

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("missionList", missionList);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }

    /**
     * 提交审核任务
     *
     * @param request
     * @param id
     * @param requires
     * @param text
     * @param imgs
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public String submit(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.id, required = true) Long id,
                         @RequestParam(value = ParamConsts.requires, required = false) String requires,
                         @RequestParam(value = ParamConsts.text, required = false) String text,
                         @RequestParam(value = ParamConsts.imgs, required = false) String imgs) {

        return hbRecommendService.submitMission(id, requires, text, imgs);

    }


}
