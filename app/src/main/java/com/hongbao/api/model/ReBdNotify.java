package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
public class ReBdNotify implements java.io.Serializable {

    // Fields

    // ID
    private Long id;
    // 用户ID
    private Integer userId;
    // 设备Id
    private String deviceId;
    // 开发者应用ID
    private String appId;
    // 虚拟货币
    private BigDecimal currency;
    // 汇率：1元钱=多少积分(>=1)   注:默认值100
    private BigDecimal ratio;
    // 时间戳
    private Long timeStamp;
    // 广告的名称
    private String adName;
    // 广告的包名
    private String adPackname;
    // 广告任务的类型  0-安装激活任务； 1(或大于1)-签到任务
    private Integer tradeType;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReBdNotify() {
    }

    /**
     * full constructor
     */
    public ReBdNotify(Integer userId, String deviceId, String appId, BigDecimal currency, BigDecimal ratio, Long timeStamp, String adName, String adPackname, Integer tradeType, String notifyTime) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.appId = appId;
        this.currency = currency;
        this.ratio = ratio;
        this.timeStamp = timeStamp;
        this.adName = adName;
        this.adPackname = adPackname;
        this.tradeType = tradeType;
        this.notifyTime = notifyTime;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * ID
     */
    public void setId(Long id) {
        this.id = id;
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
     * 设备Id
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 设备Id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 开发者应用ID
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * 开发者应用ID
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 虚拟货币
     */
    public BigDecimal getCurrency() {
        return this.currency;
    }

    /**
     * 虚拟货币
     */
    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }

    /**
     * 汇率：1元钱=多少积分(>=1)   注:默认值100
     */
    public BigDecimal getRatio() {
        return this.ratio;
    }

    /**
     * 汇率：1元钱=多少积分(>=1)   注:默认值100
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    /**
     * 时间戳
     */
    public Long getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * 时间戳
     */
    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * 广告的名称
     */
    public String getAdName() {
        return this.adName;
    }

    /**
     * 广告的名称
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * 广告的包名
     */
    public String getAdPackname() {
        return this.adPackname;
    }

    /**
     * 广告的包名
     */
    public void setAdPackname(String adPackname) {
        this.adPackname = adPackname;
    }

    /**
     * 广告任务的类型  0-安装激活任务； 1(或大于1)-签到任务
     */
    public Integer getTradeType() {
        return this.tradeType;
    }

    /**
     * 广告任务的类型  0-安装激活任务； 1(或大于1)-签到任务
     */
    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 回调时间,如:2016-08-18 12:53:30
     */
    public String getNotifyTime() {
        return this.notifyTime;
    }

    /**
     * 回调时间,如:2016-08-18 12:53:30
     */
    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

}