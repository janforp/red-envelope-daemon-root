package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public class ReIosMission implements java.io.Serializable {

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
    // 应用短描述
    private String appDesc;
    // 应用介绍
    private String appIntroduce;
    // 应用介绍图，图片的url,多张图片用;分开
    private String appImg;
    // 开始时间,如:2016-08-18 12:53:30
    private String startTime;
    // 结束时间,如:2016-08-18 12:53:30
    private String endTime;
    // 权重大的靠前
    private Integer missionWeight;
    // 任务个数
    private Integer stepNum;
    // 运营商限制, 如: 移动
    private String operatorLimit;
    // 地域限制, 如: 浙江
    private String addressLimit;
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
    public ReIosMission() {
    }

    /**
     * full constructor
     */
    public ReIosMission(String appName, String appIcon, String appLabel, String appSize, String appUrl, String appDesc, String appIntroduce, String appImg, String startTime, String endTime, Integer missionWeight, Integer stepNum, String operatorLimit, String addressLimit, Integer isEnd, Integer isDelete, BigDecimal totalMoney, String createTime, String updateTime) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.appLabel = appLabel;
        this.appSize = appSize;
        this.appUrl = appUrl;
        this.appDesc = appDesc;
        this.appIntroduce = appIntroduce;
        this.appImg = appImg;
        this.startTime = startTime;
        this.endTime = endTime;
        this.missionWeight = missionWeight;
        this.stepNum = stepNum;
        this.operatorLimit = operatorLimit;
        this.addressLimit = addressLimit;
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
     * 任务个数
     */
    public Integer getStepNum() {
        return this.stepNum;
    }

    /**
     * 任务个数
     */
    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
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