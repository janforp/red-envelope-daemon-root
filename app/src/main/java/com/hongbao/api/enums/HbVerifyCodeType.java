package com.hongbao.api.enums;

public enum HbVerifyCodeType {
    // 注册
    register(0),
    // 找回密码
    forget_password(1),
    //绑定手机号
    band_phone(2);
    // 值
    public final Integer val;

    private HbVerifyCodeType(Integer val) {
        this.val = val;
    }
}
