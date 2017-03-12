package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-11
 */
public class RePackageChannelUpdate implements java.io.Serializable {

    // Fields

    // 设备Id
    private String deviceId;
    // 如:Wandoujia
    private String channelName;
    // 如:com.wj.hongbao
    private String packageName;
    // 版本号
    private Integer versionCode;
    // 推送时间,如:2016-08-18 12:53:30
    private String pushTime;

    // Constructors

    /**
     * default constructor
     */
    public RePackageChannelUpdate() {
    }

    /**
     * full constructor
     */
    public RePackageChannelUpdate(String deviceId, String channelName, String packageName, Integer versionCode, String pushTime) {
        this.deviceId = deviceId;
        this.channelName = channelName;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.pushTime = pushTime;
    }

    // Property accessors

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
     * 如:Wandoujia
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 如:Wandoujia
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 如:com.wj.hongbao
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 如:com.wj.hongbao
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 版本号
     */
    public Integer getVersionCode() {
        return this.versionCode;
    }

    /**
     * 版本号
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * 推送时间,如:2016-08-18 12:53:30
     */
    public String getPushTime() {
        return this.pushTime;
    }

    /**
     * 推送时间,如:2016-08-18 12:53:30
     */
    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

}