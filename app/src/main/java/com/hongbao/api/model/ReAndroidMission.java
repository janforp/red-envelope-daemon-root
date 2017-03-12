package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public class ReAndroidMission implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long missionId;
    // 应用名称
    private String appName;
    // 应用图标
    private String appIcon;
    // 应用标签
    private String appLabel;
    // 应用大小(MB)
    private String appSize;
    // 安装包链接
    private String appUrl;
    // 应用包名
    private String appPackage;
    // 应用短描述
    private String appDesc;
    // 附加任务激活时机, 0:当天, 1:次日
    private Integer auxiliaryTime;
    // 应用介绍
    private String appIntroduce;
    // 应用介绍图，图片的url,多张图片用;分开
    private String appImg;
    // 开始日期,如:2016-08-18
    private String startTime;
    // 结束日期,如:2016-08-18
    private String endTime;
    // 任务总数量
    private Integer totalNum;
    // 任务剩余数量
    private Integer leftNum;
    // 激活奖励
    private BigDecimal activateMoney;
    // 激活描述
    private String activateDesc;
    // 激活任务开始时间 如:09:00:00
    private String activateStartTime;
    // 激活任务结束时间 如:19:00:00
    private String activateEndTime;
    // 打开时长(秒)
    private Integer activateTime;
    // 权重大的靠前
    private Integer missionWeight;
    // 留存天数
    private Integer keepDay;
    // 附属任务个数
    private Integer auxiliaryNum;
    // 运营商限制, 如: 移动
    private String operatorLimit;
    // 地域限制, 如: 浙江
    private String addressLimit;
    // 是否需要sim卡，0:不需要，1:需要
    private Integer simLimit;
    // 是否限量, 0:否, 1:是
    private Integer numLimit;
    // 活动是否已经结束，0:已结束，1:进行中
    private Integer isEnd;
    // 逻辑删除，0:未删除，1:已删除
    private Integer isDelete;
    // 任务总奖励
    private BigDecimal totalMoney;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndroidMission() {
    }

    /**
     * full constructor
     */
    public ReAndroidMission(String appName, String appIcon, String appLabel, String appSize, String appUrl, String appPackage, String appDesc, Integer auxiliaryTime, String appIntroduce, String appImg, String startTime, String endTime, Integer totalNum, Integer leftNum, BigDecimal activateMoney, String activateDesc, String activateStartTime, String activateEndTime, Integer activateTime, Integer missionWeight, Integer keepDay, Integer auxiliaryNum, String operatorLimit, String addressLimit, Integer simLimit, Integer numLimit, Integer isEnd, Integer isDelete, BigDecimal totalMoney, String createTime, String updateTime) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.appLabel = appLabel;
        this.appSize = appSize;
        this.appUrl = appUrl;
        this.appPackage = appPackage;
        this.appDesc = appDesc;
        this.auxiliaryTime = auxiliaryTime;
        this.appIntroduce = appIntroduce;
        this.appImg = appImg;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalNum = totalNum;
        this.leftNum = leftNum;
        this.activateMoney = activateMoney;
        this.activateDesc = activateDesc;
        this.activateStartTime = activateStartTime;
        this.activateEndTime = activateEndTime;
        this.activateTime = activateTime;
        this.missionWeight = missionWeight;
        this.keepDay = keepDay;
        this.auxiliaryNum = auxiliaryNum;
        this.operatorLimit = operatorLimit;
        this.addressLimit = addressLimit;
        this.simLimit = simLimit;
        this.numLimit = numLimit;
        this.isEnd = isEnd;
        this.isDelete = isDelete;
        this.totalMoney = totalMoney;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * id，自增长
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 应用名称
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * 应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 应用图标
     */
    public String getAppIcon() {
        return this.appIcon;
    }

    /**
     * 应用图标
     */
    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    /**
     * 应用标签
     */
    public String getAppLabel() {
        return this.appLabel;
    }

    /**
     * 应用标签
     */
    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    /**
     * 应用大小(MB)
     */
    public String getAppSize() {
        return this.appSize;
    }

    /**
     * 应用大小(MB)
     */
    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    /**
     * 安装包链接
     */
    public String getAppUrl() {
        return this.appUrl;
    }

    /**
     * 安装包链接
     */
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    /**
     * 应用包名
     */
    public String getAppPackage() {
        return this.appPackage;
    }

    /**
     * 应用包名
     */
    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    /**
     * 应用短描述
     */
    public String getAppDesc() {
        return this.appDesc;
    }

    /**
     * 应用短描述
     */
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    /**
     * 附加任务激活时机, 0:当天, 1:次日
     */
    public Integer getAuxiliaryTime() {
        return this.auxiliaryTime;
    }

    /**
     * 附加任务激活时机, 0:当天, 1:次日
     */
    public void setAuxiliaryTime(Integer auxiliaryTime) {
        this.auxiliaryTime = auxiliaryTime;
    }

    /**
     * 应用介绍
     */
    public String getAppIntroduce() {
        return this.appIntroduce;
    }

    /**
     * 应用介绍
     */
    public void setAppIntroduce(String appIntroduce) {
        this.appIntroduce = appIntroduce;
    }

    /**
     * 应用介绍图，图片的url,多张图片用;分开
     */
    public String getAppImg() {
        return this.appImg;
    }

    /**
     * 应用介绍图，图片的url,多张图片用;分开
     */
    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    /**
     * 开始日期,如:2016-08-18
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 开始日期,如:2016-08-18
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束日期,如:2016-08-18
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 结束日期,如:2016-08-18
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 任务总数量
     */
    public Integer getTotalNum() {
        return this.totalNum;
    }

    /**
     * 任务总数量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 任务剩余数量
     */
    public Integer getLeftNum() {
        return this.leftNum;
    }

    /**
     * 任务剩余数量
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    /**
     * 激活奖励
     */
    public BigDecimal getActivateMoney() {
        return this.activateMoney;
    }

    /**
     * 激活奖励
     */
    public void setActivateMoney(BigDecimal activateMoney) {
        this.activateMoney = activateMoney;
    }

    /**
     * 激活描述
     */
    public String getActivateDesc() {
        return this.activateDesc;
    }

    /**
     * 激活描述
     */
    public void setActivateDesc(String activateDesc) {
        this.activateDesc = activateDesc;
    }

    /**
     * 激活任务开始时间 如:09:00:00
     */
    public String getActivateStartTime() {
        return this.activateStartTime;
    }

    /**
     * 激活任务开始时间 如:09:00:00
     */
    public void setActivateStartTime(String activateStartTime) {
        this.activateStartTime = activateStartTime;
    }

    /**
     * 激活任务结束时间 如:19:00:00
     */
    public String getActivateEndTime() {
        return this.activateEndTime;
    }

    /**
     * 激活任务结束时间 如:19:00:00
     */
    public void setActivateEndTime(String activateEndTime) {
        this.activateEndTime = activateEndTime;
    }

    /**
     * 打开时长(秒)
     */
    public Integer getActivateTime() {
        return this.activateTime;
    }

    /**
     * 打开时长(秒)
     */
    public void setActivateTime(Integer activateTime) {
        this.activateTime = activateTime;
    }

    /**
     * 权重大的靠前
     */
    public Integer getMissionWeight() {
        return this.missionWeight;
    }

    /**
     * 权重大的靠前
     */
    public void setMissionWeight(Integer missionWeight) {
        this.missionWeight = missionWeight;
    }

    /**
     * 留存天数
     */
    public Integer getKeepDay() {
        return this.keepDay;
    }

    /**
     * 留存天数
     */
    public void setKeepDay(Integer keepDay) {
        this.keepDay = keepDay;
    }

    /**
     * 附属任务个数
     */
    public Integer getAuxiliaryNum() {
        return this.auxiliaryNum;
    }

    /**
     * 附属任务个数
     */
    public void setAuxiliaryNum(Integer auxiliaryNum) {
        this.auxiliaryNum = auxiliaryNum;
    }

    /**
     * 运营商限制, 如: 移动
     */
    public String getOperatorLimit() {
        return this.operatorLimit;
    }

    /**
     * 运营商限制, 如: 移动
     */
    public void setOperatorLimit(String operatorLimit) {
        this.operatorLimit = operatorLimit;
    }

    /**
     * 地域限制, 如: 浙江
     */
    public String getAddressLimit() {
        return this.addressLimit;
    }

    /**
     * 地域限制, 如: 浙江
     */
    public void setAddressLimit(String addressLimit) {
        this.addressLimit = addressLimit;
    }

    /**
     * 是否需要sim卡，0:不需要，1:需要
     */
    public Integer getSimLimit() {
        return this.simLimit;
    }

    /**
     * 是否需要sim卡，0:不需要，1:需要
     */
    public void setSimLimit(Integer simLimit) {
        this.simLimit = simLimit;
    }

    /**
     * 是否限量, 0:否, 1:是
     */
    public Integer getNumLimit() {
        return this.numLimit;
    }

    /**
     * 是否限量, 0:否, 1:是
     */
    public void setNumLimit(Integer numLimit) {
        this.numLimit = numLimit;
    }

    /**
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public Integer getIsEnd() {
        return this.isEnd;
    }

    /**
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * 逻辑删除，0:未删除，1:已删除
     */
    public Integer getIsDelete() {
        return this.isDelete;
    }

    /**
     * 逻辑删除，0:未删除，1:已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 任务总奖励
     */
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    /**
     * 任务总奖励
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}