package com.hongbao.api.web.controller.rest.rsa.v5;

import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.ServiceResult;
import com.hongbao.api.service.HbRegisterLoginService;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2017/1/4.
 */
@RequestMapping(value = "/rest/r/login", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class RegisterLoginController {

    @Autowired
    private HbRegisterLoginService hbRegisterLoginService;

    /**
     * 获取短信验证码
     *
     * @param request
     * @param cellphoneStr
     * @param appName
     * @param type 0-注册 1-重置密码
     * @return
     */
    @RequestMapping(value = "/sendSms")
    @ResponseBody
    public String sendSms(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr,
                          @RequestParam(value = ParamConsts.appName, required = true) String appName,
                          @RequestParam(value = ParamConsts.type, required = true) int type) {

        String cellphone = CommonMethod.validateAndFixedTelephone(cellphoneStr);
        if (cellphone == null) { // 手机号未通过验证
            return JsonConsts.ERROR_phone_number_illegal;
        }

        if(type == 0) { // 注册验证码
            return hbRegisterLoginService.sendRegisterCode(cellphone, appName);
        }else if(type == 1) { // 重置密码验证码
            return hbRegisterLoginService.sendResetCode(cellphone, appName);
        }

        return JsonConsts.ERROR_system_error;

    }

    /**
     * 注册
     *
     * @param request
     * @param cellphoneStr
     * @param password
     * @param code
     * @param invitationCode
     * @param address
     * @param longitude
     * @param latitude
     * @param imsi
     * @param imei
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr,
                           @RequestParam(value = ParamConsts.password, required = true) String password,
                           @RequestParam(value = ParamConsts.code, required = true) String code,
                           @RequestParam(value = ParamConsts.invitationCode, required = false) String invitationCode,
                           @RequestParam(value = ParamConsts.address, required = false) String address,
                           @RequestParam(value = ParamConsts.longitude, required = false) String longitude,
                           @RequestParam(value = ParamConsts.latitude, required = false) String latitude,
                           @RequestParam(value = ParamConsts.imsi, required = false) String imsi,
                           @RequestParam(value = ParamConsts.imei, required = false) String imei) {

        String cellphone = CommonMethod.validateAndFixedTelephone(cellphoneStr);
        if (cellphone == null) { // 手机号未通过验证
            return JsonConsts.ERROR_phone_number_illegal;
        }

        password = password.toLowerCase();
        if(!CommonMethod.validatePassword(password)) {
            return JsonConsts.ERROR_password_illegal;
        }


        ServiceResult serviceResult = hbRegisterLoginService.register(request, cellphone, password, code,
                invitationCode, address, longitude, latitude, imsi, imei);
        serviceResult.invokeCallbackGetJson(); // 执行回调代码
        return serviceResult.getJson();

    }

    /**
     * 登录
     *
     * @param request
     * @param cellphoneStr
     * @param password
     * @param address
     * @param longitude
     * @param latitude
     * @param imsi
     * @param imei
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr,
                        @RequestParam(value = ParamConsts.password, required = true) String password,
                        @RequestParam(value = ParamConsts.address, required = false) String address,
                        @RequestParam(value = ParamConsts.longitude, required = false) String longitude,
                        @RequestParam(value = ParamConsts.latitude, required = false) String latitude,
                        @RequestParam(value = ParamConsts.imsi, required = false) String imsi,
                        @RequestParam(value = ParamConsts.imei, required = false) String imei) {

        String cellphone = CommonMethod.validateAndFixedTelephone(cellphoneStr);
        if (cellphone == null) { // 手机号未通过验证
            return JsonConsts.ERROR_phone_number_illegal;
        }

        password = password.toLowerCase();
        if(!CommonMethod.validatePassword(password)) {
            return JsonConsts.ERROR_password_illegal;
        }

        ServiceResult serviceResult = hbRegisterLoginService.login(request, cellphone, password, address, longitude,
                latitude, imsi, imei);
        serviceResult.invokeCallbackGetJson(); // 执行回调代码
        return serviceResult.getJson();

    }


    /**
     * 重置密码
     *
     * @param request
     * @param cellphoneStr
     * @param password
     * @param code
     * @return
     */
    @RequestMapping(value = "/resetPwd")
    @ResponseBody
    public String resetPwd(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr,
                           @RequestParam(value = ParamConsts.password, required = true) String password,
                           @RequestParam(value = ParamConsts.code, required = true) String code) {

        String cellphone = CommonMethod.validateAndFixedTelephone(cellphoneStr);
        if (cellphone == null) { // 手机号未通过验证
            return JsonConsts.ERROR_phone_number_illegal;
        }

        password = password.toLowerCase();
        if(!CommonMethod.validatePassword(password)) {
            return JsonConsts.ERROR_password_illegal;
        }

        return hbRegisterLoginService.resetPwd(cellphone, password, code);

    }


}
