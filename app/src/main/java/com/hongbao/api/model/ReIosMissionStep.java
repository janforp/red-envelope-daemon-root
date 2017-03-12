package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public class ReIosMissionStep implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 步骤编号
    private Integer stepId;
    // 任务标题
    private String missionTitle;
    // 任务描述
    private String missionDesc;
    // 总量
    private Integer totalNum;
    // 剩余量
    private Integer leftNum;
    // 任务奖励
    private BigDecimal missionMoney;
    // 任务标签
    private String missionLabel;
    // 图片
    private String missionImg;
    // 审核要求
    private String checkRequire;
    // 是否有按钮，0:无，1:有
    private Integer isBtn;
    // 按钮标题
    private String btnTitle;
    // 按钮链接
    private String btnUrl;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReIosMissionStep() {
    }

    /**
     * full constructor
     */
    public ReIosMissionStep(Long missionId, Integer stepId, String missionTitle, String missionDesc, Integer totalNum, Integer leftNum, BigDecimal missionMoney, String missionLabel, String missionImg, String checkRequire, Integer isBtn, String btnTitle, String btnUrl, String createTime, String updateTime) {
        this.missionId = missionId;
        this.stepId = stepId;
        this.missionTitle = missionTitle;
        this.missionDesc = missionDesc;
        this.totalNum = totalNum;
        this.leftNum = leftNum;
        this.missionMoney = missionMoney;
        this.missionLabel = missionLabel;
        this.missionImg = missionImg;
        this.checkRequire = checkRequire;
        this.isBtn = isBtn;
        this.btnTitle = btnTitle;
        this.btnUrl = btnUrl;
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
     * 步骤编号
     */
    public Integer getStepId() {
        return this.stepId;
    }

    /**
     * 步骤编号
     */
    public void setStepId(Integer stepId) {
        this.stepId = stepId;
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
     * 是否有按钮，0:无，1:有
     */
    public Integer getIsBtn() {
        return this.isBtn;
    }

    /**
     * 是否有按钮，0:无，1:有
     */
    public void setIsBtn(Integer isBtn) {
        this.isBtn = isBtn;
    }

    /**
     * 按钮标题
     */
    public String getBtnTitle() {
        return this.btnTitle;
    }

    /**
     * 按钮标题
     */
    public void setBtnTitle(String btnTitle) {
        this.btnTitle = btnTitle;
    }

    /**
     * 按钮链接
     */
    public String getBtnUrl() {
        return this.btnUrl;
    }

    /**
     * 按钮链接
     */
    public void setBtnUrl(String btnUrl) {
        this.btnUrl = btnUrl;
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