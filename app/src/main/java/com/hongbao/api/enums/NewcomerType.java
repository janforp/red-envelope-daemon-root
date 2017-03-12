package com.hongbao.api.enums;

/**
 * Created by Summer on 2016/11/17.
 */
public enum NewcomerType {

    // 新手见面红包
    newcomer(0),

    // 完善个人信息
    userInfo(1),

    // 完成一次试玩任务
    trial(2),

    // 完成一次关注任务
    attention(3),

    // 完成一次分享任务
    share(4),

    // 完成一次高额任务
    great(5);

    // 值
    public final Integer val;

    private NewcomerType(Integer val) {
        this.val = val;
    }

}
