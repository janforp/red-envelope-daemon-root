package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-07
 */
public class ReAndroidMissionDepthKeep implements java.io.Serializable {

    // Fields

    // 任务id
    private Long missionId;
    // 深度任务id, 第几天打开
    private Integer depthId;
    // 数据日期
    private String dataDay;
    // 激活量
    private Integer activateNum;
    // 计划量
    private Integer planNum;
    // 实际量
    private Integer realNum;

    // Constructors

    /**
     * default constructor
     */
    public ReAndroidMissionDepthKeep() {
    }

    /**
     * full constructor
     */
    public ReAndroidMissionDepthKeep(Long missionId, Integer depthId, String dataDay, Integer activateNum, Integer planNum, Integer realNum) {
        this.missionId = missionId;
        this.depthId = depthId;
        this.dataDay = dataDay;
        this.activateNum = activateNum;
        this.planNum = planNum;
        this.realNum = realNum;
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
     * 深度任务id, 第几天打开
     */
    public Integer getDepthId() {
        return this.depthId;
    }

    /**
     * 深度任务id, 第几天打开
     */
    public void setDepthId(Integer depthId) {
        this.depthId = depthId;
    }

    /**
     * 数据日期
     */
    public String getDataDay() {
        return this.dataDay;
    }

    /**
     * 数据日期
     */
    public void setDataDay(String dataDay) {
        this.dataDay = dataDay;
    }

    /**
     * 激活量
     */
    public Integer getActivateNum() {
        return this.activateNum;
    }

    /**
     * 激活量
     */
    public void setActivateNum(Integer activateNum) {
        this.activateNum = activateNum;
    }

    /**
     * 计划量
     */
    public Integer getPlanNum() {
        return this.planNum;
    }

    /**
     * 计划量
     */
    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    /**
     * 实际量
     */
    public Integer getRealNum() {
        return this.realNum;
    }

    /**
     * 实际量
     */
    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

}