package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-15
 */
public class ReStartAd implements java.io.Serializable {

    // Fields

    // id
    private Integer adId;
    // 标题
    private String adTitle;
    // 图片
    private String adImg;
    // 链接
    private String adUrl;
    // 状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer adStatus;
    // 能否跳过; 0:能,1:不能
    private Integer adSkip;
    // 持续时间(秒); 默认5秒
    private Integer adDuration;

    // Constructors

    /**
     * default constructor
     */
    public ReStartAd() {
    }

    /**
     * full constructor
     */
    public ReStartAd(String adTitle, String adImg, String adUrl, Integer adStatus, Integer adSkip, Integer adDuration) {
        this.adTitle = adTitle;
        this.adImg = adImg;
        this.adUrl = adUrl;
        this.adStatus = adStatus;
        this.adSkip = adSkip;
        this.adDuration = adDuration;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getAdId() {
        return this.adId;
    }

    /**
     * id
     */
    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    /**
     * 标题
     */
    public String getAdTitle() {
        return this.adTitle;
    }

    /**
     * 标题
     */
    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    /**
     * 图片
     */
    public String getAdImg() {
        return this.adImg;
    }

    /**
     * 图片
     */
    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    /**
     * 链接
     */
    public String getAdUrl() {
        return this.adUrl;
    }

    /**
     * 链接
     */
    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    /**
     * 状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getAdStatus() {
        return this.adStatus;
    }

    /**
     * 状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setAdStatus(Integer adStatus) {
        this.adStatus = adStatus;
    }

    /**
     * 能否跳过; 0:能,1:不能
     */
    public Integer getAdSkip() {
        return this.adSkip;
    }

    /**
     * 能否跳过; 0:能,1:不能
     */
    public void setAdSkip(Integer adSkip) {
        this.adSkip = adSkip;
    }

    /**
     * 持续时间(秒); 默认5秒
     */
    public Integer getAdDuration() {
        return this.adDuration;
    }

    /**
     * 持续时间(秒); 默认5秒
     */
    public void setAdDuration(Integer adDuration) {
        this.adDuration = adDuration;
    }

}