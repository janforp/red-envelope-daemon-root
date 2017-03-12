package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-07
 */
public class ReAndriodIntegralWall implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long wallId;
    // 应用名称
    private String appName;
    // 安装包链接
    private String appUrl;
    // 应用图标
    private String appIcon;
    // 应用包名
    private String appPackage;
    // 应用简短描述
    private String appDesc;
    // 应用大小(MB)
    private String appSize;
    // 应用标签
    private String appLabel;
    // 应用介绍
    private String appIntroduce;
    // 应用介绍图，图片的url,多张图片用;分开
    private String appImg;
    // 开始时间,如:2016-08-18 12:53:30
    private String startTime;
    // 结束时间,如:2016-08-18 12:53:30
    private String endTime;
    // 是否限量, 0:否, 1:是
    private Integer isLimitNum;
    // 任务总数量
    private Integer totalNum;
    // 任务剩余数量
    private Integer leftNum;
    // 任务总奖励
    private BigDecimal totalMoney;
    // 第一步总奖励
    private BigDecimal stepOneMoney;
    // 步骤描述
    private String stepOneDesc;
    // 第一步打开时间(秒)
    private Integer stepOneSecond;
    // 第二步每天奖励
    private BigDecimal stepTwoMoney;
    // 第二步天数
    private Integer stepTwoDay;
    // 第二步打开时间(秒)
    private Integer stepTwoSecond;
    // 权重大的靠前
    private Integer wallWeight;
    // 活动是否已经结束，0:已结束，1:进行中
    private Integer isEnd;
    // 是否需要sim卡，0:不需要，1:需要
    private Integer isSim;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndriodIntegralWall() {
    }

    /**
     * full constructor
     */
    public ReAndriodIntegralWall(String appName, String appUrl, String appIcon, String appPackage, String appDesc, String appSize, String appLabel, String appIntroduce, String appImg, String startTime, String endTime, Integer isLimitNum, Integer totalNum, Integer leftNum, BigDecimal totalMoney, BigDecimal stepOneMoney, String stepOneDesc, Integer stepOneSecond, BigDecimal stepTwoMoney, Integer stepTwoDay, Integer stepTwoSecond, Integer wallWeight, Integer isEnd, Integer isSim, String createTime, String updateTime) {
        this.appName = appName;
        this.appUrl = appUrl;
        this.appIcon = appIcon;
        this.appPackage = appPackage;
        this.appDesc = appDesc;
        this.appSize = appSize;
        this.appLabel = appLabel;
        this.appIntroduce = appIntroduce;
        this.appImg = appImg;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isLimitNum = isLimitNum;
        this.totalNum = totalNum;
        this.leftNum = leftNum;
        this.totalMoney = totalMoney;
        this.stepOneMoney = stepOneMoney;
        this.stepOneDesc = stepOneDesc;
        this.stepOneSecond = stepOneSecond;
        this.stepTwoMoney = stepTwoMoney;
        this.stepTwoDay = stepTwoDay;
        this.stepTwoSecond = stepTwoSecond;
        this.wallWeight = wallWeight;
        this.isEnd = isEnd;
        this.isSim = isSim;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getWallId() {
        return this.wallId;
    }

    /**
     * id，自增长
     */
    public void setWallId(Long wallId) {
        this.wallId = wallId;
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
     * 应用简短描述
     */
    public String getAppDesc() {
        return this.appDesc;
    }

    /**
     * 应用简短描述
     */
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
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
     * 开始时间,如:2016-08-18 12:53:30
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 开始时间,如:2016-08-18 12:53:30
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间,如:2016-08-18 12:53:30
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 结束时间,如:2016-08-18 12:53:30
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 是否限量, 0:否, 1:是
     */
    public Integer getIsLimitNum() {
        return this.isLimitNum;
    }

    /**
     * 是否限量, 0:否, 1:是
     */
    public void setIsLimitNum(Integer isLimitNum) {
        this.isLimitNum = isLimitNum;
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
     * 第一步总奖励
     */
    public BigDecimal getStepOneMoney() {
        return this.stepOneMoney;
    }

    /**
     * 第一步总奖励
     */
    public void setStepOneMoney(BigDecimal stepOneMoney) {
        this.stepOneMoney = stepOneMoney;
    }

    /**
     * 步骤描述
     */
    public String getStepOneDesc() {
        return this.stepOneDesc;
    }

    /**
     * 步骤描述
     */
    public void setStepOneDesc(String stepOneDesc) {
        this.stepOneDesc = stepOneDesc;
    }

    /**
     * 第一步打开时间(秒)
     */
    public Integer getStepOneSecond() {
        return this.stepOneSecond;
    }

    /**
     * 第一步打开时间(秒)
     */
    public void setStepOneSecond(Integer stepOneSecond) {
        this.stepOneSecond = stepOneSecond;
    }

    /**
     * 第二步每天奖励
     */
    public BigDecimal getStepTwoMoney() {
        return this.stepTwoMoney;
    }

    /**
     * 第二步每天奖励
     */
    public void setStepTwoMoney(BigDecimal stepTwoMoney) {
        this.stepTwoMoney = stepTwoMoney;
    }

    /**
     * 第二步天数
     */
    public Integer getStepTwoDay() {
        return this.stepTwoDay;
    }

    /**
     * 第二步天数
     */
    public void setStepTwoDay(Integer stepTwoDay) {
        this.stepTwoDay = stepTwoDay;
    }

    /**
     * 第二步打开时间(秒)
     */
    public Integer getStepTwoSecond() {
        return this.stepTwoSecond;
    }

    /**
     * 第二步打开时间(秒)
     */
    public void setStepTwoSecond(Integer stepTwoSecond) {
        this.stepTwoSecond = stepTwoSecond;
    }

    /**
     * 权重大的靠前
     */
    public Integer getWallWeight() {
        return this.wallWeight;
    }

    /**
     * 权重大的靠前
     */
    public void setWallWeight(Integer wallWeight) {
        this.wallWeight = wallWeight;
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
     * 是否需要sim卡，0:不需要，1:需要
     */
    public Integer getIsSim() {
        return this.isSim;
    }

    /**
     * 是否需要sim卡，0:不需要，1:需要
     */
    public void setIsSim(Integer isSim) {
        this.isSim = isSim;
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