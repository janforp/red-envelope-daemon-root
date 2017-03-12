package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 2016/10/8.
 */
public class IndexRollInfo implements Serializable {

    // 昵称
    private String nickname;
    // 类型;0:支出,1:收入
    private int detailType;
    // 任务分类
    private int missionType;
    // 任务子分类
    private int missionSubtype;
    // 金额
    private BigDecimal money;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getDetailType() {
        return detailType;
    }

    public void setDetailType(int detailType) {
        this.detailType = detailType;
    }

    public int getMissionType() {
        return missionType;
    }

    public void setMissionType(int missionType) {
        this.missionType = missionType;
    }

    public int getMissionSubtype() {
        return missionSubtype;
    }

    public void setMissionSubtype(int missionSubtype) {
        this.missionSubtype = missionSubtype;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
