package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-05
 */
public class ReAndriodDeepMission implements java.io.Serializable {

    // Fields

    // 积分墙任务id
    private Long wallId;
    // 用户id
    private Integer userId;
    // 信息id
    private Long infoId;
    // 完成次数
    private Integer finishTimes;
    // 最近一次完成日期,如:2016-08-18
    private String lastFinishTime;
    // 开始日期,如:2016-08-18
    private String startTime;
    // 结束日期,如:2016-08-18
    private String endTime;
    // 领取时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndriodDeepMission() {
    }

    /**
     * full constructor
     */
    public ReAndriodDeepMission(Long wallId, Integer userId, Long infoId, Integer finishTimes, String lastFinishTime, String startTime, String endTime, String createTime, String updateTime) {
        this.wallId = wallId;
        this.userId = userId;
        this.infoId = infoId;
        this.finishTimes = finishTimes;
        this.lastFinishTime = lastFinishTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
     * 完成次数
     */
    public Integer getFinishTimes() {
        return this.finishTimes;
    }

    /**
     * 完成次数
     */
    public void setFinishTimes(Integer finishTimes) {
        this.finishTimes = finishTimes;
    }

    /**
     * 最近一次完成日期,如:2016-08-18
     */
    public String getLastFinishTime() {
        return this.lastFinishTime;
    }

    /**
     * 最近一次完成日期,如:2016-08-18
     */
    public void setLastFinishTime(String lastFinishTime) {
        this.lastFinishTime = lastFinishTime;
    }

    /**
     * 开始日期,如:2016-08-18
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 开始日期,如:2016-08-18
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束日期,如:2016-08-18
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 结束日期,如:2016-08-18
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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