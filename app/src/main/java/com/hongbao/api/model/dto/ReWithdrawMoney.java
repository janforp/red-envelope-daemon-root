package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 16/8/19.
 */
public class ReWithdrawMoney implements Serializable {

    // 申请金额(元)
    private BigDecimal applyMoney;
    // 到账金额(元)
    private BigDecimal withdrawMoney;

    public BigDecimal getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(BigDecimal applyMoney) {
        this.applyMoney = applyMoney;
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }
}
