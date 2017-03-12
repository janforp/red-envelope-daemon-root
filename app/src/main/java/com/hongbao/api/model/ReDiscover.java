package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-25
 */
public class ReDiscover implements java.io.Serializable {

    // Fields

    // id
    private Integer discoverId;
    // 标题
    private String discoverTitle;
    // 描述
    private String discoverDescription;
    // 图片
    private String discoverImg;
    // 链接
    private String discoverUrl;
    // discover状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer discoverStatus;
    // 排序，值越小，越靠前
    private Integer discoverOrder;
    // 最低显示版本
    private String limitVersion;
    private String maxVersion;
    // 最低显示版本的渠道名
    private String limitChannelName;
    // 最低显示版本的包名
    private String limitPackageName;

    // Constructors

    /**
     * default constructor
     */
    public ReDiscover() {
    }

    /**
     * full constructor
     */
    public ReDiscover(String discoverTitle, String discoverDescription, String discoverImg, String discoverUrl, Integer discoverStatus, Integer discoverOrder, String limitVersion, String maxVersion, String limitChannelName, String limitPackageName) {
        this.discoverTitle = discoverTitle;
        this.discoverDescription = discoverDescription;
        this.discoverImg = discoverImg;
        this.discoverUrl = discoverUrl;
        this.discoverStatus = discoverStatus;
        this.discoverOrder = discoverOrder;
        this.limitVersion = limitVersion;
        this.maxVersion = maxVersion;
        this.limitChannelName = limitChannelName;
        this.limitPackageName = limitPackageName;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getDiscoverId() {
        return this.discoverId;
    }

    /**
     * id
     */
    public void setDiscoverId(Integer discoverId) {
        this.discoverId = discoverId;
    }

    /**
     * 标题
     */
    public String getDiscoverTitle() {
        return this.discoverTitle;
    }

    /**
     * 标题
     */
    public void setDiscoverTitle(String discoverTitle) {
        this.discoverTitle = discoverTitle;
    }

    /**
     * 描述
     */
    public String getDiscoverDescription() {
        return this.discoverDescription;
    }

    /**
     * 描述
     */
    public void setDiscoverDescription(String discoverDescription) {
        this.discoverDescription = discoverDescription;
    }

    /**
     * 图片
     */
    public String getDiscoverImg() {
        return this.discoverImg;
    }

    /**
     * 图片
     */
    public void setDiscoverImg(String discoverImg) {
        this.discoverImg = discoverImg;
    }

    /**
     * 链接
     */
    public String getDiscoverUrl() {
        return this.discoverUrl;
    }

    /**
     * 链接
     */
    public void setDiscoverUrl(String discoverUrl) {
        this.discoverUrl = discoverUrl;
    }

    /**
     * discover状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getDiscoverStatus() {
        return this.discoverStatus;
    }

    /**
     * discover状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setDiscoverStatus(Integer discoverStatus) {
        this.discoverStatus = discoverStatus;
    }

    /**
     * 排序，值越小，越靠前
     */
    public Integer getDiscoverOrder() {
        return this.discoverOrder;
    }

    /**
     * 排序，值越小，越靠前
     */
    public void setDiscoverOrder(Integer discoverOrder) {
        this.discoverOrder = discoverOrder;
    }

    /**
     * 最低显示版本
     */
    public String getLimitVersion() {
        return this.limitVersion;
    }

    /**
     * 最低显示版本
     */
    public void setLimitVersion(String limitVersion) {
        this.limitVersion = limitVersion;
    }

    public String getMaxVersion() {
        return this.maxVersion;
    }

    public void setMaxVersion(String maxVersion) {
        this.maxVersion = maxVersion;
    }

    /**
     * 最低显示版本的渠道名
     */
    public String getLimitChannelName() {
        return this.limitChannelName;
    }

    /**
     * 最低显示版本的渠道名
     */
    public void setLimitChannelName(String limitChannelName) {
        this.limitChannelName = limitChannelName;
    }

    /**
     * 最低显示版本的包名
     */
    public String getLimitPackageName() {
        return this.limitPackageName;
    }

    /**
     * 最低显示版本的包名
     */
    public void setLimitPackageName(String limitPackageName) {
        this.limitPackageName = limitPackageName;
    }

}