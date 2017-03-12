package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
public class ReDtnNotify implements java.io.Serializable {

    // Fields

    // 订单号，每个订单号都具有唯一性；用于排重
    private String orderId;
    // 在点财的应用所对应的APPID值
    private String appId;
    // 设备识别码(IMEI)
    private String udid;
    // 用户ID
    private Integer userId;
    // 充值的虚拟币金额
    private BigDecimal currency;
    // 激活产品名称
    private String adName;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReDtnNotify() {
    }

    /**
     * full constructor
     */
    public ReDtnNotify(String orderId, String appId, String udid, Integer userId, BigDecimal currency, String adName, String notifyTime) {
        this.orderId = orderId;
        this.appId = appId;
        this.udid = udid;
        this.userId = userId;
        this.currency = currency;
        this.adName = adName;
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
     * 设备识别码(IMEI)
     */
    public String getUdid() {
        return this.udid;
    }

    /**
     * 设备识别码(IMEI)
     */
    public void setUdid(String udid) {
        this.udid = udid;
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
     * 充值的虚拟币金额
     */
    public BigDecimal getCurrency() {
        return this.currency;
    }

    /**
     * 充值的虚拟币金额
     */
    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }

    /**
     * 激活产品名称
     */
    public String getAdName() {
        return this.adName;
    }

    /**
     * 激活产品名称
     */
    public void setAdName(String adName) {
        this.adName = adName;
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