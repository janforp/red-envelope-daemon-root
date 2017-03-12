package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/12.
 */
public class ReIntegralWallInfo implements Serializable {

    // 标题
    private String wallTitle;
    // 描述
    private String wallDesc;
    // 图片
    private String wallImg;
    // 链接
    private String wallUrl;

    public String getWallTitle() {
        return wallTitle;
    }

    public void setWallTitle(String wallTitle) {
        this.wallTitle = wallTitle;
    }

    public String getWallDesc() {
        return wallDesc;
    }

    public void setWallDesc(String wallDesc) {
        this.wallDesc = wallDesc;
    }

    public String getWallImg() {
        return wallImg;
    }

    public void setWallImg(String wallImg) {
        this.wallImg = wallImg;
    }

    public String getWallUrl() {
        return wallUrl;
    }

    public void setWallUrl(String wallUrl) {
        this.wallUrl = wallUrl;
    }
}
