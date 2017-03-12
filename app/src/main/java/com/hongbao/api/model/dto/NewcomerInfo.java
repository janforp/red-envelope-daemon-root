package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/11/16.
 */
public class NewcomerInfo implements Serializable {

    // 任务id
    private long missionId;
    // 任务内容
    private String missionContent;
    // 任务奖励
    private String missionAward;
    // 任务状态 0-未完成 1-已完成
    private int missionStatus;
    // 任务类型
    private int missionType;

    public long getMissionId() {
        return missionId;
    }

    public void setMissionId(long missionId) {
        this.missionId = missionId;
    }

    public String getMissionContent() {
        return missionContent;
    }

    public void setMissionContent(String missionContent) {
        this.missionContent = missionContent;
    }

    public String getMissionAward() {
        return missionAward;
    }

    public void setMissionAward(String missionAward) {
        this.missionAward = missionAward;
    }

    public int getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(int missionStatus) {
        this.missionStatus = missionStatus;
    }

    public int getMissionType() {
        return missionType;
    }

    public void setMissionType(int missionType) {
        this.missionType = missionType;
    }
}
