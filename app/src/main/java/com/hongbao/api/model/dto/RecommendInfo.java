package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/10/26.
 */
public class RecommendInfo implements Serializable {

    // 标题
    private String recommendTitle;
    // 图片
    private String recommendImg;
    // 标签
    private List<String> recommendLabel;
    // 链接
    private String recommendUrl;

    public String getRecommendTitle() {
        return recommendTitle;
    }

    public void setRecommendTitle(String recommendTitle) {
        this.recommendTitle = recommendTitle;
    }

    public String getRecommendImg() {
        return recommendImg;
    }

    public void setRecommendImg(String recommendImg) {
        this.recommendImg = recommendImg;
    }

    public List<String> getRecommendLabel() {
        return recommendLabel;
    }

    public void setRecommendLabel(List<String> recommendLabel) {
        this.recommendLabel = recommendLabel;
    }

    public String getRecommendUrl() {
        return recommendUrl;
    }

    public void setRecommendUrl(String recommendUrl) {
        this.recommendUrl = recommendUrl;
    }
}
