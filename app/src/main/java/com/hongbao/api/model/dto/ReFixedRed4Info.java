package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/17.
 * 定时红包列表数据
 */
public class ReFixedRed4Info implements Serializable{

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
    // 是否抢到该定时红包,若没有登录,则默认为没有,1:抢了,0:没有抢
    private Integer isGet;

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

    public Integer getIsGet() {
        return isGet;
    }

    public void setIsGet(Integer isGet) {
        this.isGet = isGet;
    }
}
