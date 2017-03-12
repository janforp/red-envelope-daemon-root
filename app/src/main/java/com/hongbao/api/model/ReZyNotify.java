package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-09
 */
public class ReZyNotify implements java.io.Serializable {

    // Fields

    // 订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
    private String orderId;
    // 用户ID
    private Integer userId;
    // 开发者应用ID
    private String appId;
    // 广告id
    private Integer adId;
    // 广告标题
    private String adName;
    // 积分
    private Integer integral;
    // 设备Id
    private String deviceId;
    // 包名
    private String packageName;
    // 金额
    private BigDecimal price;
    // 回调的任务为第几天任务
    private Integer dayNum;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReZyNotify() {
    }

    /**
     * full constructor
     */
    public ReZyNotify(String orderId, Integer userId, String appId, Integer adId, String adName, Integer integral, String deviceId, String packageName, BigDecimal price, Integer dayNum, String notifyTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.appId = appId;
        this.adId = adId;
        this.adName = adName;
        this.integral = integral;
        this.deviceId = deviceId;
        this.packageName = packageName;
        this.price = price;
        this.dayNum = dayNum;
        this.notifyTime = notifyTime;
    }

    // Property accessors

    /**
     * 订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * 订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
     * 广告id
     */
    public Integer getAdId() {
        return this.adId;
    }

    /**
     * 广告id
     */
    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    /**
     * 广告标题
     */
    public String getAdName() {
        return this.adName;
    }

    /**
     * 广告标题
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * 积分
     */
    public Integer getIntegral() {
        return this.integral;
    }

    /**
     * 积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
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
     * 包名
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 金额
     */
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 金额
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 回调的任务为第几天任务
     */
    public Integer getDayNum() {
        return this.dayNum;
    }

    /**
     * 回调的任务为第几天任务
     */
    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
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