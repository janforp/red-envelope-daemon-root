package com.hongbao.api.enums;

/**
 * Created by wuqiang on 15-8-11.
 *
 * @author wuqiang
 */
public enum HbUserDevicePlatform {
    /**
     * iOS:0
     */
    iOS(0),
    /**
     * android:1
     */
    android(1);

    // 值
    public final Integer val;

    private HbUserDevicePlatform(Integer val) {
        this.val = val;
    }
}