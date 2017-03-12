package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-05
 */
public class ReAndroidMissionInstall implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 用户id
    private Integer userId;
    // 应用包名
    private String appPackage;
    // 状态: 0-已安装未体验完成 1-已完成(已完成定时会删除)
    private Integer missionStatus;
    // 领取时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndroidMissionInstall() {
    }

    /**
     * full constructor
     */
    public ReAndroidMissionInstall(Long missionId, Integer userId, String appPackage, Integer missionStatus, String createTime, String updateTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.appPackage = appPackage;
        this.missionStatus = missionStatus;
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
     * 状态: 0-已安装未体验完成 1-已完成(已完成定时会删除)
     */
    public Integer getMissionStatus() {
        return this.missionStatus;
    }

    /**
     * 状态: 0-已安装未体验完成 1-已完成(已完成定时会删除)
     */
    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
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