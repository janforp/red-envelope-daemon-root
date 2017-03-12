package com.hongbao.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hongbao.api.config.Config;
import com.hongbao.api.consts.BaseConsts;
import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.MsgConsts;
import com.hongbao.api.model.vo.JsonInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class JsonUtil {

    public static String toJSONString(Object obj) {
        JSON.DEFFAULT_DATE_FORMAT = BaseConsts.FMT_yyyy_MM_dd_HH_mm_ss;
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 构建成功JSON对象，并装载data，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=200<p/>
     * 并设置JsonInfo.data=data<p/>
     * 并设置JsonInfo.msg=null<p/>
     */
    public static String buildSuccessDataJson(String key, Object value) {
        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put(key, value);
        return buildSuccessDataJson(dataMap);
    }

    /**
     * 构建成功JSON对象，并装载data，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=200<p/>
     * 并设置JsonInfo.data=data<p/>
     * 并设置JsonInfo.msg=null<p/>
     */
    public static String buildSuccessDataJson(Map<String, Object> dataMap) {
        return toJSONString(JsonInfo.build(BaseConsts.JSON_INFO_SUCCESS_CODE, dataMap, null));
    }

    /**
     * 构建成功JSON对象，并装载data，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=200<p/>
     * 并设置JsonInfo.data=data<p/>
     * 并设置JsonInfo.msg=msg<p/>
     */
    public static String buildSuccessDataJson(Map<String, Object> dataMap, String msg) {
        return toJSONString(JsonInfo.build(BaseConsts.JSON_INFO_SUCCESS_CODE, dataMap, msg));
    }

    /**
     * 构建成功消息JSON对象，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=200<p/>
     * 并设置JsonInfo.data=null<p/>
     * 并设置JsonInfo.msg=msg<p/>
     */
    public static String buildSuccessMsgJson(String msg) {
        return toJSONString(JsonInfo.build(BaseConsts.JSON_INFO_SUCCESS_CODE, null, msg));
    }

    /**
     * 构建成功消息JSON对象，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=200<p/>
     * 并设置JsonInfo.data=null<p/>
     * 并设置JsonInfo.msg=null<p/>
     */
    public static String buildSuccessJson() {
        return JsonConsts.SUCCESS_default;
    }

    /**
     * 构建错误消息JSON对象，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=0<p/>
     * 并设置JsonInfo.data=null<p/>
     * 并设置JsonInfo.msg=null<p/>
     */
    public static String buildErrorJson(String msg) {
        return toJSONString(JsonInfo.build(BaseConsts.JSON_INFO_ERROR_CODE_DEFAULT, null, msg));
    }

    /**
     * 构建错误消息JSON对象，并指明特殊错误代码，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=code<p/>
     * 并设置JsonInfo.data=null<p/>
     * 并设置JsonInfo.msg=msg<p/>
     */
    public static String buildErrorJson(int code, String msg) {
        if (code == BaseConsts.JSON_INFO_SUCCESS_CODE) {
            code = BaseConsts.JSON_INFO_ERROR_CODE_DEFAULT;
        }
        return toJSONString(JsonInfo.build(code, null, msg));
    }


    /**
     * 构建错误消息JSON对象，并指明特殊错误代码，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=0<p/>
     * 并设置JsonInfo.data=data<p/>
     * 并设置JsonInfo.msg=msg<p/>
     */
    public static String buildErrorJson(Map<String, Object> dataMap, String msg) {
        return toJSONString(JsonInfo.build(BaseConsts.JSON_INFO_ERROR_CODE_DEFAULT, dataMap, msg));
    }

    /**
     * 构建错误消息JSON对象（发生异常专用），JsonInfo并序列化<p/>
     * 并设置JsonInfo.data=null<p/>
     * 并设置JsonInfo.code=500<p/>
     * 并设置JsonInfo.msg=MsgConsts.SYSTEM_ERROR<p/>
     */
    public static String buildExceptionJson(Throwable exception) {
        String msg = null;
        if (Config.isDebug()) {
            msg = exception.toString();
        } else {
            msg = MsgConsts.SYSTEM_ERROR;
        }
        return toJSONString(JsonInfo.build(BaseConsts.JSON_INFO_ERROR_CODE_EXCEPTION, null, msg));
    }

    /**
     * 构建错误消息JSON对象，JsonInfo并序列化<p/>
     * 并设置JsonInfo.code=500<p/>
     * 并设置JsonInfo.data=null<p/>
     * 并设置JsonInfo.msg=msg<p/>
     */
    public static String buildExceptionJson(String msg) {
        return toJSONString(JsonInfo.build(BaseConsts.JSON_INFO_ERROR_CODE_EXCEPTION, null, msg));
    }
}
