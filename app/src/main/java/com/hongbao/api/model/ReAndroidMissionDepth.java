package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-05
 */
public class ReAndroidMissionDepth implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 深度任务id, 第几天打开
    private Integer depthId;
    // 奖励
    private BigDecimal depthMoney;
    // 描述
    private String depthDesc;
    // 开始时间 如:09:00:00
    private String depthStartTime;
    // 留存
    private BigDecimal depthKeep;
    // 体验时长(秒)
    private Integer depthTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndroidMissionDepth() {
    }

    /**
     * full constructor
     */
    public ReAndroidMissionDepth(Long missionId, Integer depthId, BigDecimal depthMoney, String depthDesc, String depthStartTime, BigDecimal depthKeep, Integer depthTime, String createTime, String updateTime) {
        this.missionId = missionId;
        this.depthId = depthId;
        this.depthMoney = depthMoney;
        this.depthDesc = depthDesc;
        this.depthStartTime = depthStartTime;
        this.depthKeep = depthKeep;
        this.depthTime = depthTime;
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
     * 深度任务id, 第几天打开
     */
    public Integer getDepthId() {
        return this.depthId;
    }

    /**
     * 深度任务id, 第几天打开
     */
    public void setDepthId(Integer depthId) {
        this.depthId = depthId;
    }

    /**
     * 奖励
     */
    public BigDecimal getDepthMoney() {
        return this.depthMoney;
    }

    /**
     * 奖励
     */
    public void setDepthMoney(BigDecimal depthMoney) {
        this.depthMoney = depthMoney;
    }

    /**
     * 描述
     */
    public String getDepthDesc() {
        return this.depthDesc;
    }

    /**
     * 描述
     */
    public void setDepthDesc(String depthDesc) {
        this.depthDesc = depthDesc;
    }

    /**
     * 开始时间 如:09:00:00
     */
    public String getDepthStartTime() {
        return this.depthStartTime;
    }

    /**
     * 开始时间 如:09:00:00
     */
    public void setDepthStartTime(String depthStartTime) {
        this.depthStartTime = depthStartTime;
    }

    /**
     * 留存
     */
    public BigDecimal getDepthKeep() {
        return this.depthKeep;
    }

    /**
     * 留存
     */
    public void setDepthKeep(BigDecimal depthKeep) {
        this.depthKeep = depthKeep;
    }

    /**
     * 体验时长(秒)
     */
    public Integer getDepthTime() {
        return this.depthTime;
    }

    /**
     * 体验时长(秒)
     */
    public void setDepthTime(Integer depthTime) {
        this.depthTime = depthTime;
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