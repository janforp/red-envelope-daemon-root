package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-27
 */
public class ReDianjoyNotify implements java.io.Serializable {

    // Fields

    // ID
    private Long id;
    // 用户ID
    private Integer userid;
    // 设备Id
    private String deviceId;
    // 在点乐的应用所对应的DIANLE_APP_ID值
    private String appId;
    // 积分，注意：不是钱
    private Integer currency;
    // 汇率：1分钱=多少积分(>=1)
    private Integer appRatio;
    // 时间戳
    private String timeStamp;
    // 广告名
    private String adName;
    // 包名
    private String packName;
    // 深度任务的唯一标识符
    private String taskId;
    // 表示广告任务的类型 1-安装激活任务 4-次日打开深度任务
    private String tradeType;
    // 回调时间,如:2016-08-18 12:53:30
    private String notifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReDianjoyNotify() {
    }

    /**
     * full constructor
     */
    public ReDianjoyNotify(Integer userid, String deviceId, String appId, Integer currency, Integer appRatio, String timeStamp, String adName, String packName, String taskId, String tradeType, String notifyTime) {
        this.userid = userid;
        this.deviceId = deviceId;
        this.appId = appId;
        this.currency = currency;
        this.appRatio = appRatio;
        this.timeStamp = timeStamp;
        this.adName = adName;
        this.packName = packName;
        this.taskId = taskId;
        this.tradeType = tradeType;
        this.notifyTime = notifyTime;
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
    public Integer getUserid() {
        return this.userid;
    }

    /**
     * 用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
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
     * 在点乐的应用所对应的DIANLE_APP_ID值
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * 在点乐的应用所对应的DIANLE_APP_ID值
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 积分，注意：不是钱
     */
    public Integer getCurrency() {
        return this.currency;
    }

    /**
     * 积分，注意：不是钱
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * 汇率：1分钱=多少积分(>=1)
     */
    public Integer getAppRatio() {
        return this.appRatio;
    }

    /**
     * 汇率：1分钱=多少积分(>=1)
     */
    public void setAppRatio(Integer appRatio) {
        this.appRatio = appRatio;
    }

    /**
     * 时间戳
     */
    public String getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * 时间戳
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * 广告名
     */
    public String getAdName() {
        return this.adName;
    }

    /**
     * 广告名
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * 包名
     */
    public String getPackName() {
        return this.packName;
    }

    /**
     * 包名
     */
    public void setPackName(String packName) {
        this.packName = packName;
    }

    /**
     * 深度任务的唯一标识符
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 深度任务的唯一标识符
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 表示广告任务的类型 1-安装激活任务 4-次日打开深度任务
     */
    public String getTradeType() {
        return this.tradeType;
    }

    /**
     * 表示广告任务的类型 1-安装激活任务 4-次日打开深度任务
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