package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-21
 */
public class RePasswordRed implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 红包口令
    private String redPassword;
    // 总数
    private Integer totalNum;
    // 剩余数
    private Integer leftNum;
    // 最小金额(元)
    private BigDecimal minMoney;
    // 最大金额(元)
    private BigDecimal maxMoney;
    // 关闭时间
    private String endTime;
    private String createTime;
    // 修改时间
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public RePasswordRed() {
    }

    /**
     * full constructor
     */
    public RePasswordRed(String redPassword, Integer totalNum, Integer leftNum, BigDecimal minMoney, BigDecimal maxMoney, String endTime, String createTime, String updateTime) {
        this.redPassword = redPassword;
        this.totalNum = totalNum;
        this.leftNum = leftNum;
        this.minMoney = minMoney;
        this.maxMoney = maxMoney;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
     * 红包口令
     */
    public String getRedPassword() {
        return this.redPassword;
    }

    /**
     * 红包口令
     */
    public void setRedPassword(String redPassword) {
        this.redPassword = redPassword;
    }

    /**
     * 总数
     */
    public Integer getTotalNum() {
        return this.totalNum;
    }

    /**
     * 总数
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 剩余数
     */
    public Integer getLeftNum() {
        return this.leftNum;
    }

    /**
     * 剩余数
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    /**
     * 最小金额(元)
     */
    public BigDecimal getMinMoney() {
        return this.minMoney;
    }

    /**
     * 最小金额(元)
     */
    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    /**
     * 最大金额(元)
     */
    public BigDecimal getMaxMoney() {
        return this.maxMoney;
    }

    /**
     * 最大金额(元)
     */
    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    /**
     * 关闭时间
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 关闭时间
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}