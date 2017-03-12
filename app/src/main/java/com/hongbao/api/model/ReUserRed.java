package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-28
 */
public class ReUserRed implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long redId;
    // 发红包用户id
    private Integer userId;
    // 红包总个数
    private Integer redTotalNum;
    // 红包剩余个数
    private Integer redLeftNum;
    // 单个红包金额(元)
    private BigDecimal singleMoney;
    // 总金额(元)
    private BigDecimal totalMoney;
    // 红包内容
    private String redContent;
    // 如果未抢完,是否已经退回: 0-未退回 1-已退回
    private Integer isRefund;
    // 创建时间
    private String createTime;
    // 创建时间
    private Long createTimeMs;
    // 更新时间
    private String updateTime;
    // 退还时间
    private Long refundTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserRed() {
    }

    /**
     * full constructor
     */
    public ReUserRed(Integer userId, Integer redTotalNum, Integer redLeftNum, BigDecimal singleMoney, BigDecimal totalMoney, String redContent, Integer isRefund, String createTime, Long createTimeMs, String updateTime, Long refundTime) {
        this.userId = userId;
        this.redTotalNum = redTotalNum;
        this.redLeftNum = redLeftNum;
        this.singleMoney = singleMoney;
        this.totalMoney = totalMoney;
        this.redContent = redContent;
        this.isRefund = isRefund;
        this.createTime = createTime;
        this.createTimeMs = createTimeMs;
        this.updateTime = updateTime;
        this.refundTime = refundTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getRedId() {
        return this.redId;
    }

    /**
     * id，自增长
     */
    public void setRedId(Long redId) {
        this.redId = redId;
    }

    /**
     * 发红包用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 发红包用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 红包总个数
     */
    public Integer getRedTotalNum() {
        return this.redTotalNum;
    }

    /**
     * 红包总个数
     */
    public void setRedTotalNum(Integer redTotalNum) {
        this.redTotalNum = redTotalNum;
    }

    /**
     * 红包剩余个数
     */
    public Integer getRedLeftNum() {
        return this.redLeftNum;
    }

    /**
     * 红包剩余个数
     */
    public void setRedLeftNum(Integer redLeftNum) {
        this.redLeftNum = redLeftNum;
    }

    /**
     * 单个红包金额(元)
     */
    public BigDecimal getSingleMoney() {
        return this.singleMoney;
    }

    /**
     * 单个红包金额(元)
     */
    public void setSingleMoney(BigDecimal singleMoney) {
        this.singleMoney = singleMoney;
    }

    /**
     * 总金额(元)
     */
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    /**
     * 总金额(元)
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 红包内容
     */
    public String getRedContent() {
        return this.redContent;
    }

    /**
     * 红包内容
     */
    public void setRedContent(String redContent) {
        this.redContent = redContent;
    }

    /**
     * 如果未抢完,是否已经退回: 0-未退回 1-已退回
     */
    public Integer getIsRefund() {
        return this.isRefund;
    }

    /**
     * 如果未抢完,是否已经退回: 0-未退回 1-已退回
     */
    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    /**
     * 创建时间
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建时间
     */
    public Long getCreateTimeMs() {
        return this.createTimeMs;
    }

    /**
     * 创建时间
     */
    public void setCreateTimeMs(Long createTimeMs) {
        this.createTimeMs = createTimeMs;
    }

    /**
     * 更新时间
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 退还时间
     */
    public Long getRefundTime() {
        return this.refundTime;
    }

    /**
     * 退还时间
     */
    public void setRefundTime(Long refundTime) {
        this.refundTime = refundTime;
    }

}