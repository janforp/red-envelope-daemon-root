package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
public class ReRedBanner implements java.io.Serializable {

    // Fields

    // BannerID
    private Long bannerId;
    // Banner标题
    private String bannerTitle;
    // Banner图片
    private String bannerImg;
    // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer bannerStatus;
    // Banner链接
    private String bannerUrl;
    // 权重(0-100),大的排前面
    private Integer bannerWeight;
    // 最低显示版本号
    private Integer minVersionCode;
    // 最高显示版本号
    private Integer maxVersionCode;
    // 显示的渠道名
    private String showChannelName;
    // 显示的包名
    private String showPackageName;
    // 审核状态是否显示（默认为0） 0-不显示；1-显示
    private Integer isShow;

    // Constructors

    /**
     * default constructor
     */
    public ReRedBanner() {
    }

    /**
     * full constructor
     */
    public ReRedBanner(String bannerTitle, String bannerImg, Integer bannerStatus, String bannerUrl, Integer bannerWeight, Integer minVersionCode, Integer maxVersionCode, String showChannelName, String showPackageName, Integer isShow) {
        this.bannerTitle = bannerTitle;
        this.bannerImg = bannerImg;
        this.bannerStatus = bannerStatus;
        this.bannerUrl = bannerUrl;
        this.bannerWeight = bannerWeight;
        this.minVersionCode = minVersionCode;
        this.maxVersionCode = maxVersionCode;
        this.showChannelName = showChannelName;
        this.showPackageName = showPackageName;
        this.isShow = isShow;
    }

    // Property accessors

    /**
     * BannerID
     */
    public Long getBannerId() {
        return this.bannerId;
    }

    /**
     * BannerID
     */
    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * Banner标题
     */
    public String getBannerTitle() {
        return this.bannerTitle;
    }

    /**
     * Banner标题
     */
    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    /**
     * Banner图片
     */
    public String getBannerImg() {
        return this.bannerImg;
    }

    /**
     * Banner图片
     */
    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    /**
     * Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getBannerStatus() {
        return this.bannerStatus;
    }

    /**
     * Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setBannerStatus(Integer bannerStatus) {
        this.bannerStatus = bannerStatus;
    }

    /**
     * Banner链接
     */
    public String getBannerUrl() {
        return this.bannerUrl;
    }

    /**
     * Banner链接
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * 权重(0-100),大的排前面
     */
    public Integer getBannerWeight() {
        return this.bannerWeight;
    }

    /**
     * 权重(0-100),大的排前面
     */
    public void setBannerWeight(Integer bannerWeight) {
        this.bannerWeight = bannerWeight;
    }

    /**
     * 最低显示版本号
     */
    public Integer getMinVersionCode() {
        return this.minVersionCode;
    }

    /**
     * 最低显示版本号
     */
    public void setMinVersionCode(Integer minVersionCode) {
        this.minVersionCode = minVersionCode;
    }

    /**
     * 最高显示版本号
     */
    public Integer getMaxVersionCode() {
        return this.maxVersionCode;
    }

    /**
     * 最高显示版本号
     */
    public void setMaxVersionCode(Integer maxVersionCode) {
        this.maxVersionCode = maxVersionCode;
    }

    /**
     * 显示的渠道名
     */
    public String getShowChannelName() {
        return this.showChannelName;
    }

    /**
     * 显示的渠道名
     */
    public void setShowChannelName(String showChannelName) {
        this.showChannelName = showChannelName;
    }

    /**
     * 显示的包名
     */
    public String getShowPackageName() {
        return this.showPackageName;
    }

    /**
     * 显示的包名
     */
    public void setShowPackageName(String showPackageName) {
        this.showPackageName = showPackageName;
    }

    /**
     * 审核状态是否显示（默认为0） 0-不显示；1-显示
     */
    public Integer getIsShow() {
        return this.isShow;
    }

    /**
     * 审核状态是否显示（默认为0） 0-不显示；1-显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

}