package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-29
 */
public class ReCustomerExtend implements java.io.Serializable {

    // Fields

    // 推广id
    private Integer id;
    // 客户id
    private Integer customerId;
    // 是否跳转 0-不跳转, 1-跳转
    private Integer isRedirect;
    // 跳转链接
    private String redirectUrl;
    // 等待跳转时间(秒)
    private Integer waitTime;
    // 推广说明
    private String extendDesc;
    // banner图
    private String customerBanner;
    // banner链接
    private String customerBannerUrl;
    // 广告图
    private String customerAdvert;
    // 广告链接
    private String customerAdvertUrl;
    // 红包总个数
    private Integer redNumTotal;
    // 红包剩余个数
    private Integer redNumLeft;
    // 当天红包总个数
    private Integer redNumDayTotal;
    // 当天红包剩余个数
    private Integer redNumDayLeft;
    // 状态 0-结束, 1-进行中
    private Integer customerStatus;
    // 开始时间
    private Integer startTime;
    // 结束时间
    private Integer endTime;
    // 是热门 0-否, 1-是
    private Integer isHot;
    // 中奖概率 0-100
    private Integer redChance;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer extendStatus;
    // 奖励说明
    private String awardDesc;

    // Constructors

    /**
     * default constructor
     */
    public ReCustomerExtend() {
    }

    /**
     * full constructor
     */
    public ReCustomerExtend(Integer customerId, Integer isRedirect, String redirectUrl, Integer waitTime, String extendDesc, String customerBanner, String customerBannerUrl, String customerAdvert, String customerAdvertUrl, Integer redNumTotal, Integer redNumLeft, Integer redNumDayTotal, Integer redNumDayLeft, Integer customerStatus, Integer startTime, Integer endTime, Integer isHot, Integer redChance, Integer extendStatus, String awardDesc) {
        this.customerId = customerId;
        this.isRedirect = isRedirect;
        this.redirectUrl = redirectUrl;
        this.waitTime = waitTime;
        this.extendDesc = extendDesc;
        this.customerBanner = customerBanner;
        this.customerBannerUrl = customerBannerUrl;
        this.customerAdvert = customerAdvert;
        this.customerAdvertUrl = customerAdvertUrl;
        this.redNumTotal = redNumTotal;
        this.redNumLeft = redNumLeft;
        this.redNumDayTotal = redNumDayTotal;
        this.redNumDayLeft = redNumDayLeft;
        this.customerStatus = customerStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isHot = isHot;
        this.redChance = redChance;
        this.extendStatus = extendStatus;
        this.awardDesc = awardDesc;
    }

    // Property accessors

    /**
     * 推广id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 推广id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 客户id
     */
    public Integer getCustomerId() {
        return this.customerId;
    }

    /**
     * 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 是否跳转 0-不跳转, 1-跳转
     */
    public Integer getIsRedirect() {
        return this.isRedirect;
    }

    /**
     * 是否跳转 0-不跳转, 1-跳转
     */
    public void setIsRedirect(Integer isRedirect) {
        this.isRedirect = isRedirect;
    }

    /**
     * 跳转链接
     */
    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    /**
     * 跳转链接
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * 等待跳转时间(秒)
     */
    public Integer getWaitTime() {
        return this.waitTime;
    }

    /**
     * 等待跳转时间(秒)
     */
    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    /**
     * 推广说明
     */
    public String getExtendDesc() {
        return this.extendDesc;
    }

    /**
     * 推广说明
     */
    public void setExtendDesc(String extendDesc) {
        this.extendDesc = extendDesc;
    }

    /**
     * banner图
     */
    public String getCustomerBanner() {
        return this.customerBanner;
    }

    /**
     * banner图
     */
    public void setCustomerBanner(String customerBanner) {
        this.customerBanner = customerBanner;
    }

    /**
     * banner链接
     */
    public String getCustomerBannerUrl() {
        return this.customerBannerUrl;
    }

    /**
     * banner链接
     */
    public void setCustomerBannerUrl(String customerBannerUrl) {
        this.customerBannerUrl = customerBannerUrl;
    }

    /**
     * 广告图
     */
    public String getCustomerAdvert() {
        return this.customerAdvert;
    }

    /**
     * 广告图
     */
    public void setCustomerAdvert(String customerAdvert) {
        this.customerAdvert = customerAdvert;
    }

    /**
     * 广告链接
     */
    public String getCustomerAdvertUrl() {
        return this.customerAdvertUrl;
    }

    /**
     * 广告链接
     */
    public void setCustomerAdvertUrl(String customerAdvertUrl) {
        this.customerAdvertUrl = customerAdvertUrl;
    }

    /**
     * 红包总个数
     */
    public Integer getRedNumTotal() {
        return this.redNumTotal;
    }

    /**
     * 红包总个数
     */
    public void setRedNumTotal(Integer redNumTotal) {
        this.redNumTotal = redNumTotal;
    }

    /**
     * 红包剩余个数
     */
    public Integer getRedNumLeft() {
        return this.redNumLeft;
    }

    /**
     * 红包剩余个数
     */
    public void setRedNumLeft(Integer redNumLeft) {
        this.redNumLeft = redNumLeft;
    }

    /**
     * 当天红包总个数
     */
    public Integer getRedNumDayTotal() {
        return this.redNumDayTotal;
    }

    /**
     * 当天红包总个数
     */
    public void setRedNumDayTotal(Integer redNumDayTotal) {
        this.redNumDayTotal = redNumDayTotal;
    }

    /**
     * 当天红包剩余个数
     */
    public Integer getRedNumDayLeft() {
        return this.redNumDayLeft;
    }

    /**
     * 当天红包剩余个数
     */
    public void setRedNumDayLeft(Integer redNumDayLeft) {
        this.redNumDayLeft = redNumDayLeft;
    }

    /**
     * 状态 0-结束, 1-进行中
     */
    public Integer getCustomerStatus() {
        return this.customerStatus;
    }

    /**
     * 状态 0-结束, 1-进行中
     */
    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    /**
     * 开始时间
     */
    public Integer getStartTime() {
        return this.startTime;
    }

    /**
     * 开始时间
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     */
    public Integer getEndTime() {
        return this.endTime;
    }

    /**
     * 结束时间
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * 是热门 0-否, 1-是
     */
    public Integer getIsHot() {
        return this.isHot;
    }

    /**
     * 是热门 0-否, 1-是
     */
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    /**
     * 中奖概率 0-100
     */
    public Integer getRedChance() {
        return this.redChance;
    }

    /**
     * 中奖概率 0-100
     */
    public void setRedChance(Integer redChance) {
        this.redChance = redChance;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getExtendStatus() {
        return this.extendStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setExtendStatus(Integer extendStatus) {
        this.extendStatus = extendStatus;
    }

    /**
     * 奖励说明
     */
    public String getAwardDesc() {
        return this.awardDesc;
    }

    /**
     * 奖励说明
     */
    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }

}