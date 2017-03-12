package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2017/1/5.
 */
public class AndroidDepthInfo implements Serializable {

    // id
    private int depthId;
    // 状态(步骤颜色) 0-置灰 1-正常
    private int stepStatus;
    // 标签
    private String label;
    // 标题
    private String title;
    // 奖励
    private String award;
    // 描述
    private String desc;
    // 体验时长(秒)
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDepthId() {
        return depthId;
    }

    public void setDepthId(int depthId) {
        this.depthId = depthId;
    }

    public int getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(int stepStatus) {
        this.stepStatus = stepStatus;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
