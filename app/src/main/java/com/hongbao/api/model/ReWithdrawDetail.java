package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-24
 */
public class ReWithdrawDetail implements java.io.Serializable {

    // Fields

    // ID
    private Long withdrawId;
    // 用户ID
    private Integer userId;
    // 提现类型
    private String withdrawType;
    // 提现账户
    private String withdrawAccount;
    // 账户名称
    private String accountName;
    // 提现状态; 0:审核中, 1:已完成, 2:未通过
    private Integer withdrawStatus;
    // 备注
    private String remarks;
    // 申请金额(元)
    private BigDecimal applyMoney;
    // 到账金额(元)
    private BigDecimal withdrawMoney;
    // 申请时间,如:2016-08-18 12:53:30
    private String applyTime;
    // 确认时间,如:2016-08-18 12:53:30
    private String withdrawTime;

    // Constructors

    /**
     * default constructor
     */
    public ReWithdrawDetail() {
    }

    /**
     * full constructor
     */
    public ReWithdrawDetail(Integer userId, String withdrawType, String withdrawAccount, String accountName, Integer withdrawStatus, String remarks, BigDecimal applyMoney, BigDecimal withdrawMoney, String applyTime, String withdrawTime) {
        this.userId = userId;
        this.withdrawType = withdrawType;
        this.withdrawAccount = withdrawAccount;
        this.accountName = accountName;
        this.withdrawStatus = withdrawStatus;
        this.remarks = remarks;
        this.applyMoney = applyMoney;
        this.withdrawMoney = withdrawMoney;
        this.applyTime = applyTime;
        this.withdrawTime = withdrawTime;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getWithdrawId() {
        return this.withdrawId;
    }

    /**
     * ID
     */
    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
    }

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
     * 提现类型
     */
    public String getWithdrawType() {
        return this.withdrawType;
    }

    /**
     * 提现类型
     */
    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    /**
     * 提现账户
     */
    public String getWithdrawAccount() {
        return this.withdrawAccount;
    }

    /**
     * 提现账户
     */
    public void setWithdrawAccount(String withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    /**
     * 账户名称
     */
    public String getAccountName() {
        return this.accountName;
    }

    /**
     * 账户名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 提现状态; 0:审核中, 1:已完成, 2:未通过
     */
    public Integer getWithdrawStatus() {
        return this.withdrawStatus;
    }

    /**
     * 提现状态; 0:审核中, 1:已完成, 2:未通过
     */
    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    /**
     * 备注
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 申请金额(元)
     */
    public BigDecimal getApplyMoney() {
        return this.applyMoney;
    }

    /**
     * 申请金额(元)
     */
    public void setApplyMoney(BigDecimal applyMoney) {
        this.applyMoney = applyMoney;
    }

    /**
     * 到账金额(元)
     */
    public BigDecimal getWithdrawMoney() {
        return this.withdrawMoney;
    }

    /**
     * 到账金额(元)
     */
    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    /**
     * 申请时间,如:2016-08-18 12:53:30
     */
    public String getApplyTime() {
        return this.applyTime;
    }

    /**
     * 申请时间,如:2016-08-18 12:53:30
     */
    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 确认时间,如:2016-08-18 12:53:30
     */
    public String getWithdrawTime() {
        return this.withdrawTime;
    }

    /**
     * 确认时间,如:2016-08-18 12:53:30
     */
    public void setWithdrawTime(String withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

}