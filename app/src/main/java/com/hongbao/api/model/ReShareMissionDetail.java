package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-17
 */
public class ReShareMissionDetail implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 用户id
    private Integer userId;
    // 微信openid
    private String openId;
    // 点击分享链接的时间,如:2016-08-18 12:53:30
    private String clickTime;

    // Constructors

    /**
     * default constructor
     */
    public ReShareMissionDetail() {
    }

    /**
     * full constructor
     */
    public ReShareMissionDetail(Long missionId, Integer userId, String openId, String clickTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.openId = openId;
        this.clickTime = clickTime;
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
     * 微信openid
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 微信openid
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 点击分享链接的时间,如:2016-08-18 12:53:30
     */
    public String getClickTime() {
        return this.clickTime;
    }

    /**
     * 点击分享链接的时间,如:2016-08-18 12:53:30
     */
    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

}