package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 2017/1/16.
 */
public class AndroidFinishInfo implements Serializable {

    // id
    private Long missionId;
    // 标题
    private String appName;
    // 图标
    private String appIcon;
    // 总金额
    private BigDecimal totalMoney;
    // 已赚
    private BigDecimal gainMoney;
    // 时间
    private String updateTime;
    // 结束日期
    private String endDay;

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getGainMoney() {
        return gainMoney;
    }

    public void setGainMoney(BigDecimal gainMoney) {
        this.gainMoney = gainMoney;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
