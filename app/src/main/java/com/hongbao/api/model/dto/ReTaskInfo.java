package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/28.
 */
public class ReTaskInfo implements Serializable {

    // id
    private Long taskId;
    // 任务id
    private Long missionId;
    // 任务图标
    private String missionIcon;
    // 任务名字
    private String missionTitle;
    // 任务标签
    private String missionLabel;
    // 剩余
    private Integer leftNum;
    // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
    private Integer taskStatus;
    // 审核备注
    private String verifyRemarks;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public String getMissionLabel() {
        return missionLabel;
    }

    public void setMissionLabel(String missionLabel) {
        this.missionLabel = missionLabel;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getVerifyRemarks() {
        return verifyRemarks;
    }

    public void setVerifyRemarks(String verifyRemarks) {
        this.verifyRemarks = verifyRemarks;
    }
}
