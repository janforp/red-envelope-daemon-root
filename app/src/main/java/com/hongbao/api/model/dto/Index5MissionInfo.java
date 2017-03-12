package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2017/1/5.
 */
public class Index5MissionInfo implements Serializable {

    // 图标
    private String icon;
    // 标题
    private String title;
    // 描述
    private String desc;
    // 奖励
    private String award;
    // 状态 0-正常 1-未开始 2-已抢光
    private int status;
    // 链接
    private String url;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
