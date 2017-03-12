package com.hongbao.api.web.controller.rest.rsa;

import com.hongbao.api.consts.*;
import com.hongbao.api.model.dto.ServiceResult;
import com.hongbao.api.service.HbCaptchaService;
import com.hongbao.api.service.user.HbUserRegisterLoginService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.captcha.CaptchaUtil;
import org.craigq.common.logger.LogMgr;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Summer on 16/8/10.
 */
@RequestMapping(value = "/rest/r/user", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class HbUserRegisterLoginController {

    @Autowired
    private HbUserRegisterLoginService hbUserRegisterLoginService;
    @Autowired
    private HbCaptchaService hbCaptchaService;


    /**
     * 获取图形验证码
     * @param request
     * @param cellphoneStr
     * @return
     */
    @RequestMapping(value = "/captcha")
    @ResponseBody
    public String captcha(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr) {

        String cellphone = CommonMethod.validateAndFixedTelephone(cellphoneStr);
        if (cellphone == null) {
            // 手机号未通过验证
            return JsonConsts.ERROR_phone_number_illegal;
        }

        Map<String, Object> dataResult = new HashMap<>(1);

        ByteArrayOutputStream imageOutputStream = null;

        try {
            imageOutputStream = new ByteArrayOutputStream();
            // 验证码
            String textCaptcha = EncoderHelper.getChallangeAndWriteImage(CaptchaUtil.getRandomCaptchaService(), "png", imageOutputStream);
            byte[] captchaChallengeAsJpeg = imageOutputStream.toByteArray();

            hbCaptchaService.setCaptcha(CacheConsts.CACHE_CAPTCHA+cellphone, textCaptcha, 300L);

            dataResult.put("captcha", captchaChallengeAsJpeg);

        } catch (IOException e) {
            LogMgr.getLogger().error("generate captcha image error: {}", e);
        } finally {
            if (imageOutputStream != null) {
                try {
                    imageOutputStream.close();
                } catch (IOException e) {
                    LogMgr.getLogger().error(e);
                }
            }
        }

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 验证图形验证码并发送短信
     * @param request
     * @param cellphoneStr
     * @param code
     * @return
     */
    @RequestMapping(value = "/sendSms")
    @ResponseBody
    public String sendSms(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr,
                          @RequestParam(value = ParamConsts.appName, required = false) String appName,
                          @RequestParam(value = ParamConsts.code, required = false) String code) {

        String cellphone = CommonMethod.validateAndFixedTelephone(cellphoneStr);
        if (cellphone == null) { // 手机号未通过验证
            return JsonConsts.ERROR_phone_number_illegal;
        }

        // 验证通过,发送短信验证码
        return hbUserRegisterLoginService.sendLoginCode(cellphone, appName);

    }


    /**
     * 注册登录
     * @param request
     * @param response
     * @param cellphoneStr
     * @param code
     * @return
     */
    @RequestMapping(value = "/registerLogin")
    @ResponseBody
    public String registerLogin(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = ParamConsts.cellphone, required = true) String cellphoneStr,
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

        ServiceResult serviceResult = hbUserRegisterLoginService.registerLogin(request, response, cellphone, code,
                invitationCode, address, longitude, latitude, imsi, imei);
        serviceResult.invokeCallbackGetJson(); // 执行回调代码
        return serviceResult.getJson();

    }


}
