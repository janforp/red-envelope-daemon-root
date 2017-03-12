package com.hongbao.api.consts;

import com.hongbao.api.config.Config;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class RequestConsts {
    /**
     * 请求相关的编码方式
     */
    public static final String CHARSET = Config.CHARSET;
    /**
     * Controller方法的@RequestMapping{produces = RequestConsts.CONTENT_TYPE_JSON}
     */
    public static final String CONTENT_TYPE_JSON = "application/json;charset=" + CHARSET;
    public static final String CONTENT_TYPE_TEXT = "text/plain;charset=" + CHARSET;
    public static final String CONTENT_TYPE_HTML = "text/html;charset=" + CHARSET;

    /**
     * 请求头中"userKey"的名称
     */
    public static final String HEADER_USER_KEY = "uk";
    /**
     * 请求头中"platform/平台"的名称；平台（0：IOS;1：安卓）
     */
    public static final String HEADER_PLATFORM = "pf";
    /**
     * 请求头中"App版本"的名称；
     */
    public static final String HEADER_VERSION = "v";
    /**
     * 请求头中"App版本号"的名称；
     */
    public static final String HEADER_VERSION_CODE = "b";
    /**
     * 请求头中"渠道"的名称；
     */
    public static final String HEADER_CHANNEL_NAME = "channel";
    /**
     * 请求头中"packageName"的名称；
     */
    public static final String HEADER_PACKAGE_NAME = "pgn";
    /**
     * 请求头中"网络类型"的名称；wifi：wifi网络；edge：非wifi,包含3G/2G；fail：网络断开连接；wwan：（2g或者3g）
     */
    public static final String HEADER_NETWORK_TYPE = "network";
    /**
     * 请求头中"设备名称"的名称；
     */
    public static final String HEADER_DEVICE_NAME = "dn";
    /**
     * 请求头中"screen-width"的名称；
     */
    public static final String HEADER_SCREEN_WIDTH = "screen-width";
    /**
     * 请求头中"screen-height"的名称；
     */
    public static final String HEADER_SCREEN_HEIGHT = "screen-height";
    /**
     * 请求头中"imsi"的名称；
     */
    public static final String HEADER_IMSI = "imsi";
    /**
     * 请求头中"imei"的名称；
     */
    public static final String HEADER_IMEI = "imei";
    /**
     * 请求头中"sim"的名称；
     */
    public static final String HEADER_SIM_NUM = "sim";
    /**
     * 请求头中"mn"的名称；
     */
    public static final String HEADER_MOBILE_NUM = "mn";


    /**
     * 系统参数中"时间戳"参数名称
     */
    public static final String SYSTEM_PARAM_TIMESTAMP = "t";
    /**
     * 系统参数中"设备ID"参数名称
     */
    public static final String SYSTEM_PARAM_DEVICE_ID = "d";
    /**
     * 系统参数中"用户ID"参数名称
     */
    public static final String SYSTEM_PARAM_USER_ID = "u";
    /**
     * 系统参数"aesKey"参数名称；AES秘钥（由客户端产生，只用于本次请求的响应加密）
     */
    public static final String SYSTEM_PARAM_AES_KEY = "aesKey";
    /**
     * 系统参数"reqId"参数名称；reqId
     */
    public static final String SYSTEM_PARAM_REQ_ID = "reqId";




    /**
     * Request属性范围中"userId"的名称
     */
    public static final String ATTR_USER_ID = "USER_ID";
    /**
     * Request属性范围中"userType"/用户类型 的名称
     */
    public static final String ATTR_USER_TYPE = "USER_TYPE";
    /**
     * Request属性范围中"userKey"的名称
     */
    public static final String ATTR_USER_KEY = "CACHE_USER_KEY";
    /**
     * Request属性范围中"设备名称"的名称
     */
    public static final String ATTR_DEVICE_NAME = "DEVICE_NAME";
    /**
     * Request属性范围中"设备ID"的名称
     */
    public static final String ATTR_DEVICE_ID = "DEVICE_ID";
    /**
     * Request属性范围中"CLIENT_TEMP_KEY"的名称，客户端产生的用于本次请求响应内容的加密的秘钥
     */
    public static final String ATTR_CLIENT_TEMP_KEY = "CLIENT_TEMP_KEY";
    /**
     * Request属性范围中"PLATFORM"的名称，意义：手机平台，（0：IOS;1：安卓）
     */
    public static final String ATTR_PLATFORM = "PLATFORM";
    /**
     * Request属性范围中"VERSION"的名称，意义：对应的版本号
     */
    public static final String ATTR_VERSION = "VERSION";
    /**
     * Request属性范围中"networkType"的名称，客户端网络类型
     */
    public static final String ATTR_NETWORK_TYPE = "NETWORK_TYPE";
    /**
     * Request属性范围中"设备屏幕宽度"的名称
     */
    public static final String ATTR_SCREEN_WIDTH = "SCREEN_WIDTH";
    /**
     * Request属性范围中"设备屏幕高度"的名称
     */
    public static final String ATTR_SCREEN_HEIGHT = "SCREEN_HEIGHT";
    /**
     * Request属性范围中"REQUEST_TYPE"的名称，意义：该请求的请求数据采用的数据方式；0：明文；1：AES加密；2：RSA加密
     */
    public static final String ATTR_REQUEST_TYPE = "REQUEST_TYPE";
    /**
     * Request属性范围中"RESPONSE_DATA_TYPE"的名称，意义：该请求的响应数据应当采用的数据方式；0：明文；1：AES加密；2：RSA加密
     * <p/>
     * 注：如果发生UnableEncryptResponseException异常，无论应当采用何种方式都采用原文方式响应，并把响应头中的data-type设置为0
     */
    public static final String ATTR_RESPONSE_DATA_TYPE = "RESPONSE_DATA_TYPE";
    /**
     * Request属性范围中"imsi"的名称；
     */
    public static final String ATTR_IMSI = "USER_IMSI";
    /**
     * Request属性范围中"imei"的名称；
     */
    public static final String ATTR_IMEI = "USER_IMEI";
    /**
     * Request属性范围中"sim"的名称；
     */
    public static final String ATTR_SIM_NUM = "SIM_NUM";
    /**
     * Request属性范围中"mn"的名称；
     */
    public static final String ATTR_MOBILE_NUM = "MOBILE_NUM";



    /**
     * 响应头中"etype"的名称：响应头中如果没有此属性表明是原文响应；0：明文；1：AES加密；2：RSA加密
     */
    public static final String RESPONSE_HEADER_RESPONSE_DATA_TYPE = "data-type";
    /**
     * 响应头中"data-type"和request属性"REQUEST_DATA_TYPE"的对应的值：定义数据方式为明文
     */
    public static final String VALUE_DATA_TYPE_PUB = "0";
    /**
     * 响应头中"data-type"和request属性"REQUEST_DATA_TYPE"的对应的值：定义数据方式为AES加密
     */
    public static final String VALUE_DATA_TYPE_AES = "1";
    /**
     * 请求类型是：全明文类型请求
     */
    public static final Integer VALUE_REQUEST_TYPE_PUB = 0;
    /**
     * 请求类型是：使用userSecret作为密钥AES双向加密请求
     */
    public static final Integer VALUE_REQUEST_TYPE_AES_USER_SECRET_FULL = 1;
    /**
     * 请求类型是：使用userSecret作为密钥AES单向加密请求（请求加密，响应不加密）
     */
    public static final Integer VALUE_REQUEST_TYPE_AES_USER_SECRET_SEMI = 2;
    /**
     * 请求类型是：RSA+AES加密请求（AES密钥是临时密钥）
     */
    public static final Integer VALUE_REQUEST_TYPE_RSA_AES_TEMP_KEY = 3;
    /**
     * 只有当响应数据为密文时，采用这个key封装<p/>
     * requestBody用这个参数名来包装加密数据<p/>
     * 同时全局响应的加密数据，需要做成一个JSON对象:{data:"密文数据"}
     * <p/>
     * 注意：这个值一定要和EncryptDataDto.data的属性名相同
     */
    public static final String GLOBAL_BUSINESS_PARAMETER_DATA_NAME = "data";
    /**
     * 系统参数名（用来封装系统参数密文数据）
     */
    public static final String GLOBAL_SYSTEM_PARAMETER_NAME = "s";
}
