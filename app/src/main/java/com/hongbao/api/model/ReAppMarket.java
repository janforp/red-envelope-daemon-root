package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-22
 */
public class ReAppMarket implements java.io.Serializable {

    // Fields

    // id
    private Integer marketId;
    // 市场名称
    private String marketName;
    // 包名
    private String marketPackage;
    // 市场下载地址
    private String marketUrl;
    // 排序,值较小者排在较前
    private Integer marketOrder;
    // 应用市场图标
    private String marketIcon;
    // 市场名图
    private String marketTitleIcon;

    // Constructors

    /**
     * default constructor
     */
    public ReAppMarket() {
    }

    /**
     * full constructor
     */
    public ReAppMarket(String marketName, String marketPackage, String marketUrl, Integer marketOrder, String marketIcon, String marketTitleIcon) {
        this.marketName = marketName;
        this.marketPackage = marketPackage;
        this.marketUrl = marketUrl;
        this.marketOrder = marketOrder;
        this.marketIcon = marketIcon;
        this.marketTitleIcon = marketTitleIcon;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getMarketId() {
        return this.marketId;
    }

    /**
     * id
     */
    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    /**
     * 市场名称
     */
    public String getMarketName() {
        return this.marketName;
    }

    /**
     * 市场名称
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * 包名
     */
    public String getMarketPackage() {
        return this.marketPackage;
    }

    /**
     * 包名
     */
    public void setMarketPackage(String marketPackage) {
        this.marketPackage = marketPackage;
    }

    /**
     * 市场下载地址
     */
    public String getMarketUrl() {
        return this.marketUrl;
    }

    /**
     * 市场下载地址
     */
    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    /**
     * 排序,值较小者排在较前
     */
    public Integer getMarketOrder() {
        return this.marketOrder;
    }

    /**
     * 排序,值较小者排在较前
     */
    public void setMarketOrder(Integer marketOrder) {
        this.marketOrder = marketOrder;
    }

    /**
     * 应用市场图标
     */
    public String getMarketIcon() {
        return this.marketIcon;
    }

    /**
     * 应用市场图标
     */
    public void setMarketIcon(String marketIcon) {
        this.marketIcon = marketIcon;
    }

    /**
     * 市场名图
     */
    public String getMarketTitleIcon() {
        return this.marketTitleIcon;
    }

    /**
     * 市场名图
     */
    public void setMarketTitleIcon(String marketTitleIcon) {
        this.marketTitleIcon = marketTitleIcon;
    }

}