package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-10
 */
public class ReYmAndriod implements java.io.Serializable {

    // Fields

    // 订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
    private String orderId;
    // 开发者应用ID
    private String appId;
    // 广告标题
    private String adName;
    // 用户ID
    private Integer userId;
    // 渠道号
    private String channelName;
    // 用户可以赚取的积分
    private BigDecimal points;
    // 广告id
    private Integer adId;
    // 应用包名
    private String packageName;
    // 设备Id
    private String deviceId;
    // 金额
    private BigDecimal price;
    // 回调的任务类型。1=>主任务；2=>附加任务(附加任务可能会有多个)；3=>分享主任务；4=>深度分享任务
    private String tradeType;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReYmAndriod() {
    }

    /**
     * full constructor
     */
    public ReYmAndriod(String orderId, String appId, String adName, Integer userId, String channelName, BigDecimal points, Integer adId, String packageName, String deviceId, BigDecimal price, String tradeType, String notifyTime) {
        this.orderId = orderId;
        this.appId = appId;
        this.adName = adName;
        this.userId = userId;
        this.channelName = channelName;
        this.points = points;
        this.adId = adId;
        this.packageName = packageName;
        this.deviceId = deviceId;
        this.price = price;
        this.tradeType = tradeType;
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
     * 应用包名
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 应用包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
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
     * 回调的任务类型。1=>主任务；2=>附加任务(附加任务可能会有多个)；3=>分享主任务；4=>深度分享任务
     */
    public String getTradeType() {
        return this.tradeType;
    }

    /**
     * 回调的任务类型。1=>主任务；2=>附加任务(附加任务可能会有多个)；3=>分享主任务；4=>深度分享任务
     */
    public void setTradeType(String tradeType) {
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