package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 16/8/19.
 */
public class ReWithdrawDetailInfo implements Serializable {

    // 提现金额
    private List<ReWithdrawMoney> reWithdrawMoneyList;
    // 提现说明
    private String withdrawDesc;
    // 提现次数
    private Integer withdrawTimes;
    // 提现类型
    private String withdrawType;
    // 微信openid
    private String openid;
    // 微信昵称
    private String nickname;
    // 微信绑定链接
    private String bindUrl;

    public List<ReWithdrawMoney> getReWithdrawMoneyList() {
        return reWithdrawMoneyList;
    }

    public void setReWithdrawMoneyList(List<ReWithdrawMoney> reWithdrawMoneyList) {
        this.reWithdrawMoneyList = reWithdrawMoneyList;
    }

    public String getWithdrawDesc() {
        return withdrawDesc;
    }

    public void setWithdrawDesc(String withdrawDesc) {
        this.withdrawDesc = withdrawDesc;
    }

    public Integer getWithdrawTimes() {
        return withdrawTimes;
    }

    public void setWithdrawTimes(Integer withdrawTimes) {
        this.withdrawTimes = withdrawTimes;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getBindUrl() {
        return bindUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBindUrl(String bindUrl) {
        this.bindUrl = bindUrl;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }
}
