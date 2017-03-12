package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/23.
 */
public class ReCodeRedInfo implements Serializable {

    // 客户名称
    private String customerName;
    // 客户头像
    private String customerImg;
    // 客户描述
    private String customerDesc;
    // 奖励说明
    private String awardDesc;
    // 跳转链接
    private String redUrl;
    // 状态 0-已结束,1-进行中
    private Integer redStatus;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }

    public String getRedUrl() {
        return redUrl;
    }

    public void setRedUrl(String redUrl) {
        this.redUrl = redUrl;
    }

    public Integer getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(Integer redStatus) {
        this.redStatus = redStatus;
    }
}
