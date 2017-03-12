package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/11/14.
 */
public class IndexMissionInfo implements Serializable {

    // 图标
    private String icon;
    // 标题
    private String title;
    // 描述
    private String desc;
    // 角标
    private String cornerMark;
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

    public String getCornerMark() {
        return cornerMark;
    }

    public void setCornerMark(String cornerMark) {
        this.cornerMark = cornerMark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IndexMissionInfo(String icon, String title, String desc, String cornerMark, String url) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.cornerMark = cornerMark;
        this.url = url;
    }

}
