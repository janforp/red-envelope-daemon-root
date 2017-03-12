package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/10/27.
 */
public class MyMissionInfo implements Serializable {

    // id
    private Long taskId;
    // 图标
    private String missionIcon;
    // 标题
    private String missionTitle;
    // 状态值 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
    private int taskStatus;
    // 状态内容
    private String statusContent;
    // 备注
    private String verifyRemarks;
    // 跳转链接
    private String missionUrl;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }

    public String getVerifyRemarks() {
        return verifyRemarks;
    }

    public void setVerifyRemarks(String verifyRemarks) {
        this.verifyRemarks = verifyRemarks;
    }

    public String getMissionUrl() {
        return missionUrl;
    }

    public void setMissionUrl(String missionUrl) {
        this.missionUrl = missionUrl;
    }

}
