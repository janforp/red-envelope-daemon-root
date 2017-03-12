package com.hongbao.api.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/16.
 * 口令红包玩法介绍弹框的数据
 */
public class PasswordRedIntroduction implements Serializable {

    // 描述
    private String desc;
    // 群号
    private String qq;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
