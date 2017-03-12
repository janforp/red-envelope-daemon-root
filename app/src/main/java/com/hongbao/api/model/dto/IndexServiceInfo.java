package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/11/14.
 */
public class IndexServiceInfo implements Serializable {

    // 图标
    private String icon;
    // 标题
    private String title;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IndexServiceInfo(String icon, String title, String url) {
        this.icon = icon;
        this.title = title;
        this.url = url;
    }

}
