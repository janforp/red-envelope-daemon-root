package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-10
 */
public class ReIosMissionFinish implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 步骤编号
    private Integer stepId;
    // 用户id
    private Integer userId;
    // 状态 0-审核中 1-已通过 2-未通过 3-重新提交
    private Integer missionStatus;
    // 提交内容
    private String commitText;
    // 提交图片
    private String commitImg;
    // 审核备注
    private String checkText;
    // 重新提交状态下, 超过这个时间就自动放弃
    private Long abandonTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReIosMissionFinish() {
    }

    /**
     * full constructor
     */
    public ReIosMissionFinish(Long missionId, Integer stepId, Integer userId, Integer missionStatus, String commitText, String commitImg, String checkText, Long abandonTime, String createTime, String updateTime) {
        this.missionId = missionId;
        this.stepId = stepId;
        this.userId = userId;
        this.missionStatus = missionStatus;
        this.commitText = commitText;
        this.commitImg = commitImg;
        this.checkText = checkText;
        this.abandonTime = abandonTime;
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
     * 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 状态 0-审核中 1-已通过 2-未通过 3-重新提交
     */
    public Integer getMissionStatus() {
        return this.missionStatus;
    }

    /**
     * 状态 0-审核中 1-已通过 2-未通过 3-重新提交
     */
    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    /**
     * 提交内容
     */
    public String getCommitText() {
        return this.commitText;
    }

    /**
     * 提交内容
     */
    public void setCommitText(String commitText) {
        this.commitText = commitText;
    }

    /**
     * 提交图片
     */
    public String getCommitImg() {
        return this.commitImg;
    }

    /**
     * 提交图片
     */
    public void setCommitImg(String commitImg) {
        this.commitImg = commitImg;
    }

    /**
     * 审核备注
     */
    public String getCheckText() {
        return this.checkText;
    }

    /**
     * 审核备注
     */
    public void setCheckText(String checkText) {
        this.checkText = checkText;
    }

    /**
     * 重新提交状态下, 超过这个时间就自动放弃
     */
    public Long getAbandonTime() {
        return this.abandonTime;
    }

    /**
     * 重新提交状态下, 超过这个时间就自动放弃
     */
    public void setAbandonTime(Long abandonTime) {
        this.abandonTime = abandonTime;
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