package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-20
 */
public class ReUserCommissionDetail implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 用户ID
    private Integer userId;
    // 金额
    private BigDecimal accountMoney;
    // 类型;0:支出,1:收入
    private Integer detailType;
    // 详情
    private String detailContent;
    // 时间,如:2016-08-18 12:53:30
    private String detailTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserCommissionDetail() {
    }

    /**
     * full constructor
     */
    public ReUserCommissionDetail(Integer userId, BigDecimal accountMoney, Integer detailType, String detailContent, String detailTime) {
        this.userId = userId;
        this.accountMoney = accountMoney;
        this.detailType = detailType;
        this.detailContent = detailContent;
        this.detailTime = detailTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
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
     * 金额
     */
    public BigDecimal getAccountMoney() {
        return this.accountMoney;
    }

    /**
     * 金额
     */
    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    /**
     * 类型;0:支出,1:收入
     */
    public Integer getDetailType() {
        return this.detailType;
    }

    /**
     * 类型;0:支出,1:收入
     */
    public void setDetailType(Integer detailType) {
        this.detailType = detailType;
    }

    /**
     * 详情
     */
    public String getDetailContent() {
        return this.detailContent;
    }

    /**
     * 详情
     */
    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public String getDetailTime() {
        return this.detailTime;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }

}