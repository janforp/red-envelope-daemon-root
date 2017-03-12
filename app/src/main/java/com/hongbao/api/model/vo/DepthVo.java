package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 2016/12/5.
 */
public class DepthVo implements Serializable {

    // 任务id
    private Long missionId;
    // 任务标题
    private String missionTitle;
    // 任务图标
    private String missionIcon;
    // app包名
    private String appPackage;
    // app下载链接
    private String appUrl;
    // 打开时间(秒)
    private Integer openSecond;
    // 奖励(元)
    private BigDecimal money;

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Integer getOpenSecond() {
        return openSecond;
    }

    public void setOpenSecond(Integer openSecond) {
        this.openSecond = openSecond;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
