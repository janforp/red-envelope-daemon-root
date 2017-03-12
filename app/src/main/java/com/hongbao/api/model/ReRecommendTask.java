package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-07
 */
public class ReRecommendTask implements java.io.Serializable {

    // Fields

    // id
    private Long taskId;
    // 用户ID
    private Integer userId;
    // 任务id
    private Long missionId;
    // 提交文字
    private String commitText;
    // 提交图片
    private String commitImg;
    // 提交要求
    private String commitRequire;
    // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
    private Integer taskStatus;
    // 审核备注
    private String verifyRemarks;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;
    // 任务过期,释放时间
    private Long releaseTime;

    // Constructors

    /**
     * default constructor
     */
    public ReRecommendTask() {
    }

    /**
     * full constructor
     */
    public ReRecommendTask(Integer userId, Long missionId, String commitText, String commitImg, String commitRequire, Integer taskStatus, String verifyRemarks, Long createTime, Long updateTime, Long releaseTime) {
        this.userId = userId;
        this.missionId = missionId;
        this.commitText = commitText;
        this.commitImg = commitImg;
        this.commitRequire = commitRequire;
        this.taskStatus = taskStatus;
        this.verifyRemarks = verifyRemarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.releaseTime = releaseTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getTaskId() {
        return this.taskId;
    }

    /**
     * id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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
     * 提交文字
     */
    public String getCommitText() {
        return this.commitText;
    }

    /**
     * 提交文字
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
     * 提交要求
     */
    public String getCommitRequire() {
        return this.commitRequire;
    }

    /**
     * 提交要求
     */
    public void setCommitRequire(String commitRequire) {
        this.commitRequire = commitRequire;
    }

    /**
     * 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
     */
    public Integer getTaskStatus() {
        return this.taskStatus;
    }

    /**
     * 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 审核备注
     */
    public String getVerifyRemarks() {
        return this.verifyRemarks;
    }

    /**
     * 审核备注
     */
    public void setVerifyRemarks(String verifyRemarks) {
        this.verifyRemarks = verifyRemarks;
    }

    /**
     * 创建时间
     */
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Long getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 任务过期,释放时间
     */
    public Long getReleaseTime() {
        return this.releaseTime;
    }

    /**
     * 任务过期,释放时间
     */
    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

}