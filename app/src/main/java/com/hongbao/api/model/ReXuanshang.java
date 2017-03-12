package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-11
 */
public class ReXuanshang implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long id;
    // 发任务用户id
    private Integer userId;
    // 任务总个数
    private Integer totalNum;
    // 审核通过个数
    private Integer passNum;
    // 单个任务金额(元)
    private BigDecimal singleMoney;
    // 任务描述
    private String missionDesc;
    // 图片用分号隔开
    private String missionImg;
    // 逻辑删除，0:未删除，1:已删除
    private Integer isDelete;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReXuanshang() {
    }

    /**
     * full constructor
     */
    public ReXuanshang(Integer userId, Integer totalNum, Integer passNum, BigDecimal singleMoney, String missionDesc, String missionImg, Integer isDelete, String createTime, String updateTime) {
        this.userId = userId;
        this.totalNum = totalNum;
        this.passNum = passNum;
        this.singleMoney = singleMoney;
        this.missionDesc = missionDesc;
        this.missionImg = missionImg;
        this.isDelete = isDelete;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id，自增长
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 发任务用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 发任务用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 任务总个数
     */
    public Integer getTotalNum() {
        return this.totalNum;
    }

    /**
     * 任务总个数
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 审核通过个数
     */
    public Integer getPassNum() {
        return this.passNum;
    }

    /**
     * 审核通过个数
     */
    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    /**
     * 单个任务金额(元)
     */
    public BigDecimal getSingleMoney() {
        return this.singleMoney;
    }

    /**
     * 单个任务金额(元)
     */
    public void setSingleMoney(BigDecimal singleMoney) {
        this.singleMoney = singleMoney;
    }

    /**
     * 任务描述
     */
    public String getMissionDesc() {
        return this.missionDesc;
    }

    /**
     * 任务描述
     */
    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    /**
     * 图片用分号隔开
     */
    public String getMissionImg() {
        return this.missionImg;
    }

    /**
     * 图片用分号隔开
     */
    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    /**
     * 逻辑删除，0:未删除，1:已删除
     */
    public Integer getIsDelete() {
        return this.isDelete;
    }

    /**
     * 逻辑删除，0:未删除，1:已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

}