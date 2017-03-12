package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-07
 */
public class ReModuleUserInfo implements java.io.Serializable {

    // Fields

    // 信息id，自增长
    private Long infoId;
    // 任务类型
    private Integer missionType;
    // 任务子类型
    private Integer missionSubtype;
    // 任务id
    private Long missionId;
    // 用户id
    private Integer userId;
    // 平台; 0:ios ,1:andriod 
    private Integer platfrom;
    // 设备Id
    private String deviceId;
    // 设备名称
    private String deviceName;
    // 包名
    private String packageName;
    // 渠道
    private String channelName;
    // app版本
    private String appVersion;
    // 用户ip
    private String userIp;
    // 国际移动用户识别码imsi
    private String userImsi;
    // 国际移动设备身份码imei
    private String userImei;
    // sim卡序列号
    private String simNum;
    private String mobileNum;
    // 领取时间,如:2016-08-18 12:53:30
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public ReModuleUserInfo() {
    }

    /**
     * full constructor
     */
    public ReModuleUserInfo(Integer missionType, Integer missionSubtype, Long missionId, Integer userId, Integer platfrom, String deviceId, String deviceName, String packageName, String channelName, String appVersion, String userIp, String userImsi, String userImei, String simNum, String mobileNum, String createTime) {
        this.missionType = missionType;
        this.missionSubtype = missionSubtype;
        this.missionId = missionId;
        this.userId = userId;
        this.platfrom = platfrom;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.packageName = packageName;
        this.channelName = channelName;
        this.appVersion = appVersion;
        this.userIp = userIp;
        this.userImsi = userImsi;
        this.userImei = userImei;
        this.simNum = simNum;
        this.mobileNum = mobileNum;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * 信息id，自增长
     */
    public Long getInfoId() {
        return this.infoId;
    }

    /**
     * 信息id，自增长
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    /**
     * 任务类型
     */
    public Integer getMissionType() {
        return this.missionType;
    }

    /**
     * 任务类型
     */
    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    /**
     * 任务子类型
     */
    public Integer getMissionSubtype() {
        return this.missionSubtype;
    }

    /**
     * 任务子类型
     */
    public void setMissionSubtype(Integer missionSubtype) {
        this.missionSubtype = missionSubtype;
    }

    /**
     * 任务id
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务id
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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
     * 用户ip
     */
    public String getUserIp() {
        return this.userIp;
    }

    /**
     * 用户ip
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

    /**
     * sim卡序列号
     */
    public String getSimNum() {
        return this.simNum;
    }

    /**
     * sim卡序列号
     */
    public void setSimNum(String simNum) {
        this.simNum = simNum;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    /**
     * 领取时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 领取时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}