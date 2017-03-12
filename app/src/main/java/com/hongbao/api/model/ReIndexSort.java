package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-11
 */
public class ReIndexSort implements java.io.Serializable {

    // Fields

    // id
    private Integer sortId;
    // 名称
    private String sortName;
    // 分类详情(表名,表名,表名)
    private String sortDesc;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer sortStatus;
    // 排序,值较小者排在较前
    private Integer sortOrder;
    // 最低显示版本
    private String limitVersion;
    // 最低显示版本的渠道名
    private String limitChannelName;
    // 最低显示版本的包名
    private String limitPackageName;
    // 当前审核版本
    private String verifyVersion;
    // 审核通过的渠道包名
    private String verifyChannelPackage;
    // 图标
    private String sortImg;

    // Constructors

    /**
     * default constructor
     */
    public ReIndexSort() {
    }

    /**
     * full constructor
     */
    public ReIndexSort(String sortName, String sortDesc, Integer sortStatus, Integer sortOrder, String limitVersion, String limitChannelName, String limitPackageName, String verifyVersion, String verifyChannelPackage, String sortImg) {
        this.sortName = sortName;
        this.sortDesc = sortDesc;
        this.sortStatus = sortStatus;
        this.sortOrder = sortOrder;
        this.limitVersion = limitVersion;
        this.limitChannelName = limitChannelName;
        this.limitPackageName = limitPackageName;
        this.verifyVersion = verifyVersion;
        this.verifyChannelPackage = verifyChannelPackage;
        this.sortImg = sortImg;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getSortId() {
        return this.sortId;
    }

    /**
     * id
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    /**
     * 名称
     */
    public String getSortName() {
        return this.sortName;
    }

    /**
     * 名称
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * 分类详情(表名,表名,表名)
     */
    public String getSortDesc() {
        return this.sortDesc;
    }

    /**
     * 分类详情(表名,表名,表名)
     */
    public void setSortDesc(String sortDesc) {
        this.sortDesc = sortDesc;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getSortStatus() {
        return this.sortStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setSortStatus(Integer sortStatus) {
        this.sortStatus = sortStatus;
    }

    /**
     * 排序,值较小者排在较前
     */
    public Integer getSortOrder() {
        return this.sortOrder;
    }

    /**
     * 排序,值较小者排在较前
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
     * 当前审核版本
     */
    public String getVerifyVersion() {
        return this.verifyVersion;
    }

    /**
     * 当前审核版本
     */
    public void setVerifyVersion(String verifyVersion) {
        this.verifyVersion = verifyVersion;
    }

    /**
     * 审核通过的渠道包名
     */
    public String getVerifyChannelPackage() {
        return this.verifyChannelPackage;
    }

    /**
     * 审核通过的渠道包名
     */
    public void setVerifyChannelPackage(String verifyChannelPackage) {
        this.verifyChannelPackage = verifyChannelPackage;
    }

    /**
     * 图标
     */
    public String getSortImg() {
        return this.sortImg;
    }

    /**
     * 图标
     */
    public void setSortImg(String sortImg) {
        this.sortImg = sortImg;
    }

}