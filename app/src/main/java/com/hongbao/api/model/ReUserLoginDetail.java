package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-22
 */
public class ReUserLoginDetail implements java.io.Serializable {

    // Fields

    // ID
    private Long id;
    // 用户ID
    private Integer userId;
    // 平台; 0:ios ,1:andriod 
    private Integer platfrom;
    // 版本
    private String version;
    // 包名
    private String packageName;
    // 渠道名
    private String channelName;
    // 设备Id
    private String deviceId;
    // 设备名称
    private String deviceName;
    // 登录IP
    private String loginIp;
    // 登录地址
    private String loginAddress;
    // 经度
    private String loginLongitude;
    // 纬度
    private String loginLatitude;
    // 登录时间,如:2016-08-18 12:53:30
    private String loginTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserLoginDetail() {
    }

    /**
     * full constructor
     */
    public ReUserLoginDetail(Integer userId, Integer platfrom, String version, String packageName, String channelName, String deviceId, String deviceName, String loginIp, String loginAddress, String loginLongitude, String loginLatitude, String loginTime) {
        this.userId = userId;
        this.platfrom = platfrom;
        this.version = version;
        this.packageName = packageName;
        this.channelName = channelName;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.loginIp = loginIp;
        this.loginAddress = loginAddress;
        this.loginLongitude = loginLongitude;
        this.loginLatitude = loginLatitude;
        this.loginTime = loginTime;
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
     * 平台; 0:ios ,1:andriod 
     */
    public Integer getPlatfrom() {
        return this.platfrom;
    }

    /**
     * 平台; 0:ios ,1:andriod 
     */
    public void setPlatfrom(Integer platfrom) {
        this.platfrom = platfrom;
    }

    /**
     * 版本
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * 版本
     */
    public void setVersion(String version) {
        this.version = version;
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
     * 渠道名
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道名
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
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
     * 登录IP
     */
    public String getLoginIp() {
        return this.loginIp;
    }

    /**
     * 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 登录地址
     */
    public String getLoginAddress() {
        return this.loginAddress;
    }

    /**
     * 登录地址
     */
    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    /**
     * 经度
     */
    public String getLoginLongitude() {
        return this.loginLongitude;
    }

    /**
     * 经度
     */
    public void setLoginLongitude(String loginLongitude) {
        this.loginLongitude = loginLongitude;
    }

    /**
     * 纬度
     */
    public String getLoginLatitude() {
        return this.loginLatitude;
    }

    /**
     * 纬度
     */
    public void setLoginLatitude(String loginLatitude) {
        this.loginLatitude = loginLatitude;
    }

    /**
     * 登录时间,如:2016-08-18 12:53:30
     */
    public String getLoginTime() {
        return this.loginTime;
    }

    /**
     * 登录时间,如:2016-08-18 12:53:30
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

}