package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-29
 */
public class ReAccountDetail implements java.io.Serializable {

    // Fields

    // ID
    private Long detailId;
    // 用户ID
    private Integer userId;
    // 注册时app的渠道版本id
    private Integer appId;
    // 金额
    private BigDecimal accountMoney;
    // 类型;0:支出,1:收入
    private Integer detailType;
    // 任务分类
    private Integer missionType;
    // 任务子分类
    private Integer missionSubtype;
    // 详情
    private String detailContent;
    // 时间,如:2016-08-18 12:53:30
    private String detailTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAccountDetail() {
    }

    /**
     * full constructor
     */
    public ReAccountDetail(Integer userId, Integer appId, BigDecimal accountMoney, Integer detailType, Integer missionType, Integer missionSubtype, String detailContent, String detailTime) {
        this.userId = userId;
        this.appId = appId;
        this.accountMoney = accountMoney;
        this.detailType = detailType;
        this.missionType = missionType;
        this.missionSubtype = missionSubtype;
        this.detailContent = detailContent;
        this.detailTime = detailTime;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getDetailId() {
        return this.detailId;
    }

    /**
     * ID
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
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

    /**
     * 金额
     */
    public BigDecimal getAccountMoney() {
        return this.accountMoney;
    }

    /**
     * 金额
     */
    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    /**
     * 类型;0:支出,1:收入
     */
    public Integer getDetailType() {
        return this.detailType;
    }

    /**
     * 类型;0:支出,1:收入
     */
    public void setDetailType(Integer detailType) {
        this.detailType = detailType;
    }

    /**
     * 任务分类
     */
    public Integer getMissionType() {
        return this.missionType;
    }

    /**
     * 任务分类
     */
    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    /**
     * 任务子分类
     */
    public Integer getMissionSubtype() {
        return this.missionSubtype;
    }

    /**
     * 任务子分类
     */
    public void setMissionSubtype(Integer missionSubtype) {
        this.missionSubtype = missionSubtype;
    }

    /**
     * 详情
     */
    public String getDetailContent() {
        return this.detailContent;
    }

    /**
     * 详情
     */
    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public String getDetailTime() {
        return this.detailTime;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }

}