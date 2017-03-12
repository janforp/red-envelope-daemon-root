package com.hongbao.api.service;

import com.alibaba.fastjson.JSON;
import com.hongbao.api.consts.BaseConsts;
import com.hongbao.api.model.vo.JuheMsgBack;
import com.hongbao.api.util.sms.JuheMessage;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    /**
     * 发送登录验证码
     *
     * @param cellphone 目标手机号
     * @param code      验证码
     */
    public int sendLoginCode(String cellphone, String code, String appName) {
        if (cellphone == null || code == null) {
            return 1;
        }
        String msgBackStr = JuheMessage.sendMsg(code, cellphone, BaseConsts.SMS_LOGIN_TPL_ID, appName);
        JuheMsgBack msgBack = JSON.parseObject(msgBackStr, JuheMsgBack.class);
        return msgBack.getError_code();
    }


    /**
     * 发送注册验证码
     *
     * @param cellphone 目标手机号
     * @param code      验证码
     */
    public int sendRegisterCode(String cellphone, String code, String appName) {
        if (cellphone == null || code == null) {
            return 1;
        }
        String msgBackStr = JuheMessage.sendMsg(code, cellphone, BaseConsts.SMS_REGISTER_TPL_ID, appName);
        JuheMsgBack msgBack = JSON.parseObject(msgBackStr, JuheMsgBack.class);
        return msgBack.getError_code();
    }


    /**
     * 发送重置密码验证码
     *
     * @param cellphone 目标手机号
     * @param code      验证码
     */
    public int sendResetCode(String cellphone, String code, String appName) {
        if (cellphone == null || code == null) {
            return 1;
        }
        String msgBackStr = JuheMessage.sendMsg(code, cellphone, BaseConsts.SMS_RESET_TPL_ID, appName);
        JuheMsgBack msgBack = JSON.parseObject(msgBackStr, JuheMsgBack.class);
        return msgBack.getError_code();
    }

}
