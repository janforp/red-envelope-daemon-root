package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/15.
 */
public class ReStartAdInfo implements Serializable {

    // 图片
    private String adImg;
    // 链接
    private String adUrl;
    // 能否跳过; 0:能,1:不能
    private Integer adSkip;
    // 持续时间(秒); 默认5秒
    private Integer adDuration;

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public Integer getAdSkip() {
        return adSkip;
    }

    public void setAdSkip(Integer adSkip) {
        this.adSkip = adSkip;
    }

    public Integer getAdDuration() {
        return adDuration;
    }

    public void setAdDuration(Integer adDuration) {
        this.adDuration = adDuration;
    }
}
