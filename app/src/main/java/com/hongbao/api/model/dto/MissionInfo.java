package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/12/10.
 */
public class MissionInfo implements Serializable {

    // id
    private Long missionId;
    // 图标
    private String missionIcon;
    // 标题
    private String missionTitle;
    // 描述
    private String missionDesc;
    // 商家图标
    private String merchantIcon;
    // 商家名称
    private String merchantName;
    // 参与人数
    private String participantsNum;
    // 状态 0-进行中 1-审核中 2-审核通过 3-未通过 4-已结束 5-未领取
    private int missionStatus;
    // 状态内容
    private String statusContent;
    // 跳转链接
    private String missionUrl;

    public String getMissionUrl() {
        return missionUrl;
    }

    public void setMissionUrl(String missionUrl) {
        this.missionUrl = missionUrl;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

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

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public String getMerchantIcon() {
        return merchantIcon;
    }

    public void setMerchantIcon(String merchantIcon) {
        this.merchantIcon = merchantIcon;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getParticipantsNum() {
        return participantsNum;
    }

    public void setParticipantsNum(String participantsNum) {
        this.participantsNum = participantsNum;
    }

    public int getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(int missionStatus) {
        this.missionStatus = missionStatus;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }
}
