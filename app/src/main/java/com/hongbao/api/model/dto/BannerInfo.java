package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/12.
 */
public class BannerInfo implements Serializable{

    // Banner图片
    private String bannerImg;
    // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer bannerStatus;
    // Banner链接
    private String bannerUrl;
    // 最低显示版本的渠道名
    private String limitChannelName;
    // 最低显示版本的包名
    private String limitPackageName;
    // 审核状态是否显示（默认为0） 0-不显示；1-显示
    private Integer isShow;
    // 最小显示版本号
    private Integer minVersionCode;
    // 最大显示版本号
    private Integer maxVersionCode;

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public Integer getBannerStatus() {
        return bannerStatus;
    }

    public void setBannerStatus(Integer bannerStatus) {
        this.bannerStatus = bannerStatus;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getLimitChannelName() {
        return limitChannelName;
    }

    public void setLimitChannelName(String limitChannelName) {
        this.limitChannelName = limitChannelName;
    }

    public String getLimitPackageName() {
        return limitPackageName;
    }

    public void setLimitPackageName(String limitPackageName) {
        this.limitPackageName = limitPackageName;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getMinVersionCode() {
        return minVersionCode;
    }

    public void setMinVersionCode(Integer minVersionCode) {
        this.minVersionCode = minVersionCode;
    }

    public Integer getMaxVersionCode() {
        return maxVersionCode;
    }

    public void setMaxVersionCode(Integer maxVersionCode) {
        this.maxVersionCode = maxVersionCode;
    }
}
