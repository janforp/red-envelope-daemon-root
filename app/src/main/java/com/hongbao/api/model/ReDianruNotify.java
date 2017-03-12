package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-12
 */
public class ReDianruNotify implements java.io.Serializable {

    // Fields

    // 唯一标识 ID
    private String hashId;
    // 开发者应用 ID
    private String appId;
    // 广告 ID
    private String adId;
    // 广告名称
    private String adName;
    // 用户ID
    private Integer userId;
    // mac 地址
    private String macAdd;
    // 设备唯一标识(IMEI)
    private String deviceId;
    // 渠道来源
    private String source;
    // 积分
    private Integer point;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReDianruNotify() {
    }

    /**
     * full constructor
     */
    public ReDianruNotify(String hashId, String appId, String adId, String adName, Integer userId, String macAdd, String deviceId, String source, Integer point, String notifyTime) {
        this.hashId = hashId;
        this.appId = appId;
        this.adId = adId;
        this.adName = adName;
        this.userId = userId;
        this.macAdd = macAdd;
        this.deviceId = deviceId;
        this.source = source;
        this.point = point;
        this.notifyTime = notifyTime;
    }

    // Property accessors

    /**
     * 唯一标识 ID
     */
    public String getHashId() {
        return this.hashId;
    }

    /**
     * 唯一标识 ID
     */
    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    /**
     * 开发者应用 ID
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * 开发者应用 ID
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 广告 ID
     */
    public String getAdId() {
        return this.adId;
    }

    /**
     * 广告 ID
     */
    public void setAdId(String adId) {
        this.adId = adId;
    }

    /**
     * 广告名称
     */
    public String getAdName() {
        return this.adName;
    }

    /**
     * 广告名称
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
     * mac 地址
     */
    public String getMacAdd() {
        return this.macAdd;
    }

    /**
     * mac 地址
     */
    public void setMacAdd(String macAdd) {
        this.macAdd = macAdd;
    }

    /**
     * 设备唯一标识(IMEI)
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 设备唯一标识(IMEI)
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 渠道来源
     */
    public String getSource() {
        return this.source;
    }

    /**
     * 渠道来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 积分
     */
    public Integer getPoint() {
        return this.point;
    }

    /**
     * 积分
     */
    public void setPoint(Integer point) {
        this.point = point;
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