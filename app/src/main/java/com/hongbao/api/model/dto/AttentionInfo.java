package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Summer on 2016/11/16.
 */
public class AttentionInfo implements Serializable {

    // 图片
    private String img;
    // 标题
    private String title;
    // 标签
    private List<String> label;
    // 金额
    private BigDecimal money;
    // 链接
    private String url;
    // 状态 0-未完成 1-已完成
    private int status;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
