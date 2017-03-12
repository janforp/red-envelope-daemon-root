package com.hongbao.api.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 2016/12/10.
 * 福利导航
 */
public class NavigationVo implements Serializable{

    // 导航名称
    private String navigationName;
    // 导航图片
    private String navigationImg;
    // 导航链接
    private String navigationUrl;

    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
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

    /**
     * default constructor
     */
    public NavigationVo() {
    }

    /**
     * full constructor
     */
    public NavigationVo(String navigationName, String navigationImg, String navigationUrl) {
        this.navigationName = navigationName;
        this.navigationImg = navigationImg;
        this.navigationUrl = navigationUrl;
    }

}
