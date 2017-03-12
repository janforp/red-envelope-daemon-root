package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/19.
 */
public class ReScoreExchangeInfo implements Serializable {


    // 图标
    private String exchangeImg;
    // 名称
    private String exchangeName;
    // 说明
    private String exchangeDesc;
    // 跳转链接
    private String exchangeUrl;


    public String getExchangeImg() {
        return exchangeImg;
    }

    public void setExchangeImg(String exchangeImg) {
        this.exchangeImg = exchangeImg;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeDesc() {
        return exchangeDesc;
    }

    public void setExchangeDesc(String exchangeDesc) {
        this.exchangeDesc = exchangeDesc;
    }

    public String getExchangeUrl() {
        return exchangeUrl;
    }

    public void setExchangeUrl(String exchangeUrl) {
        this.exchangeUrl = exchangeUrl;
    }
}
