package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/11/19.
 */
public class MoneyAndCountInfo implements Serializable {

    // 金额
    private String money;
    // 个数
    private String count;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
