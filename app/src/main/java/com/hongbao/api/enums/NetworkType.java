package com.hongbao.api.enums;

/**
 * 网络类型
 *
 * @author wuqiang
 */
public enum NetworkType {
    /**
     * 未知网络类型
     */
    unkown(),
    /**
     * 2G网络（gprs/edge）
     */
    net_2g(),
    /**
     * 3G网络
     */
    net_3g(),
    /**
     * 4G网络
     */
    net_4g(),
    /**
     * Wifi
     */
    net_wifi();

    private NetworkType() {
    }

}