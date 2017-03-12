package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-22
 */
public class ReUserCommissionRecord implements java.io.Serializable {

    // Fields

    // 完成积分墙激活日期
    private String dataDay;
    // 邀请人id
    private Integer userId;
    // 被邀请人id
    private Integer invitedUserId;
    // 总金额(元)
    private BigDecimal totalMoney;
    // 获得佣金(元)
    private BigDecimal gainMoney;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserCommissionRecord() {
    }

    /**
     * full constructor
     */
    public ReUserCommissionRecord(String dataDay, Integer userId, Integer invitedUserId, BigDecimal totalMoney, BigDecimal gainMoney, String createTime, String updateTime) {
        this.dataDay = dataDay;
        this.userId = userId;
        this.invitedUserId = invitedUserId;
        this.totalMoney = totalMoney;
        this.gainMoney = gainMoney;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * 完成积分墙激活日期
     */
    public String getDataDay() {
        return this.dataDay;
    }

    /**
     * 完成积分墙激活日期
     */
    public void setDataDay(String dataDay) {
        this.dataDay = dataDay;
    }

    /**
     * 邀请人id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 邀请人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 被邀请人id
     */
    public Integer getInvitedUserId() {
        return this.invitedUserId;
    }

    /**
     * 被邀请人id
     */
    public void setInvitedUserId(Integer invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    /**
     * 总金额(元)
     */
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    /**
     * 总金额(元)
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 获得佣金(元)
     */
    public BigDecimal getGainMoney() {
        return this.gainMoney;
    }

    /**
     * 获得佣金(元)
     */
    public void setGainMoney(BigDecimal gainMoney) {
        this.gainMoney = gainMoney;
    }

    /**
     * 创建时间
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}