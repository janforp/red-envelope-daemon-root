package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public class ReNewcomerMissionDetail implements java.io.Serializable {

    // Fields

    // 任务ID
    private Long missionId;
    // 用户ID
    private Integer userId;
    // 金额
    private BigDecimal money;
    // 领取时间,如:2016-08-18 12:53:30
    private String drawTime;

    // Constructors

    /**
     * default constructor
     */
    public ReNewcomerMissionDetail() {
    }

    /**
     * full constructor
     */
    public ReNewcomerMissionDetail(Long missionId, Integer userId, BigDecimal money, String drawTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.money = money;
        this.drawTime = drawTime;
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
     * 领取时间,如:2016-08-18 12:53:30
     */
    public String getDrawTime() {
        return this.drawTime;
    }

    /**
     * 领取时间,如:2016-08-18 12:53:30
     */
    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

}