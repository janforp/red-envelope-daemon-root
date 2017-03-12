package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-25
 */
public class ReIntegralWall implements java.io.Serializable {

    // Fields

    // 积分墙ID
    private Integer wallId;
    // 标题
    private String wallTitle;
    // 描述
    private String wallDesc;
    // 图片
    private String wallImg;
    // 状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer wallStatus;
    // 链接
    private String wallUrl;
    // 排序，值越小，越靠前
    private Integer wallOrder;
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
    public ReIntegralWall() {
    }

    /**
     * full constructor
     */
    public ReIntegralWall(String wallTitle, String wallDesc, String wallImg, Integer wallStatus, String wallUrl, Integer wallOrder, String limitVersion, String maxVersion, String limitChannelName, String limitPackageName) {
        this.wallTitle = wallTitle;
        this.wallDesc = wallDesc;
        this.wallImg = wallImg;
        this.wallStatus = wallStatus;
        this.wallUrl = wallUrl;
        this.wallOrder = wallOrder;
        this.limitVersion = limitVersion;
        this.maxVersion = maxVersion;
        this.limitChannelName = limitChannelName;
        this.limitPackageName = limitPackageName;
    }

    // Property accessors

    /**
     * 积分墙ID
     */
    public Integer getWallId() {
        return this.wallId;
    }

    /**
     * 积分墙ID
     */
    public void setWallId(Integer wallId) {
        this.wallId = wallId;
    }

    /**
     * 标题
     */
    public String getWallTitle() {
        return this.wallTitle;
    }

    /**
     * 标题
     */
    public void setWallTitle(String wallTitle) {
        this.wallTitle = wallTitle;
    }

    /**
     * 描述
     */
    public String getWallDesc() {
        return this.wallDesc;
    }

    /**
     * 描述
     */
    public void setWallDesc(String wallDesc) {
        this.wallDesc = wallDesc;
    }

    /**
     * 图片
     */
    public String getWallImg() {
        return this.wallImg;
    }

    /**
     * 图片
     */
    public void setWallImg(String wallImg) {
        this.wallImg = wallImg;
    }

    /**
     * 状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getWallStatus() {
        return this.wallStatus;
    }

    /**
     * 状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setWallStatus(Integer wallStatus) {
        this.wallStatus = wallStatus;
    }

    /**
     * 链接
     */
    public String getWallUrl() {
        return this.wallUrl;
    }

    /**
     * 链接
     */
    public void setWallUrl(String wallUrl) {
        this.wallUrl = wallUrl;
    }

    /**
     * 排序，值越小，越靠前
     */
    public Integer getWallOrder() {
        return this.wallOrder;
    }

    /**
     * 排序，值越小，越靠前
     */
    public void setWallOrder(Integer wallOrder) {
        this.wallOrder = wallOrder;
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