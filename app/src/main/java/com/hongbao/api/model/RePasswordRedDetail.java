package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public class RePasswordRedDetail implements java.io.Serializable {

    // Fields

    // 口令红包id
    private Long passwordId;
    // 领取红包用户id
    private Integer userId;
    // 领取金额(元)
    private BigDecimal money;
    // 领取时间
    private String receiveTime;

    // Constructors

    /**
     * default constructor
     */
    public RePasswordRedDetail() {
    }

    /**
     * full constructor
     */
    public RePasswordRedDetail(Long passwordId, Integer userId, BigDecimal money, String receiveTime) {
        this.passwordId = passwordId;
        this.userId = userId;
        this.money = money;
        this.receiveTime = receiveTime;
    }

    // Property accessors

    /**
     * 口令红包id
     */
    public Long getPasswordId() {
        return this.passwordId;
    }

    /**
     * 口令红包id
     */
    public void setPasswordId(Long passwordId) {
        this.passwordId = passwordId;
    }

    /**
     * 领取红包用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 领取红包用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 领取金额(元)
     */
    public BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 领取金额(元)
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 领取时间
     */
    public String getReceiveTime() {
        return this.receiveTime;
    }

    /**
     * 领取时间
     */
    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

}