package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2017/1/5.
 */
public class AndroidAuxiliaryInfo implements Serializable {

    // 任务编号
    private Integer missionNo;
    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交 4-可做
    private Integer missionStatus;
    // 任务标签
    private String missionLabel;
    // 任务标题
    private String missionTitle;
    // 任务奖励
    private String missionMoney;
    // 任务详情
    private String missionDesc;
    // 图片
    private List<String> missionImg;
    // 审核备注
    private String checkText;
    // 审核要求
    private String checkRequire;

    public String getCheckRequire() {
        return checkRequire;
    }

    public void setCheckRequire(String checkRequire) {
        this.checkRequire = checkRequire;
    }

    public Integer getMissionNo() {
        return missionNo;
    }

    public void setMissionNo(Integer missionNo) {
        this.missionNo = missionNo;
    }

    public Integer getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    public String getMissionLabel() {
        return missionLabel;
    }

    public void setMissionLabel(String missionLabel) {
        this.missionLabel = missionLabel;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public String getMissionMoney() {
        return missionMoney;
    }

    public void setMissionMoney(String missionMoney) {
        this.missionMoney = missionMoney;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public List<String> getMissionImg() {
        return missionImg;
    }

    public void setMissionImg(List<String> missionImg) {
        this.missionImg = missionImg;
    }

    public String getCheckText() {
        return checkText;
    }

    public void setCheckText(String checkText) {
        this.checkText = checkText;
    }
}
