package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/11/15.
 */
public class TrialTaskInfo implements Serializable {

    // id
    private Long keywordId;
    // app图片
    private String appIcon;
    // 包名
    private String appPackage;
    // 关键词
    private String keyword;
    // 市场名
    private String appMarket;
    // 市场url
    private String marketUrl;
    // 市场包名
    private String marketPackage;
    // 标签
    private List<String> appLabel;
    // 状态 0-进行中, 1-已完成, 2-显示金额
    private Integer status;
    // 描述
    private String desc;
    // 详情页链接
    private String detailUrl;

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAppMarket() {
        return appMarket;
    }

    public void setAppMarket(String appMarket) {
        this.appMarket = appMarket;
    }

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public String getMarketPackage() {
        return marketPackage;
    }

    public void setMarketPackage(String marketPackage) {
        this.marketPackage = marketPackage;
    }

    public List<String> getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(List<String> appLabel) {
        this.appLabel = appLabel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
