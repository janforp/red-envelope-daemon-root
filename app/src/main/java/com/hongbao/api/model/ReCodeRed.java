package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-23
 */
public class ReCodeRed implements java.io.Serializable {

    // Fields

    // id
    private Integer codeId;
    // 红包口令
    private String redCode;
    // 客户名称
    private String customerName;
    // 客户头像
    private String customerImg;
    // 客户描述
    private String customerDesc;
    // 奖励说明
    private String awardDesc;
    // banner图
    private String customerBanner;
    // banner链接
    private String customerBannerUrl;
    // 最大红包(展示用)
    private String redMax;
    // 红包领取规则
    private String redDesc;
    // 红包总个数
    private Integer redNumTotal;
    // 红包剩余个数
    private Integer redNumLeft;
    // 当天红包总个数
    private Integer redNumDayTotal;
    // 当天红包剩余个数
    private Integer redNumDayLeft;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer codeStatus;
    // 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long createTime;
    // 更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReCodeRed() {
    }

    /**
     * full constructor
     */
    public ReCodeRed(String redCode, String customerName, String customerImg, String customerDesc, String awardDesc, String customerBanner, String customerBannerUrl, String redMax, String redDesc, Integer redNumTotal, Integer redNumLeft, Integer redNumDayTotal, Integer redNumDayLeft, Integer codeStatus, Long createTime, Long updateTime) {
        this.redCode = redCode;
        this.customerName = customerName;
        this.customerImg = customerImg;
        this.customerDesc = customerDesc;
        this.awardDesc = awardDesc;
        this.customerBanner = customerBanner;
        this.customerBannerUrl = customerBannerUrl;
        this.redMax = redMax;
        this.redDesc = redDesc;
        this.redNumTotal = redNumTotal;
        this.redNumLeft = redNumLeft;
        this.redNumDayTotal = redNumDayTotal;
        this.redNumDayLeft = redNumDayLeft;
        this.codeStatus = codeStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getCodeId() {
        return this.codeId;
    }

    /**
     * id
     */
    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    /**
     * 红包口令
     */
    public String getRedCode() {
        return this.redCode;
    }

    /**
     * 红包口令
     */
    public void setRedCode(String redCode) {
        this.redCode = redCode;
    }

    /**
     * 客户名称
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 客户头像
     */
    public String getCustomerImg() {
        return this.customerImg;
    }

    /**
     * 客户头像
     */
    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    /**
     * 客户描述
     */
    public String getCustomerDesc() {
        return this.customerDesc;
    }

    /**
     * 客户描述
     */
    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
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
     * 最大红包(展示用)
     */
    public String getRedMax() {
        return this.redMax;
    }

    /**
     * 最大红包(展示用)
     */
    public void setRedMax(String redMax) {
        this.redMax = redMax;
    }

    /**
     * 红包领取规则
     */
    public String getRedDesc() {
        return this.redDesc;
    }

    /**
     * 红包领取规则
     */
    public void setRedDesc(String redDesc) {
        this.redDesc = redDesc;
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
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getCodeStatus() {
        return this.codeStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    /**
     * 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}