package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/15.
 */
public class ReNavigationInfo implements Serializable {

    // navigation标题
    private String navigationTitle;
    // navigation图片
    private String navigationImg;
    // navigation链接
    private String navigationUrl;

    public String getNavigationTitle() {
        return navigationTitle;
    }

    public void setNavigationTitle(String navigationTitle) {
        this.navigationTitle = navigationTitle;
    }

    public String getNavigationImg() {
        return navigationImg;
    }

    public void setNavigationImg(String navigationImg) {
        this.navigationImg = navigationImg;
    }

    public String getNavigationUrl() {
        return navigationUrl;
    }

    public void setNavigationUrl(String navigationUrl) {
        this.navigationUrl = navigationUrl;
    }
}
