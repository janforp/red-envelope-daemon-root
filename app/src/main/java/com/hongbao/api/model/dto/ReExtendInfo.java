package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/29.
 */
public class ReExtendInfo implements Serializable {

    private Integer id;
    private Integer customerId;
    private Integer redNumDayLeft;
    private String customerName;
    private String customerImg;
    private String awardDesc;
    private String extendDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRedNumDayLeft() {
        return redNumDayLeft;
    }

    public void setRedNumDayLeft(Integer redNumDayLeft) {
        this.redNumDayLeft = redNumDayLeft;
    }

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

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }

    public String getExtendDesc() {
        return extendDesc;
    }

    public void setExtendDesc(String extendDesc) {
        this.extendDesc = extendDesc;
    }
}
