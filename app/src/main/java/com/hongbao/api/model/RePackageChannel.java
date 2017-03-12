package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-29
 */
public class RePackageChannel implements java.io.Serializable {

    // Fields

    // 如:红包达人
    private String appName;
    // 如:Wandoujia
    private String channelName;
    // 如:com.wj.hongbao
    private String packageName;
    // 如:1.0.0
    private String appVersion;
    // 如:http://dev.image.lswuyou.cn/hongbao/0611389f-3300-4cfe-a40a-0e7448c25f3a
    private String appIcon;
    // 状态,如 0-审核中, 1-已通过,2-未通过
    private Integer status;
    // 备注
    private String notes;
    // 上传时间,如:2016-08-18 12:53:30
    private String uploadTime;
    // 版本号
    private Integer versionCode;
    // apk链接
    private String apkUrl;
    // 更新说明
    private String updateRemark;
    // 是否强制更新 0-否， 1-是
    private Integer isForce;
    // app的渠道版本id
    private Integer appId;

    // Constructors

    /**
     * default constructor
     */
    public RePackageChannel() {
    }

    /**
     * full constructor
     */
    public RePackageChannel(String appName, String channelName, String packageName, String appVersion, String appIcon, Integer status, String notes, String uploadTime, Integer versionCode, String apkUrl, String updateRemark, Integer isForce, Integer appId) {
        this.appName = appName;
        this.channelName = channelName;
        this.packageName = packageName;
        this.appVersion = appVersion;
        this.appIcon = appIcon;
        this.status = status;
        this.notes = notes;
        this.uploadTime = uploadTime;
        this.versionCode = versionCode;
        this.apkUrl = apkUrl;
        this.updateRemark = updateRemark;
        this.isForce = isForce;
        this.appId = appId;
    }

    // Property accessors

    /**
     * 如:红包达人
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * 如:红包达人
     */
    public void setAppName(String appName) {
        this.appName = appName;
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
     * 如:1.0.0
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * 如:1.0.0
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 如:http://dev.image.lswuyou.cn/hongbao/0611389f-3300-4cfe-a40a-0e7448c25f3a
     */
    public String getAppIcon() {
        return this.appIcon;
    }

    /**
     * 如:http://dev.image.lswuyou.cn/hongbao/0611389f-3300-4cfe-a40a-0e7448c25f3a
     */
    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    /**
     * 状态,如 0-审核中, 1-已通过,2-未通过
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 状态,如 0-审核中, 1-已通过,2-未通过
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     */
    public String getNotes() {
        return this.notes;
    }

    /**
     * 备注
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 上传时间,如:2016-08-18 12:53:30
     */
    public String getUploadTime() {
        return this.uploadTime;
    }

    /**
     * 上传时间,如:2016-08-18 12:53:30
     */
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
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
     * apk链接
     */
    public String getApkUrl() {
        return this.apkUrl;
    }

    /**
     * apk链接
     */
    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    /**
     * 更新说明
     */
    public String getUpdateRemark() {
        return this.updateRemark;
    }

    /**
     * 更新说明
     */
    public void setUpdateRemark(String updateRemark) {
        this.updateRemark = updateRemark;
    }

    /**
     * 是否强制更新 0-否， 1-是
     */
    public Integer getIsForce() {
        return this.isForce;
    }

    /**
     * 是否强制更新 0-否， 1-是
     */
    public void setIsForce(Integer isForce) {
        this.isForce = isForce;
    }

    /**
     * app的渠道版本id
     */
    public Integer getAppId() {
        return this.appId;
    }

    /**
     * app的渠道版本id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

}