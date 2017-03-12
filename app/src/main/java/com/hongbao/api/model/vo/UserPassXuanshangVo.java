package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 某个人完成了某个悬赏任务
 * 如：王小二完成任务，赚到0.10元
 */
public class UserPassXuanshangVo implements Serializable {

    private Long        xuanshagnId;
    private String      nickname;
    private BigDecimal money;

    public Long getXuanshagnId() {
        return xuanshagnId;
    }

    public void setXuanshagnId(Long xuanshagnId) {
        this.xuanshagnId = xuanshagnId;
    }

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
