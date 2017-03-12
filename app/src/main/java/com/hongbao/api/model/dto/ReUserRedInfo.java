package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 2016/12/23.
 */
public class ReUserRedInfo implements Serializable {

    // id
    private Long redId;
    // 用户id
    private Integer userId;
    // 用户头像
    private String portrait;
    // 用户昵称
    private String nickname;
    // 发布时间
    private Long createTimeMs;
    // 红包内容
    private String redContent;
    // 红包总个数
    private Integer redTotalNum;
    // 红包剩余个数
    private Integer redLeftNum;
    // 抢到红包金额(元)
    private BigDecimal redMoney;
    // 退还时间
    private Long refundTime;
    // 总金额
    private BigDecimal totalMoney;

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getRedId() {
        return redId;
    }

    public void setRedId(Long redId) {
        this.redId = redId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getCreateTimeMs() {
        return createTimeMs;
    }

    public void setCreateTimeMs(Long createTimeMs) {
        this.createTimeMs = createTimeMs;
    }

    public String getRedContent() {
        return redContent;
    }

    public void setRedContent(String redContent) {
        this.redContent = redContent;
    }

    public Integer getRedTotalNum() {
        return redTotalNum;
    }

    public void setRedTotalNum(Integer redTotalNum) {
        this.redTotalNum = redTotalNum;
    }

    public Integer getRedLeftNum() {
        return redLeftNum;
    }

    public void setRedLeftNum(Integer redLeftNum) {
        this.redLeftNum = redLeftNum;
    }

    public BigDecimal getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(BigDecimal redMoney) {
        this.redMoney = redMoney;
    }

    public Long getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Long refundTime) {
        this.refundTime = refundTime;
    }
}
