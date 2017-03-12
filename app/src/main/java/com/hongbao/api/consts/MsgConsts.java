package com.hongbao.api.consts;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class MsgConsts {
    public static final String SYSTEM_ERROR = "服务繁忙，请稍后重试";
    public static final String DATA_ERROR = "数据错误，请稍后重试";
    public static final String ERROR_404 = "Not Found.";
    public static final String ERROR_400 = "Bad Request.";

    // 服务器加密响应内容失败
    public static final String UNABLE_ENCRYPT_RESPONSE_ERROR = "无法处理你的请求，加密失败";

    public static final String TIMESTAMP_ERROR = "无法处理你的请求，请确认你的系统时间是否正确";
    public static final String REQID_ERROR = "无法处理你的请求，请确认你的reqId是否正确";
    public static final String USER_NOT_EXIST = "账号不存在";
    public static final String USER_PASSWORD_CHANGE = "请重新登录";
    public static final String PARAMETERS_ERROR = "请检查信息是否填写完整";
    public static final String PASSWORD_ILLEGAL = "请按要求填写密码";
    public static final String USER_PERMANENT_BLOCKED = "账号被禁用，请联系工作人员";
    public static final String LOGINNAME_PASSWORD_ERROR = "用户名或密码错误";
    public static final String PHONE_NUMBER_ILLEGAL = "请填写正确的手机号";
    public static final String PHONE_NUMBER_ALREADY_REGISTER = "该号码已被注册，请直接登录";
    public static final String PHONE_NUMBER_ALREADY_USED = "该号码已被其他帐号绑定";
    public static final String PHONE_NUMBER_NOT_REGISTER = "该号码未注册，请检查";
    public static final String VERIFY_SMS_SEND_SUCCESS = "短信验证码已发送，请注意查收";
    public static final String VERIFY_SMS_SEND_FAIL = "短信验证码发送失败，请重试";
    public static final String VERIFY_CODE_EXPIRE = "短信验证码已过期，请重新获取";
    public static final String VERIFY_CODE_WRONG = "短信验证码错误，请重新获取";
    public static final String ALREADY_BAND_PHONE = "帐号已经绑定过手机号";
    public static final String PARAMETERS_LENGTH_ERROR = "昵称过长";


    public static final String RESET_PWD_SUCCESS = "密码重置成功,请重新登录";
    public static final String RESET_PWD_ERROR = "密码重置失败,请稍后再试";
    public static final String INPUT_EMPTY_PHONE = "请输入手机号码";

    public static final String CAPTCHA_CODE_EXPIRE = "图形验证码已过期，请重新获取";
    public static final String CAPTCHA_CODE_WRONG = "图形验证码错误，请重新输入";

    // 仅在测试环境使用
    public static final String SUCCESS_TEST_VERIFY_SMS_SEND = "验证码：{0}";

}
