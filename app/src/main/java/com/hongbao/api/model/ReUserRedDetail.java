package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
public class ReUserRedDetail implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long detailId;
    // 发红包用户id
    private Long redId;
    // 抢红包用户id
    private Integer userId;
    // 红包金额(元)
    private BigDecimal redMoney;
    // 创建时间
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserRedDetail() {
    }

    /**
     * full constructor
     */
    public ReUserRedDetail(Long redId, Integer userId, BigDecimal redMoney, String createTime) {
        this.redId = redId;
        this.userId = userId;
        this.redMoney = redMoney;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getDetailId() {
        return this.detailId;
    }

    /**
     * id，自增长
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    /**
     * 发红包用户id
     */
    public Long getRedId() {
        return this.redId;
    }

    /**
     * 发红包用户id
     */
    public void setRedId(Long redId) {
        this.redId = redId;
    }

    /**
     * 抢红包用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 抢红包用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 红包金额(元)
     */
    public BigDecimal getRedMoney() {
        return this.redMoney;
    }

    /**
     * 红包金额(元)
     */
    public void setRedMoney(BigDecimal redMoney) {
        this.redMoney = redMoney;
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

}