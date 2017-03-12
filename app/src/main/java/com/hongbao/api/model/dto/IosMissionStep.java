package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2017/1/10.
 */
public class IosMissionStep implements Serializable {

    // 步骤编号
    private Integer stepId;
    // 任务标题
    private String missionTitle;
    // 任务描述
    private String missionDesc;
    // 任务奖励
    private String missionMoney;
    // 图片
    private List<String> missionImg;
    // 审核要求
    private String checkRequire;
    // 是否有按钮，0:无，1:有
    private Integer isBtn;
    // 按钮标题
    private String btnTitle;
    // 按钮链接
    private String btnUrl;
    // 审核备注
    private String checkText;
    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交 4-可做
    private Integer missionStatus;

    public Integer getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
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

    public String getMissionMoney() {
        return missionMoney;
    }

    public void setMissionMoney(String missionMoney) {
        this.missionMoney = missionMoney;
    }

    public List<String> getMissionImg() {
        return missionImg;
    }

    public void setMissionImg(List<String> missionImg) {
        this.missionImg = missionImg;
    }

    public String getCheckRequire() {
        return checkRequire;
    }

    public void setCheckRequire(String checkRequire) {
        this.checkRequire = checkRequire;
    }

    public Integer getIsBtn() {
        return isBtn;
    }

    public void setIsBtn(Integer isBtn) {
        this.isBtn = isBtn;
    }

    public String getBtnTitle() {
        return btnTitle;
    }

    public void setBtnTitle(String btnTitle) {
        this.btnTitle = btnTitle;
    }

    public String getBtnUrl() {
        return btnUrl;
    }

    public void setBtnUrl(String btnUrl) {
        this.btnUrl = btnUrl;
    }

    public String getCheckText() {
        return checkText;
    }

    public void setCheckText(String checkText) {
        this.checkText = checkText;
    }
}
