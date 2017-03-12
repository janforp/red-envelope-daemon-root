package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-02
 */
public class ReWithdrawBindZfb implements java.io.Serializable {

    // Fields

    // 用户ID
    private Integer userId;
    // 支付宝账户
    private String alipayAccount;
    // 姓名
    private String fullName;

    // Constructors

    /**
     * default constructor
     */
    public ReWithdrawBindZfb() {
    }

    /**
     * full constructor
     */
    public ReWithdrawBindZfb(Integer userId, String alipayAccount, String fullName) {
        this.userId = userId;
        this.alipayAccount = alipayAccount;
        this.fullName = fullName;
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
     * 支付宝账户
     */
    public String getAlipayAccount() {
        return this.alipayAccount;
    }

    /**
     * 支付宝账户
     */
    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    /**
     * 姓名
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * 姓名
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}