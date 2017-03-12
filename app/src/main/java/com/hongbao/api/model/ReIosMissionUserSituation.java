package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-16
 */
public class ReIosMissionUserSituation implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 用户id
    private Integer userId;
    // 应用名称
    private String appName;
    // 应用图标
    private String appIcon;
    // 当前步骤编号
    private Integer stepId;
    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
    private Integer missionStatus;
    // 任务总奖励
    private BigDecimal totalMoney;
    // 已获得任务总奖励
    private BigDecimal gainMoney;
    // 任务结束时间,如:2016-08-18 12:53:30
    private String endTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReIosMissionUserSituation() {
    }

    /**
     * full constructor
     */
    public ReIosMissionUserSituation(Long missionId, Integer userId, String appName, String appIcon, Integer stepId, Integer missionStatus, BigDecimal totalMoney, BigDecimal gainMoney, String endTime, String createTime, String updateTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.appName = appName;
        this.appIcon = appIcon;
        this.stepId = stepId;
        this.missionStatus = missionStatus;
        this.totalMoney = totalMoney;
        this.gainMoney = gainMoney;
        this.endTime = endTime;
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
     * 应用名称
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * 应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 应用图标
     */
    public String getAppIcon() {
        return this.appIcon;
    }

    /**
     * 应用图标
     */
    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    /**
     * 当前步骤编号
     */
    public Integer getStepId() {
        return this.stepId;
    }

    /**
     * 当前步骤编号
     */
    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    /**
     * 状态 0-审核中 1-已通过 2-未通过 3-重新提交
     */
    public Integer getMissionStatus() {
        return this.missionStatus;
    }

    /**
     * 状态 0-审核中 1-已通过 2-未通过 3-重新提交
     */
    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    /**
     * 任务总奖励
     */
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    /**
     * 任务总奖励
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 已获得任务总奖励
     */
    public BigDecimal getGainMoney() {
        return this.gainMoney;
    }

    /**
     * 已获得任务总奖励
     */
    public void setGainMoney(BigDecimal gainMoney) {
        this.gainMoney = gainMoney;
    }

    /**
     * 任务结束时间,如:2016-08-18 12:53:30
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 任务结束时间,如:2016-08-18 12:53:30
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
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