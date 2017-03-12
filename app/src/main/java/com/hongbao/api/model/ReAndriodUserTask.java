package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-03
 */
public class ReAndriodUserTask implements java.io.Serializable {

    // Fields

    // 积分墙任务id
    private Long wallId;
    // 用户id
    private Integer userId;
    // 信息id
    private Long infoId;
    // 状态, 0:进行中, 1:已完成, 2:已放弃
    private Integer taskStatus;
    // 领取时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 任务过期,释放时间
    private String releaseTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndriodUserTask() {
    }

    /**
     * full constructor
     */
    public ReAndriodUserTask(Long wallId, Integer userId, Long infoId, Integer taskStatus, String createTime, String updateTime, String releaseTime) {
        this.wallId = wallId;
        this.userId = userId;
        this.infoId = infoId;
        this.taskStatus = taskStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.releaseTime = releaseTime;
    }

    // Property accessors

    /**
     * 积分墙任务id
     */
    public Long getWallId() {
        return this.wallId;
    }

    /**
     * 积分墙任务id
     */
    public void setWallId(Long wallId) {
        this.wallId = wallId;
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
     * 信息id
     */
    public Long getInfoId() {
        return this.infoId;
    }

    /**
     * 信息id
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    /**
     * 状态, 0:进行中, 1:已完成, 2:已放弃
     */
    public Integer getTaskStatus() {
        return this.taskStatus;
    }

    /**
     * 状态, 0:进行中, 1:已完成, 2:已放弃
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
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

    /**
     * 任务过期,释放时间
     */
    public String getReleaseTime() {
        return this.releaseTime;
    }

    /**
     * 任务过期,释放时间
     */
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

}