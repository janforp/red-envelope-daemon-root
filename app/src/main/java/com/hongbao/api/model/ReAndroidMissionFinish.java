package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-06
 */
public class ReAndroidMissionFinish implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 用户id
    private Integer userId;
    // 应用包名
    private String appPackage;
    // 开始任务的日期
    private String startDay;
    // 最后任务的日期
    private String endDay;
    // 任务总天数
    private Integer totalDay;
    // 最后完成时的任务编号
    private Integer missionNo;
    // 用户获取到的总奖励
    private BigDecimal totalMoney;
    // 领取时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndroidMissionFinish() {
    }

    /**
     * full constructor
     */
    public ReAndroidMissionFinish(Long missionId, Integer userId, String appPackage, String startDay, String endDay, Integer totalDay, Integer missionNo, BigDecimal totalMoney, String createTime, String updateTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.appPackage = appPackage;
        this.startDay = startDay;
        this.endDay = endDay;
        this.totalDay = totalDay;
        this.missionNo = missionNo;
        this.totalMoney = totalMoney;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * 任务id
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务id
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 应用包名
     */
    public String getAppPackage() {
        return this.appPackage;
    }

    /**
     * 应用包名
     */
    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    /**
     * 开始任务的日期
     */
    public String getStartDay() {
        return this.startDay;
    }

    /**
     * 开始任务的日期
     */
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    /**
     * 最后任务的日期
     */
    public String getEndDay() {
        return this.endDay;
    }

    /**
     * 最后任务的日期
     */
    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    /**
     * 任务总天数
     */
    public Integer getTotalDay() {
        return this.totalDay;
    }

    /**
     * 任务总天数
     */
    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    /**
     * 最后完成时的任务编号
     */
    public Integer getMissionNo() {
        return this.missionNo;
    }

    /**
     * 最后完成时的任务编号
     */
    public void setMissionNo(Integer missionNo) {
        this.missionNo = missionNo;
    }

    /**
     * 用户获取到的总奖励
     */
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    /**
     * 用户获取到的总奖励
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 领取时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 领取时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}