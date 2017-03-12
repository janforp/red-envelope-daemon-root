package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/8/18.
 */
public class ReFixedRedInfo implements Serializable {

    // ID
    private Integer fixedId;
    // 名称
    private String fixedTitle;
    // 剩余红包数量
    private Integer fixedRemainder;
    // 链接
    private String fixedUrl;
    // 时间
    private String fixedTime;
    // 倒计时(毫秒 正数-未开始,负数-已开始)
    private long countdown;

    public Integer getFixedId() {
        return fixedId;
    }

    public void setFixedId(Integer fixedId) {
        this.fixedId = fixedId;
    }

    public String getFixedTitle() {
        return fixedTitle;
    }

    public void setFixedTitle(String fixedTitle) {
        this.fixedTitle = fixedTitle;
    }

    public Integer getFixedRemainder() {
        return fixedRemainder;
    }

    public void setFixedRemainder(Integer fixedRemainder) {
        this.fixedRemainder = fixedRemainder;
    }

    public String getFixedUrl() {
        return fixedUrl;
    }

    public void setFixedUrl(String fixedUrl) {
        this.fixedUrl = fixedUrl;
    }

    public String getFixedTime() {
        return fixedTime;
    }

    public void setFixedTime(String fixedTime) {
        this.fixedTime = fixedTime;
    }

    public long getCountdown() {
        return countdown;
    }

    public void setCountdown(long countdown) {
        this.countdown = countdown;
    }

}
