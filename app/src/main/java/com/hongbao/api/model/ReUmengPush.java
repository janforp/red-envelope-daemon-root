package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-18
 */
public class ReUmengPush implements java.io.Serializable {

    // Fields

    // re_user.user_id，主键
    private Integer userId;
    // 平台 0-ios, 1-andriod
    private Integer platform;
    // token
    private String deviceToken;

    // Constructors

    /**
     * default constructor
     */
    public ReUmengPush() {
    }

    /**
     * full constructor
     */
    public ReUmengPush(Integer userId, Integer platform, String deviceToken) {
        this.userId = userId;
        this.platform = platform;
        this.deviceToken = deviceToken;
    }

    // Property accessors

    /**
     * re_user.user_id，主键
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * re_user.user_id，主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 平台 0-ios, 1-andriod
     */
    public Integer getPlatform() {
        return this.platform;
    }

    /**
     * 平台 0-ios, 1-andriod
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * token
     */
    public String getDeviceToken() {
        return this.deviceToken;
    }

    /**
     * token
     */
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

}