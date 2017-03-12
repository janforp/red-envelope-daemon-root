package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/14.
 */
public class YMInfo implements Serializable {

    // 状态 0-不跳转;1-跳转
    private int status;
    // 内容
    private String content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
