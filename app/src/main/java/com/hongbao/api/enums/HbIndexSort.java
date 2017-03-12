package com.hongbao.api.enums;

/**
 * Created by Summer on 16/9/19.
 */
public enum HbIndexSort {

    // 兑换码红包
    code_red(1),
    // 微信现金红包
    wx_red(2),
    // 任务
    mission_red(3);

    // 值
    public final Integer val;

    private HbIndexSort(Integer val) {
        this.val = val;
    }


}
