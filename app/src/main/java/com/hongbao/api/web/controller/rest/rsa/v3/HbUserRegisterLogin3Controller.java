package com.hongbao.api.web.controller.rest.rsa.v3;

import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.user.HbUserRegisterLoginService;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Summer on 16/10/25.
 */
@RequestMapping(value = "/rest/r/user3", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbUserRegisterLogin3Controller {

    @Autowired
    private HbUserRegisterLoginService hbUserRegisterLoginService;

    /**
     * 获取短信验证码
     *
     * @param request
     * @param cellphoneStr
     * @return
     */
    @RequestMapping(value = "/sendSms")
    @ResponseBody
    public String sendSms(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr,
                          @RequestParam(value = ParamConsts.appName, required = true) String appName) {

        String cellphone = CommonMethod.validateAndFixedTelephone(cellphoneStr);
        if (cellphone == null) { // 手机号未通过验证
            return JsonConsts.ERROR_phone_number_illegal;
        }

        // 发送短信验证码
        return hbUserRegisterLoginService.sendLoginCode(cellphone, appName);

    }


}
