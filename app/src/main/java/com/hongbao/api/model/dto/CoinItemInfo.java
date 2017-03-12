package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/9.
 */
public class CoinItemInfo implements Serializable {

    // 商品标题
    private String itemTitle;
    // 商品图片
    private String itemImg;
    // 商品链接
    private String itemUrl;
    // 金币数量
    private Integer itemCoin;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public Integer getItemCoin() {
        return itemCoin;
    }

    public void setItemCoin(Integer itemCoin) {
        this.itemCoin = itemCoin;
    }
}
