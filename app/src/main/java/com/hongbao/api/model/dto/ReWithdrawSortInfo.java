package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/19.
 */
public class ReWithdrawSortInfo implements Serializable {

    // 提现分类ID
    private Integer withdrawId;
    // 名字
    private String withdrawName;
    // 图标
    private String withdrawImg;
    // 副标题
    private String withdrawExplain;
    // 跳转链接
    private String withdrawUrl;

    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getWithdrawName() {
        return withdrawName;
    }

    public void setWithdrawName(String withdrawName) {
        this.withdrawName = withdrawName;
    }

    public String getWithdrawImg() {
        return withdrawImg;
    }

    public void setWithdrawImg(String withdrawImg) {
        this.withdrawImg = withdrawImg;
    }

    public String getWithdrawExplain() {
        return withdrawExplain;
    }

    public void setWithdrawExplain(String withdrawExplain) {
        this.withdrawExplain = withdrawExplain;
    }

    public String getWithdrawUrl() {
        return withdrawUrl;
    }

    public void setWithdrawUrl(String withdrawUrl) {
        this.withdrawUrl = withdrawUrl;
    }
}
