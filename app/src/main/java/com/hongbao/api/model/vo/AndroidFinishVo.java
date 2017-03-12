package com.hongbao.api.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 2017/1/5.
 */
public class AndroidFinishVo implements Serializable {

    // 图标
    private String missionIcon;
    // 标题
    private String missionTitle;
    // 总金额
    private String totalMoney;
    // 已赚
    private String gainMoney;
    // 跳转链接
    private String missionUrl;
    // 时间
    private String updateTime;

    public String getMissionIcon() {
        return missionIcon;
    }

    public void setMissionIcon(String missionIcon) {
        this.missionIcon = missionIcon;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
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

    public String getMissionUrl() {
        return missionUrl;
    }

    public void setMissionUrl(String missionUrl) {
        this.missionUrl = missionUrl;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
