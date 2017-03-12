package com.hongbao.api.model.vo;

import com.hongbao.api.model.dto.AndroidAuxiliaryInfo;
import com.hongbao.api.model.dto.AndroidDepthInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2017/1/5.
 */
public class AndroidIntegralVo implements Serializable {

    // id，自增长
    private Long missionId;
    // 安装包链接
    private String appUrl;
    // 应用包名
    private String appPackage;
    // 应用图标
    private String appIcon;
    // 应用名称
    private String appName;
    // 应用大小(MB)
    private String appSize;
    // 总奖励
    private String totalMoney;
    // 已赚
    private String gainMoney;
    // 应用介绍图
    private List<String> appImg;
    // 应用介绍
    private String appIntroduce;
    // 任务步骤
    private List<AndroidDepthInfo> missionList;
    // 附属任务
    private List<AndroidAuxiliaryInfo> auxiliaryList;

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getGainMoney() {
        return gainMoney;
    }

    public void setGainMoney(String gainMoney) {
        this.gainMoney = gainMoney;
    }

    public List<String> getAppImg() {
        return appImg;
    }

    public void setAppImg(List<String> appImg) {
        this.appImg = appImg;
    }

    public String getAppIntroduce() {
        return appIntroduce;
    }

    public void setAppIntroduce(String appIntroduce) {
        this.appIntroduce = appIntroduce;
    }

    public List<AndroidDepthInfo> getMissionList() {
        return missionList;
    }

    public void setMissionList(List<AndroidDepthInfo> missionList) {
        this.missionList = missionList;
    }

    public List<AndroidAuxiliaryInfo> getAuxiliaryList() {
        return auxiliaryList;
    }

    public void setAuxiliaryList(List<AndroidAuxiliaryInfo> auxiliaryList) {
        this.auxiliaryList = auxiliaryList;
    }

}
