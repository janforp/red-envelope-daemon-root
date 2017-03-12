package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-18
 */
public class ReFixedRed implements java.io.Serializable {

    // Fields

    // ID
    private Integer fixedId;
    // 名称
    private String fixedTitle;
    // 红包数量
    private Integer fixedAmount;
    // 剩余红包数量
    private Integer fixedRemainder;
    // 链接
    private String fixedUrl;
    // 时
    private String fixedHour;
    // 分
    private String fixedMinute;
    // 秒
    private String fixedSecond;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer fixedStatus;

    // Constructors

    /**
     * default constructor
     */
    public ReFixedRed() {
    }

    /**
     * full constructor
     */
    public ReFixedRed(String fixedTitle, Integer fixedAmount, Integer fixedRemainder, String fixedUrl, String fixedHour, String fixedMinute, String fixedSecond, Integer fixedStatus) {
        this.fixedTitle = fixedTitle;
        this.fixedAmount = fixedAmount;
        this.fixedRemainder = fixedRemainder;
        this.fixedUrl = fixedUrl;
        this.fixedHour = fixedHour;
        this.fixedMinute = fixedMinute;
        this.fixedSecond = fixedSecond;
        this.fixedStatus = fixedStatus;
    }

    // Property accessors

    /**
     * ID
     */
    public Integer getFixedId() {
        return this.fixedId;
    }

    /**
     * ID
     */
    public void setFixedId(Integer fixedId) {
        this.fixedId = fixedId;
    }

    /**
     * 名称
     */
    public String getFixedTitle() {
        return this.fixedTitle;
    }

    /**
     * 名称
     */
    public void setFixedTitle(String fixedTitle) {
        this.fixedTitle = fixedTitle;
    }

    /**
     * 红包数量
     */
    public Integer getFixedAmount() {
        return this.fixedAmount;
    }

    /**
     * 红包数量
     */
    public void setFixedAmount(Integer fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    /**
     * 剩余红包数量
     */
    public Integer getFixedRemainder() {
        return this.fixedRemainder;
    }

    /**
     * 剩余红包数量
     */
    public void setFixedRemainder(Integer fixedRemainder) {
        this.fixedRemainder = fixedRemainder;
    }

    /**
     * 链接
     */
    public String getFixedUrl() {
        return this.fixedUrl;
    }

    /**
     * 链接
     */
    public void setFixedUrl(String fixedUrl) {
        this.fixedUrl = fixedUrl;
    }

    /**
     * 时
     */
    public String getFixedHour() {
        return this.fixedHour;
    }

    /**
     * 时
     */
    public void setFixedHour(String fixedHour) {
        this.fixedHour = fixedHour;
    }

    /**
     * 分
     */
    public String getFixedMinute() {
        return this.fixedMinute;
    }

    /**
     * 分
     */
    public void setFixedMinute(String fixedMinute) {
        this.fixedMinute = fixedMinute;
    }

    /**
     * 秒
     */
    public String getFixedSecond() {
        return this.fixedSecond;
    }

    /**
     * 秒
     */
    public void setFixedSecond(String fixedSecond) {
        this.fixedSecond = fixedSecond;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getFixedStatus() {
        return this.fixedStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setFixedStatus(Integer fixedStatus) {
        this.fixedStatus = fixedStatus;
    }

}