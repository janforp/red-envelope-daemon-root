package com.hongbao.api.consts;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class BaseConsts {

    public static final String FMT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String FMT_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String FMT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_MM_dd_HH_mm = "MM-dd HH:mm";
    public static final String FMT_yyyy_MM = "yyyy-MM";
    public static final String FMT_MM_dd = "MM-dd";

    // 返回的JSON，code==200表示成功
    public static final int JSON_INFO_SUCCESS_CODE = 200;
    // 返回的JSON，code==302，一个特殊的code，仅仅可能会在loginByOpen接口中返回（当这个这个第三方账号第一次使用时，需要选择身份后进行注册（访问registerByOpen接口））
    public static final int JSON_INFO_ERROR_CODE_NEED_REGISTER_BY_OPEN = 302;
    // 错误JSON的默认的code值（最常用的值）
    public static final int JSON_INFO_ERROR_CODE_DEFAULT = 0;
    // 错误JSON的code值：非法请求时
    public static final int JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST = 400;
    // 错误JSON的code值：发生异常时
    public static final int JSON_INFO_ERROR_CODE_EXCEPTION = 500;
    // 错误JSON的code值：无法确认用户正常登录了（用户不存在/用户被封号/密钥改变/用户请求被伪造等错误），客户端检查到这种状态，应该主动退出登录
    public static final int JSON_INFO_ERROR_CODE_UNAUTHORIZED = 401;
    // 时间戳误差过大，服务器拒绝请求
    public static final int JSON_INFO_ERROR_CODE_TIMESTAMP_ERROR = 402;
    // 数据库所支持的IP最大长度
    public static final int SUPPORT_MAX_CLIENT_IP_LENGTH = 64;

    // 聚合API 登录 短信模板ID
    public static final String SMS_LOGIN_TPL_ID = "17512";
    // 聚合API 注册 短信模板ID
    public static final String SMS_REGISTER_TPL_ID = "26594";
    // 聚合API 重置密码 短信模板ID
    public static final String SMS_RESET_TPL_ID = "26635";

    //每页条数
    public static final int PAGE_SIZE = 20;

}
