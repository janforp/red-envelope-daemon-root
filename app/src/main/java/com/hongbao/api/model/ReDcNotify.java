package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
public class ReDcNotify implements java.io.Serializable {

    // Fields

    // 订单号，每个订单号都具有唯一性；用于排重
    private String orderId;
    // 设备号，手机唯一，即手机的IMEI或 MAC地址
    private String deviceId;
    // 在点财的应用所对应的APPID值
    private String appId;
    // 虚拟货币
    private BigDecimal score;
    // 汇率：1元钱=多少积分(>=1)   注:默认值100
    private BigDecimal rate;
    // 广告任务的类型  0-安装激活任务； 1(或大于1)-签到任务
    private Integer tradeType;
    // 广告的名称
    private String adName;
    // 广告的包名
    private String adPackname;
    // 用户ID
    private Integer userId;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReDcNotify() {
    }

    /**
     * full constructor
     */
    public ReDcNotify(String orderId, String deviceId, String appId, BigDecimal score, BigDecimal rate, Integer tradeType, String adName, String adPackname, Integer userId, String notifyTime) {
        this.orderId = orderId;
        this.deviceId = deviceId;
        this.appId = appId;
        this.score = score;
        this.rate = rate;
        this.tradeType = tradeType;
        this.adName = adName;
        this.adPackname = adPackname;
        this.userId = userId;
        this.notifyTime = notifyTime;
    }

    // Property accessors

    /**
     * 订单号，每个订单号都具有唯一性；用于排重
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * 订单号，每个订单号都具有唯一性；用于排重
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 设备号，手机唯一，即手机的IMEI或 MAC地址
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 设备号，手机唯一，即手机的IMEI或 MAC地址
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 在点财的应用所对应的APPID值
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * 在点财的应用所对应的APPID值
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 虚拟货币
     */
    public BigDecimal getScore() {
        return this.score;
    }

    /**
     * 虚拟货币
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 汇率：1元钱=多少积分(>=1)   注:默认值100
     */
    public BigDecimal getRate() {
        return this.rate;
    }

    /**
     * 汇率：1元钱=多少积分(>=1)   注:默认值100
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
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