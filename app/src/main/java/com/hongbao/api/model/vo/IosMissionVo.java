package com.hongbao.api.model.vo;

import com.hongbao.api.model.dto.IosMissionStep;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2017/1/10.
 */
public class IosMissionVo implements Serializable {

    // id，自增长
    private Long missionId;
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
    private List<IosMissionStep> stepList;

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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

    public List<IosMissionStep> getStepList() {
        return stepList;
    }

    public void setStepList(List<IosMissionStep> stepList) {
        this.stepList = stepList;
    }
}
