package com.hongbao.api.enums;

public enum VerifyCodeStatus {
    /**
     * 0：未验证
     */
    not_verify(0),
    /**
     * 1：验证通过
     */
    verify_success(1),
    /**
     * 2：验证失败
     */
    verify_fail(2);

    // 值
    public final Integer val;

    private VerifyCodeStatus(Integer val) {
        this.val = val;
    }
}
