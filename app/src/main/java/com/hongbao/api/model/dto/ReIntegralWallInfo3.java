package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/10/27.
 */
public class ReIntegralWallInfo3 implements Serializable {

    // 标题
    private String wallTitle;
    // 标签
    private List<String> wallLabel;
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

    public List<String> getWallLabel() {
        return wallLabel;
    }

    public void setWallLabel(List<String> wallLabel) {
        this.wallLabel = wallLabel;
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
