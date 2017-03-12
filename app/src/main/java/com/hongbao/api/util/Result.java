package com.hongbao.api.util;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/17.
 * 返回结果
 */
public class Result implements Serializable {

    private Integer code;
    private Data data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

