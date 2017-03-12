package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-25
 */
public class ReNavigation implements java.io.Serializable {

    // Fields

    // navigationID
    private Integer navigationId;
    // navigation标题
    private String navigationTitle;
    // navigation图片
    private String navigationImg;
    // navigation状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer navigationStatus;
    // navigation链接
    private String navigationUrl;
    // navigation排序，值越小，越靠前
    private Integer navigationOrder;
    // 最低显示版本
    private String limitVersion;
    // 最高版本
    private String maxVersion;
    // 最低显示版本的渠道名
    private String limitChannelName;
    // 最低显示版本的包名
    private String limitPackageName;
    // 审核状态是否显示（默认为0） 0-不显示；1-显示
    private Integer isShow;

    // Constructors

    /**
     * default constructor
     */
    public ReNavigation() {
    }

    /**
     * full constructor
     */
    public ReNavigation(String navigationTitle, String navigationImg, Integer navigationStatus, String navigationUrl, Integer navigationOrder, String limitVersion, String maxVersion, String limitChannelName, String limitPackageName, Integer isShow) {
        this.navigationTitle = navigationTitle;
        this.navigationImg = navigationImg;
        this.navigationStatus = navigationStatus;
        this.navigationUrl = navigationUrl;
        this.navigationOrder = navigationOrder;
        this.limitVersion = limitVersion;
        this.maxVersion = maxVersion;
        this.limitChannelName = limitChannelName;
        this.limitPackageName = limitPackageName;
        this.isShow = isShow;
    }

    // Property accessors

    /**
     * navigationID
     */
    public Integer getNavigationId() {
        return this.navigationId;
    }

    /**
     * navigationID
     */
    public void setNavigationId(Integer navigationId) {
        this.navigationId = navigationId;
    }

    /**
     * navigation标题
     */
    public String getNavigationTitle() {
        return this.navigationTitle;
    }

    /**
     * navigation标题
     */
    public void setNavigationTitle(String navigationTitle) {
        this.navigationTitle = navigationTitle;
    }

    /**
     * navigation图片
     */
    public String getNavigationImg() {
        return this.navigationImg;
    }

    /**
     * navigation图片
     */
    public void setNavigationImg(String navigationImg) {
        this.navigationImg = navigationImg;
    }

    /**
     * navigation状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getNavigationStatus() {
        return this.navigationStatus;
    }

    /**
     * navigation状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setNavigationStatus(Integer navigationStatus) {
        this.navigationStatus = navigationStatus;
    }

    /**
     * navigation链接
     */
    public String getNavigationUrl() {
        return this.navigationUrl;
    }

    /**
     * navigation链接
     */
    public void setNavigationUrl(String navigationUrl) {
        this.navigationUrl = navigationUrl;
    }

    /**
     * navigation排序，值越小，越靠前
     */
    public Integer getNavigationOrder() {
        return this.navigationOrder;
    }

    /**
     * navigation排序，值越小，越靠前
     */
    public void setNavigationOrder(Integer navigationOrder) {
        this.navigationOrder = navigationOrder;
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

    /**
     * 最高版本
     */
    public String getMaxVersion() {
        return this.maxVersion;
    }

    /**
     * 最高版本
     */
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