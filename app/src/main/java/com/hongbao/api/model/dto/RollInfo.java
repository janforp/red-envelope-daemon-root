package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 2016/10/8.
 */
public class RollInfo implements Serializable {

    // 昵称
    private String nickname;
    // 金额
    private BigDecimal money;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

}
