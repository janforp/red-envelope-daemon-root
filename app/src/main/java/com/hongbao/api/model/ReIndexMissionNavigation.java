package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-10
 */
public class ReIndexMissionNavigation implements java.io.Serializable {

    // Fields

    // 导航id，自增长
    private Long navigationId;
    // 导航名称
    private String navigationName;
    // 导航图片
    private String navigationImg;
    // 导航链接
    private String navigationUrl;
    // 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
    private Integer navigationStatus;
    // 权重0-100, 越大越靠前
    private Integer navigationWeight;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReIndexMissionNavigation() {
    }

    /**
     * full constructor
     */
    public ReIndexMissionNavigation(String navigationName, String navigationImg, String navigationUrl, Integer navigationStatus, Integer navigationWeight, String createTime, String updateTime) {
        this.navigationName = navigationName;
        this.navigationImg = navigationImg;
        this.navigationUrl = navigationUrl;
        this.navigationStatus = navigationStatus;
        this.navigationWeight = navigationWeight;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * 导航id，自增长
     */
    public Long getNavigationId() {
        return this.navigationId;
    }

    /**
     * 导航id，自增长
     */
    public void setNavigationId(Long navigationId) {
        this.navigationId = navigationId;
    }

    /**
     * 导航名称
     */
    public String getNavigationName() {
        return this.navigationName;
    }

    /**
     * 导航名称
     */
    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    /**
     * 导航图片
     */
    public String getNavigationImg() {
        return this.navigationImg;
    }

    /**
     * 导航图片
     */
    public void setNavigationImg(String navigationImg) {
        this.navigationImg = navigationImg;
    }

    /**
     * 导航链接
     */
    public String getNavigationUrl() {
        return this.navigationUrl;
    }

    /**
     * 导航链接
     */
    public void setNavigationUrl(String navigationUrl) {
        this.navigationUrl = navigationUrl;
    }

    /**
     * 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
     */
    public Integer getNavigationStatus() {
        return this.navigationStatus;
    }

    /**
     * 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
     */
    public void setNavigationStatus(Integer navigationStatus) {
        this.navigationStatus = navigationStatus;
    }

    /**
     * 权重0-100, 越大越靠前
     */
    public Integer getNavigationWeight() {
        return this.navigationWeight;
    }

    /**
     * 权重0-100, 越大越靠前
     */
    public void setNavigationWeight(Integer navigationWeight) {
        this.navigationWeight = navigationWeight;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}