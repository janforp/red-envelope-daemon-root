package com.hongbao.api.web.controller.rest.aesfull;

import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReScoreDetail;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.dto.ReUserMoneyAndScore;
import com.hongbao.api.model.vo.AliyunOSSFederationTokenVo;
import com.hongbao.api.service.AliyunStsService;
import com.hongbao.api.service.user.HbUserService;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
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
 * Created by Summer on 16/8/16.
 */
@RequestMapping(value = "/rest/a/f/user", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbUserController {

    @Autowired
    private HbUserService hbUserService;
    @Autowired
    private AliyunStsService aliyunStsService;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 查询账户明细
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAccount")
    @ResponseBody
    public String getAccount(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.id, required = true) Long id) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        if(id == 0) {
            id = null;
        }

        List<ReAccountDetail> list = hbUserService.getAccountDetailList(userId, id);

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("accountList", list);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }

    /**
     * 查询金币明细
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getScore")
    @ResponseBody
    public String getScore(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.id, required = true) Integer id) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        if(id == 0) {
            id = null;
        }

        List<ReScoreDetail> list = hbUserService.getScoreDetailList(userId, id);

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("scoreList", list);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }

    /**
     * 查询余额 金币
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMoneyInfo")
    @ResponseBody
    public String getMoneyInfo(HttpServletRequest request) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        UserInfo userInfo = userCacheService.getUser(userId);

        // 用户余额 金币
        ReUserMoneyAndScore moneyInfo = new ReUserMoneyAndScore();
        moneyInfo.setUserMoney(userInfo.getUserMoney());
        moneyInfo.setUserScore(userInfo.getUserScore());

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("moneyInfo", moneyInfo);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }


    /**
     * 获取阿里云OSS访问Token
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getOssToken")
    @ResponseBody
    public String getOssToken(HttpServletRequest request) {

        String userId = String.valueOf(request.getAttribute(RequestConsts.ATTR_USER_ID));
        if (StringUtil.isEmpty(userId)) {
            return JsonConsts.ERROR_parameters;
        }

        AliyunOSSFederationTokenVo ossToken = aliyunStsService.getOssUploadToken(userId);
        if (StringUtil.isEmpty(ossToken)) {
            return JsonConsts.ERROR_system_error;
        }

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("ossToken", ossToken);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }

    /**
     * 修改头像
     * @param request
     * @param portrait
     * @return
     */
    @RequestMapping(value = "/changePortrait")
    @ResponseBody
    public String changePortrait(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.portrait, required = true) String portrait) {

        if (StringUtil.isEmpty(portrait)) {
            return JsonConsts.ERROR_parameters;
        }

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbUserService.changePortrait(userId, portrait);

    }

    /**
     * 修改昵称
     * @param request
     * @param nickname
     * @return
     */
    @RequestMapping(value = "/changeNickname")
    @ResponseBody
    public String changeNickname(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.nickname, required = true) String nickname) {

        if (StringUtil.isEmpty(nickname)) {
            return JsonConsts.ERROR_parameters;
        }

        if (nickname.length() > 15) {
            return JsonConsts.ERROR_length;
        }

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbUserService.changeNickname(userId, nickname);

    }

    /**
     * 修改姓名
     * @param request
     * @param realName
     * @return
     */
    @RequestMapping(value = "/changeRealName")
    @ResponseBody
    public String changeRealName(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.realName, required = true) String realName) {

        if (StringUtil.isEmpty(realName)) {
            return JsonConsts.ERROR_parameters;
        }

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbUserService.changeRealName(userId, realName);

    }

    /**
     * 修改性别
     * @param request
     * @param gender
     * @return
     */
    @RequestMapping(value = "/changeGender")
    @ResponseBody
    public String changeGender(HttpServletRequest request,
                               @RequestParam(value = ParamConsts.gender, required = true) int gender) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbUserService.changeGender(userId, gender);

    }

    /**
     * 修改职业
     * @param request
     * @param profession
     * @return
     */
    @RequestMapping(value = "/changeProfession")
    @ResponseBody
    public String changeProfession(HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.profession, required = true) String profession) {

        if (StringUtil.isEmpty(profession)) {
            return JsonConsts.ERROR_parameters;
        }

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbUserService.changeProfession(userId, profession);

    }

    /**
     * 修改地址
     * @param request
     * @param address
     * @return
     */
    @RequestMapping(value = "/changeAddress")
    @ResponseBody
    public String changeAddress(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.address, required = true) String address) {

        if (StringUtil.isEmpty(address)) {
            return JsonConsts.ERROR_parameters;
        }

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbUserService.changeAddress(userId, address);

    }


    /**
     * 存储友盟token
     * @param request
     * @param token
     * @return
     */
    @RequestMapping(value = "/uMengToken")
    @ResponseBody
    public String uMengToken(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.token, required = true) String token) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        int platform = (int) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        return hbUserService.saveToken(userId, platform, token);

    }


    /**
     * 退出登录
     * @param request
     * @param cacheSessionId
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.id, required = true) String cacheSessionId) {

        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return hbUserService.logout(userId, cacheSessionId);

    }


}
