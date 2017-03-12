package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-23
 */
public class ReWithdrawBind implements java.io.Serializable {

    // Fields

    // 用户ID
    private Integer userId;
    // 微信openid
    private String openId;
    // 微信昵称
    private String nickname;

    // Constructors

    /**
     * default constructor
     */
    public ReWithdrawBind() {
    }

    /**
     * full constructor
     */
    public ReWithdrawBind(Integer userId, String openId, String nickname) {
        this.userId = userId;
        this.openId = openId;
        this.nickname = nickname;
    }

    // Property accessors

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 微信openid
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 微信openid
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 微信昵称
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * 微信昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}