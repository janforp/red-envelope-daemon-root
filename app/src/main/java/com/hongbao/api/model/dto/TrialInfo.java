package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Summer on 2016/11/15.
 */
public class TrialInfo implements Serializable {

    // 任务id
    private Long missionId;
    // 任务类型
    private Integer missionType;
    // 任务标题
    private String missionTitle;
    // 任务图标
    private String missionIcon;
    // 金额
    private BigDecimal missionMoney;
    // app包名
    private String appPackage;
    // app下载链接
    private String appUrl;
    // 市场名
    private String appMarket;
    // 市场url
    private String marketUrl;
    // 市场包名
    private String marketPackage;
    // 标签
    private String appLabel;
    // 任务描述
    private String missionDesc;
    // 开始时间
    private String startTime;
    // 剩余数量
    private Integer leftNum;
    // appId
    private Long appId;
    // 打开时间(秒)
    private Integer openSecond;
    // 第一步描述
    private String stepOneDesc;
    // 第一步金额
    private BigDecimal stepOneMoney;

    public BigDecimal getStepOneMoney() {
        return stepOneMoney;
    }

    public void setStepOneMoney(BigDecimal stepOneMoney) {
        this.stepOneMoney = stepOneMoney;
    }

    public String getStepOneDesc() {
        return stepOneDesc;
    }

    public void setStepOneDesc(String stepOneDesc) {
        this.stepOneDesc = stepOneDesc;
    }

    public Integer getOpenSecond() {
        return openSecond;
    }

    public void setOpenSecond(Integer openSecond) {
        this.openSecond = openSecond;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getMissionMoney() {
        return missionMoney;
    }

    public void setMissionMoney(BigDecimal missionMoney) {
        this.missionMoney = missionMoney;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Integer getMissionType() {
        return missionType;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public String getMissionIcon() {
        return missionIcon;
    }

    public void setMissionIcon(String missionIcon) {
        this.missionIcon = missionIcon;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppMarket() {
        return appMarket;
    }

    public void setAppMarket(String appMarket) {
        this.appMarket = appMarket;
    }

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public String getMarketPackage() {
        return marketPackage;
    }

    public void setMarketPackage(String marketPackage) {
        this.marketPackage = marketPackage;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }
}
