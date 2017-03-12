package com.hongbao.api.model.dto;

public class GreatMissionInfo implements java.io.Serializable {

    // id
    private Long missionId;
    // 任务图标
    private String missionIcon;
    // 任务名字
    private String missionTitle;
    // 任务奖励
    private String missionReward;
    // 商家图标
    private String merchantIcon;
    // 商家名称
    private String merchantName;
    // 参加人数
    private Integer participantsNum;
    // 结束时间,如:2016-08-18 12:53:30
    private String endTime;
    // 剩余数量
    private Integer leftNum;
    // 进行中 0-否  1-是
    private Integer isEnd;
    // 任务id
    private Long taskId;
    // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过
    private Integer taskStatus;

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

    public String getMissionReward() {
        return missionReward;
    }

    public void setMissionReward(String missionReward) {
        this.missionReward = missionReward;
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

    public Integer getParticipantsNum() {
        return participantsNum;
    }

    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}