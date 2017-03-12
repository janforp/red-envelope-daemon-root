package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
public class ReYmIos implements java.io.Serializable {

    // Fields

    // 订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
    private String orderId;
    // 开发者应用ID
    private String appId;
    // 广告标题
    private String adName;
    // 广告id
    private Integer adId;
    // 用户ID
    private Integer userId;
    // 设备Id
    private String deviceId;
    // 渠道号
    private String channelName;
    // 金额
    private BigDecimal price;
    // 用户可以赚取的积分
    private BigDecimal points;
    // 应用商店的 Id，该值在某些任务类型可能为空
    private String storeId;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReYmIos() {
    }

    /**
     * full constructor
     */
    public ReYmIos(String orderId, String appId, String adName, Integer adId, Integer userId, String deviceId, String channelName, BigDecimal price, BigDecimal points, String storeId, String notifyTime) {
        this.orderId = orderId;
        this.appId = appId;
        this.adName = adName;
        this.adId = adId;
        this.userId = userId;
        this.deviceId = deviceId;
        this.channelName = channelName;
        this.price = price;
        this.points = points;
        this.storeId = storeId;
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
     * 渠道号
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道号
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
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
     * 用户可以赚取的积分
     */
    public BigDecimal getPoints() {
        return this.points;
    }

    /**
     * 用户可以赚取的积分
     */
    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    /**
     * 应用商店的 Id，该值在某些任务类型可能为空
     */
    public String getStoreId() {
        return this.storeId;
    }

    /**
     * 应用商店的 Id，该值在某些任务类型可能为空
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
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