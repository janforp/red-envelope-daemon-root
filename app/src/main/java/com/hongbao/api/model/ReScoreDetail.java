package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-29
 */
public class ReScoreDetail implements java.io.Serializable {

    // Fields

    // ID
    private Integer scoreId;
    // 用户ID
    private Integer userId;
    // 积分数量
    private Integer score;
    // 类型;0:消费积分,1:获得积分
    private Integer scoreType;
    // 说明
    private String scoreContent;
    // 时间,如:2016-08-18 12:53:30
    private String scoreTime;
    // 注册时app的渠道版本id
    private Integer appId;

    // Constructors

    /**
     * default constructor
     */
    public ReScoreDetail() {
    }

    /**
     * full constructor
     */
    public ReScoreDetail(Integer userId, Integer score, Integer scoreType, String scoreContent, String scoreTime, Integer appId) {
        this.userId = userId;
        this.score = score;
        this.scoreType = scoreType;
        this.scoreContent = scoreContent;
        this.scoreTime = scoreTime;
        this.appId = appId;
    }

    // Property accessors

    /**
     * ID
     */
    public Integer getScoreId() {
        return this.scoreId;
    }

    /**
     * ID
     */
    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
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
     * 积分数量
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * 积分数量
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 类型;0:消费积分,1:获得积分
     */
    public Integer getScoreType() {
        return this.scoreType;
    }

    /**
     * 类型;0:消费积分,1:获得积分
     */
    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    /**
     * 说明
     */
    public String getScoreContent() {
        return this.scoreContent;
    }

    /**
     * 说明
     */
    public void setScoreContent(String scoreContent) {
        this.scoreContent = scoreContent;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public String getScoreTime() {
        return this.scoreTime;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public void setScoreTime(String scoreTime) {
        this.scoreTime = scoreTime;
    }

    /**
     * 注册时app的渠道版本id
     */
    public Integer getAppId() {
        return this.appId;
    }

    /**
     * 注册时app的渠道版本id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

}