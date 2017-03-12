package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-29
 */
public class ReUserChannel implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long id;
    // 用户id
    private Integer userId;
    // 平台 0-iOS, 1-Android
    private Integer platform;
    // 设备Id
    private String deviceId;
    // 设备名称
    private String deviceName;
    // 注册时app的渠道包名id
    private Integer appId;
    // 包名
    private String packageName;
    // 渠道
    private String channelName;
    // app版本
    private String appVersion;
    // 时间
    private String createTime;
    // 注册ip
    private String userIp;
    // 国际移动用户识别码imsi
    private String userImsi;
    // 国际移动设备身份码imei
    private String userImei;

    // Constructors

    /**
     * default constructor
     */
    public ReUserChannel() {
    }

    /**
     * full constructor
     */
    public ReUserChannel(Integer userId, Integer platform, String deviceId, String deviceName, Integer appId, String packageName, String channelName, String appVersion, String createTime, String userIp, String userImsi, String userImei) {
        this.userId = userId;
        this.platform = platform;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.appId = appId;
        this.packageName = packageName;
        this.channelName = channelName;
        this.appVersion = appVersion;
        this.createTime = createTime;
        this.userIp = userIp;
        this.userImsi = userImsi;
        this.userImei = userImei;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id，自增长
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 平台 0-iOS, 1-Android
     */
    public Integer getPlatform() {
        return this.platform;
    }

    /**
     * 平台 0-iOS, 1-Android
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
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
     * 设备名称
     */
    public String getDeviceName() {
        return this.deviceName;
    }

    /**
     * 设备名称
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 注册时app的渠道包名id
     */
    public Integer getAppId() {
        return this.appId;
    }

    /**
     * 注册时app的渠道包名id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
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
     * 渠道
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * app版本
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * app版本
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 时间
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 注册ip
     */
    public String getUserIp() {
        return this.userIp;
    }

    /**
     * 注册ip
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    /**
     * 国际移动用户识别码imsi
     */
    public String getUserImsi() {
        return this.userImsi;
    }

    /**
     * 国际移动用户识别码imsi
     */
    public void setUserImsi(String userImsi) {
        this.userImsi = userImsi;
    }

    /**
     * 国际移动设备身份码imei
     */
    public String getUserImei() {
        return this.userImei;
    }

    /**
     * 国际移动设备身份码imei
     */
    public void setUserImei(String userImei) {
        this.userImei = userImei;
    }

}