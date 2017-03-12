package com.hongbao.api.model.vo;

import java.io.Serializable;

/**
 * Created by wuqiang on 15-8-6.
 * <p/>
 * JSON响应对象，均由此类封装
 *
 * @author wuqiang
 */
public class JsonInfo implements Serializable {

    // code=200：表示请求成功；其他值为失败
    private Integer code;
    // 失败或成功消息
    private String msg;
    // 返回的数据
    private Object data;

    private JsonInfo() {
    }

    public static JsonInfo build(int code, Object data, String msg) {
        JsonInfo json = new JsonInfo();
        json.code = code;
        json.data = data;
        json.msg = msg;
        return json;
    }


    /**
     * code=200：表示请求成功；其他值为失败
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
