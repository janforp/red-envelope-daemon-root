package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-21
 */
public class ReUserCommission implements java.io.Serializable {

    // Fields

    // 用户ID
    private Integer userId;
    // 当前佣金(元)
    private BigDecimal currentMoney;
    // 累计佣金(元)
    private BigDecimal totalMoney;

    // Constructors

    /**
     * default constructor
     */
    public ReUserCommission() {
    }

    /**
     * full constructor
     */
    public ReUserCommission(Integer userId, BigDecimal currentMoney, BigDecimal totalMoney) {
        this.userId = userId;
        this.currentMoney = currentMoney;
        this.totalMoney = totalMoney;
    }

    // Property accessors

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 当前佣金(元)
     */
    public BigDecimal getCurrentMoney() {
        return this.currentMoney;
    }

    /**
     * 当前佣金(元)
     */
    public void setCurrentMoney(BigDecimal currentMoney) {
        this.currentMoney = currentMoney;
    }

    /**
     * 累计佣金(元)
     */
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    /**
     * 累计佣金(元)
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

}