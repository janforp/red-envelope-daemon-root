package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 2016/12/24.
 */
public class RedDetailInfo implements Serializable {

    // 用户头像
    private String portrait;
    // 用户昵称
    private String nickname;
    // 领取时间
    private String receiveTime;
    // 红包金额
    private BigDecimal redMoney;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public BigDecimal getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(BigDecimal redMoney) {
        this.redMoney = redMoney;
    }
}
