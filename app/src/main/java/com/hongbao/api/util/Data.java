package com.hongbao.api.util;

import java.io.Serializable;

/**
 * Created by wujie5 on 16/11/17.
 */
public class Data implements Serializable {

    private Integer code;
    private String  msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
