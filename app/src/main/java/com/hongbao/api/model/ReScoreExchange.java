package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
public class ReScoreExchange implements java.io.Serializable {

    // Fields

    // 金币兑换分类id
    private Integer exchangeId;
    // 图标
    private String exchangeImg;
    // 名称
    private String exchangeName;
    // 说明
    private String exchangeDesc;
    // 跳转链接
    private String exchangeUrl;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer exchangeStatus;
    // 排序,值较小者排在较前
    private Integer exchangeOrder;

    // Constructors

    /**
     * default constructor
     */
    public ReScoreExchange() {
    }

    /**
     * full constructor
     */
    public ReScoreExchange(String exchangeImg, String exchangeName, String exchangeDesc, String exchangeUrl, Integer exchangeStatus, Integer exchangeOrder) {
        this.exchangeImg = exchangeImg;
        this.exchangeName = exchangeName;
        this.exchangeDesc = exchangeDesc;
        this.exchangeUrl = exchangeUrl;
        this.exchangeStatus = exchangeStatus;
        this.exchangeOrder = exchangeOrder;
    }

    // Property accessors

    /**
     * 金币兑换分类id
     */
    public Integer getExchangeId() {
        return this.exchangeId;
    }

    /**
     * 金币兑换分类id
     */
    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    /**
     * 图标
     */
    public String getExchangeImg() {
        return this.exchangeImg;
    }

    /**
     * 图标
     */
    public void setExchangeImg(String exchangeImg) {
        this.exchangeImg = exchangeImg;
    }

    /**
     * 名称
     */
    public String getExchangeName() {
        return this.exchangeName;
    }

    /**
     * 名称
     */
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    /**
     * 说明
     */
    public String getExchangeDesc() {
        return this.exchangeDesc;
    }

    /**
     * 说明
     */
    public void setExchangeDesc(String exchangeDesc) {
        this.exchangeDesc = exchangeDesc;
    }

    /**
     * 跳转链接
     */
    public String getExchangeUrl() {
        return this.exchangeUrl;
    }

    /**
     * 跳转链接
     */
    public void setExchangeUrl(String exchangeUrl) {
        this.exchangeUrl = exchangeUrl;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getExchangeStatus() {
        return this.exchangeStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    /**
     * 排序,值较小者排在较前
     */
    public Integer getExchangeOrder() {
        return this.exchangeOrder;
    }

    /**
     * 排序,值较小者排在较前
     */
    public void setExchangeOrder(Integer exchangeOrder) {
        this.exchangeOrder = exchangeOrder;
    }

}