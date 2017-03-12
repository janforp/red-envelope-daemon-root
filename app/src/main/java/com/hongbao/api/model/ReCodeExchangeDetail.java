package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public class ReCodeExchangeDetail implements java.io.Serializable {

    // Fields

    // 任务ID
    private Long missionId;
    // 用户ID
    private Integer userId;
    // 兑换码
    private String exchangeCode;
    // 兑换状态 0-未兑换, 1-已兑换
    private Integer exchangeStatus;
    // 金额
    private BigDecimal money;
    // 生成时间,如:2016-08-18 12:53:30
    private String createTime;
    // 兑换时间,如:2016-08-18 12:53:30
    private String exchangeTime;

    // Constructors

    /**
     * default constructor
     */
    public ReCodeExchangeDetail() {
    }

    /**
     * full constructor
     */
    public ReCodeExchangeDetail(Long missionId, Integer userId, String exchangeCode, Integer exchangeStatus, BigDecimal money, String createTime, String exchangeTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.exchangeCode = exchangeCode;
        this.exchangeStatus = exchangeStatus;
        this.money = money;
        this.createTime = createTime;
        this.exchangeTime = exchangeTime;
    }

    // Property accessors

    /**
     * 任务ID
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务ID
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

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
     * 兑换码
     */
    public String getExchangeCode() {
        return this.exchangeCode;
    }

    /**
     * 兑换码
     */
    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    /**
     * 兑换状态 0-未兑换, 1-已兑换
     */
    public Integer getExchangeStatus() {
        return this.exchangeStatus;
    }

    /**
     * 兑换状态 0-未兑换, 1-已兑换
     */
    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    /**
     * 金额
     */
    public BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 生成时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 生成时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 兑换时间,如:2016-08-18 12:53:30
     */
    public String getExchangeTime() {
        return this.exchangeTime;
    }

    /**
     * 兑换时间,如:2016-08-18 12:53:30
     */
    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

}