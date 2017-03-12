package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-20
 */
public class ReAuxiliaryStatistic implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 当前步骤编号
    private Integer stepId;
    // 平台 0-ios 1-安卓
    private Integer platform;
    // 日期时间,如:2016-08-18
    private String statisticTime;
    // 提交数量
    private Integer submitNum;

    // Constructors

    /**
     * default constructor
     */
    public ReAuxiliaryStatistic() {
    }

    /**
     * full constructor
     */
    public ReAuxiliaryStatistic(Long missionId, Integer stepId, Integer platform, String statisticTime, Integer submitNum) {
        this.missionId = missionId;
        this.stepId = stepId;
        this.platform = platform;
        this.statisticTime = statisticTime;
        this.submitNum = submitNum;
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
     * 当前步骤编号
     */
    public Integer getStepId() {
        return this.stepId;
    }

    /**
     * 当前步骤编号
     */
    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    /**
     * 平台 0-ios 1-安卓
     */
    public Integer getPlatform() {
        return this.platform;
    }

    /**
     * 平台 0-ios 1-安卓
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * 日期时间,如:2016-08-18
     */
    public String getStatisticTime() {
        return this.statisticTime;
    }

    /**
     * 日期时间,如:2016-08-18
     */
    public void setStatisticTime(String statisticTime) {
        this.statisticTime = statisticTime;
    }

    /**
     * 提交数量
     */
    public Integer getSubmitNum() {
        return this.submitNum;
    }

    /**
     * 提交数量
     */
    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

}