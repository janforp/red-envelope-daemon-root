package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
public class ReAppTask implements java.io.Serializable {

    // Fields

    // id
    private Long taskId;
    // 用户ID
    private Integer userId;
    // 设备Id
    private String deviceId;
    // 关键词id
    private Long keywordId;
    // 此关键词对应的appID
    private Long appId;
    // 当前执行步骤
    private Integer taskStep;
    // 状态; 0:进行中,1:已完成,2:已放弃
    private Integer taskStatus;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAppTask() {
    }

    /**
     * full constructor
     */
    public ReAppTask(Integer userId, String deviceId, Long keywordId, Long appId, Integer taskStep, Integer taskStatus, Long createTime, Long updateTime) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.keywordId = keywordId;
        this.appId = appId;
        this.taskStep = taskStep;
        this.taskStatus = taskStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getTaskId() {
        return this.taskId;
    }

    /**
     * id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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
     * 设备Id
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 设备Id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 关键词id
     */
    public Long getKeywordId() {
        return this.keywordId;
    }

    /**
     * 关键词id
     */
    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    /**
     * 此关键词对应的appID
     */
    public Long getAppId() {
        return this.appId;
    }

    /**
     * 此关键词对应的appID
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 当前执行步骤
     */
    public Integer getTaskStep() {
        return this.taskStep;
    }

    /**
     * 当前执行步骤
     */
    public void setTaskStep(Integer taskStep) {
        this.taskStep = taskStep;
    }

    /**
     * 状态; 0:进行中,1:已完成,2:已放弃
     */
    public Integer getTaskStatus() {
        return this.taskStatus;
    }

    /**
     * 状态; 0:进行中,1:已完成,2:已放弃
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 创建时间
     */
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Long getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}