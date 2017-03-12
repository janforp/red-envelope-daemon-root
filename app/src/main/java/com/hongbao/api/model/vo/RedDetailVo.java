package com.hongbao.api.model.vo;

import com.hongbao.api.model.dto.RedDetailInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/12/24.
 */
public class RedDetailVo implements Serializable{

    // 用户头像
    private String portrait;
    // 用户昵称
    private String nickname;
    // 红包内容
    private String redContent;
    // 是否抢到 (0-未抢到, 1-抢到)
    private int isReceive;
    // 抢到金额
    private String redMoney;
    // 红包领取情况
    private String redStatus;
    // 领取详情列表
    private List<RedDetailInfo> detailList;

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

    public String getRedContent() {
        return redContent;
    }

    public void setRedContent(String redContent) {
        this.redContent = redContent;
    }

    public int getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(int isReceive) {
        this.isReceive = isReceive;
    }

    public String getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(String redMoney) {
        this.redMoney = redMoney;
    }

    public String getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(String redStatus) {
        this.redStatus = redStatus;
    }

    public List<RedDetailInfo> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<RedDetailInfo> detailList) {
        this.detailList = detailList;
    }

}
