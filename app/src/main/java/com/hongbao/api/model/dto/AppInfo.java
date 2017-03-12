package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/14.
 */
public class AppInfo implements Serializable {

    // app包名
    private String appPackage;
    // app名称
    private String appName;

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
