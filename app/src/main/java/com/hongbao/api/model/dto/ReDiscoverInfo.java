package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/15.
 */
public class ReDiscoverInfo implements Serializable {

    // 标题
    private String discoverTitle;
    // 描述
    private String discoverDescription;
    // 图片
    private String discoverImg;
    // 链接
    private String discoverUrl;

    public String getDiscoverTitle() {
        return discoverTitle;
    }

    public void setDiscoverTitle(String discoverTitle) {
        this.discoverTitle = discoverTitle;
    }

    public String getDiscoverDescription() {
        return discoverDescription;
    }

    public void setDiscoverDescription(String discoverDescription) {
        this.discoverDescription = discoverDescription;
    }

    public String getDiscoverImg() {
        return discoverImg;
    }

    public void setDiscoverImg(String discoverImg) {
        this.discoverImg = discoverImg;
    }

    public String getDiscoverUrl() {
        return discoverUrl;
    }

    public void setDiscoverUrl(String discoverUrl) {
        this.discoverUrl = discoverUrl;
    }
}
