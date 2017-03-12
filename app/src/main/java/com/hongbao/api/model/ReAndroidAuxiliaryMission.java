package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public class ReAndroidAuxiliaryMission implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 任务编号
    private Integer missionNo;
    // 任务标题
    private String missionTitle;
    // 任务标签
    private String missionLabel;
    // 总量
    private Integer totalNum;
    // 剩余量
    private Integer leftNum;
    // 任务详情
    private String missionDesc;
    // 图片
    private String missionImg;
    // 任务奖励
    private BigDecimal missionMoney;
    // 审核要求
    private String checkRequire;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAndroidAuxiliaryMission() {
    }

    /**
     * full constructor
     */
    public ReAndroidAuxiliaryMission(Long missionId, Integer missionNo, String missionTitle, String missionLabel, Integer totalNum, Integer leftNum, String missionDesc, String missionImg, BigDecimal missionMoney, String checkRequire, String createTime, String updateTime) {
        this.missionId = missionId;
        this.missionNo = missionNo;
        this.missionTitle = missionTitle;
        this.missionLabel = missionLabel;
        this.totalNum = totalNum;
        this.leftNum = leftNum;
        this.missionDesc = missionDesc;
        this.missionImg = missionImg;
        this.missionMoney = missionMoney;
        this.checkRequire = checkRequire;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * 任务id
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务id
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 任务编号
     */
    public Integer getMissionNo() {
        return this.missionNo;
    }

    /**
     * 任务编号
     */
    public void setMissionNo(Integer missionNo) {
        this.missionNo = missionNo;
    }

    /**
     * 任务标题
     */
    public String getMissionTitle() {
        return this.missionTitle;
    }

    /**
     * 任务标题
     */
    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    /**
     * 任务标签
     */
    public String getMissionLabel() {
        return this.missionLabel;
    }

    /**
     * 任务标签
     */
    public void setMissionLabel(String missionLabel) {
        this.missionLabel = missionLabel;
    }

    /**
     * 总量
     */
    public Integer getTotalNum() {
        return this.totalNum;
    }

    /**
     * 总量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 剩余量
     */
    public Integer getLeftNum() {
        return this.leftNum;
    }

    /**
     * 剩余量
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    /**
     * 任务详情
     */
    public String getMissionDesc() {
        return this.missionDesc;
    }

    /**
     * 任务详情
     */
    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    /**
     * 图片
     */
    public String getMissionImg() {
        return this.missionImg;
    }

    /**
     * 图片
     */
    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    /**
     * 任务奖励
     */
    public BigDecimal getMissionMoney() {
        return this.missionMoney;
    }

    /**
     * 任务奖励
     */
    public void setMissionMoney(BigDecimal missionMoney) {
        this.missionMoney = missionMoney;
    }

    /**
     * 审核要求
     */
    public String getCheckRequire() {
        return this.checkRequire;
    }

    /**
     * 审核要求
     */
    public void setCheckRequire(String checkRequire) {
        this.checkRequire = checkRequire;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}