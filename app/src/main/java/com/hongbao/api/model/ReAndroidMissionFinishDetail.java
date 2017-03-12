package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-12
 */
public class ReAndroidMissionFinishDetail implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long detailId;
    // 任务id
    private Long missionId;
    // 用户id
    private Integer userId;
    // 开始任务的日期
    private String startDay;
    // 任务编号, 这个任务的第几天任务
    private Integer missionNo;
    // 任务奖励
    private BigDecimal missionMoney;
    // 领取时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndroidMissionFinishDetail() {
    }

    /**
     * full constructor
     */
    public ReAndroidMissionFinishDetail(Long missionId, Integer userId, String startDay, Integer missionNo, BigDecimal missionMoney, String createTime, String updateTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.startDay = startDay;
        this.missionNo = missionNo;
        this.missionMoney = missionMoney;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getDetailId() {
        return this.detailId;
    }

    /**
     * id，自增长
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

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
     * 任务编号, 这个任务的第几天任务
     */
    public Integer getMissionNo() {
        return this.missionNo;
    }

    /**
     * 任务编号, 这个任务的第几天任务
     */
    public void setMissionNo(Integer missionNo) {
        this.missionNo = missionNo;
    }

    /**
     * 任务奖励
     */
    public BigDecimal getMissionMoney() {
        return this.missionMoney;
    }

    /**
     * 任务奖励
     */
    public void setMissionMoney(BigDecimal missionMoney) {
        this.missionMoney = missionMoney;
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