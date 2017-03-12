package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 2016/10/25.
 */
public class ReUserMoneyInfo implements Serializable {

    // 今日
    private BigDecimal todayMoney;
    // 总共
    private BigDecimal totalMoney;

    public BigDecimal getTodayMoney() {
        return todayMoney;
    }

    public void setTodayMoney(BigDecimal todayMoney) {
        this.todayMoney = todayMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
