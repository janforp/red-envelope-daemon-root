package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/11/15.
 */
public class TrialVo implements Serializable {

    // 任务id
    private Long missionId;
    // 任务类型 0-ASO, 1-积分墙
    private Integer missionType;
    // 任务标题
    private String missionTitle;
    // 任务图标
    private String missionIcon;
    // app包名
    private String appPackage;
    // 市场名
    private String appMarket;
    // 市场url
    private String marketUrl;
    // 市场包名
    private String marketPackage;
    // 标签
    private List<String> appLabel;
    // 状态 0-进行中, 1-已完成, 2-显示金额
    private Integer missionStatus;
    // 状态内容
    private String statusContent;
    // 任务描述
    private String missionDesc;
    // 详情页链接
    private String detailUrl;

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

    public List<String> getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(List<String> appLabel) {
        this.appLabel = appLabel;
    }

    public Integer getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
