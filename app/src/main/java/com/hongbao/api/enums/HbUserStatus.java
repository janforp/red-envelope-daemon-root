package com.hongbao.api.enums;

/**
 * Created by wuqiang on 15-8-11.
 *
 * @author wuqiang
 */
public enum HbUserStatus {
    /**
     * 永久封号：0
     */
    permanent_blocked(0),
    /**
     * 正常：1
     */
    normal(1);

    // 值
    public final Integer val;

    private HbUserStatus(Integer val) {
        this.val = val;
    }
}
