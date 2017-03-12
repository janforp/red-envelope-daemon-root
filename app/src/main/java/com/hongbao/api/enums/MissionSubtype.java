package com.hongbao.api.enums;

/**
 * Created by Summer on 16/9/19.
 */
public enum MissionSubtype {

    // 其他
    other(0),
    // 定时红包
    fix_red(1),
    // 签到
    sign(2),
    // 口令红包
    word_red(3),
    // 邀请好友
    invite_friend(4),
    // 新手任务
    newcomer(5),
    // 注册任务
    register(6),
    // 提现到支付宝
    zhifubao(7),
    // 提现到微信
    weixin(8),
    // 话费充值
    huafei(9),
    // 佣金
    commission(10),
    // 积分墙
    wall(11),
    // 文章阅读
    read(12),
    // 转发点击
    click(13),
    // 发红包
    send_red(14),
    // 抢红包
    get_red(15),
    // 发布悬赏任务
    send_reward(16),
    // 完成悬赏任务
    finish_reward(17),
    // 退还红包
    refund_red(18),
    // Android积分墙：激活奖励
    android_activity(19),
    // Android积分墙：留存任务
    android_keep(20),
    // Android积分墙：附加任务
    android_auxiliary(21),
    // ios高额任务
    ios_high_reward(22),
    // 赚钱领取奖励任务
    reward_mission(23),
    // 大转盘
    rotate(24);


    // 值
    public final Integer val;

    private MissionSubtype(Integer val) {
        this.val = val;
    }


}
