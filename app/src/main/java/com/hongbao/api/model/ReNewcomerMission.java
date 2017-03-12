package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-17
 */
public class ReNewcomerMission implements java.io.Serializable {

    // Fields

    // 任务ID
    private Long missionId;
    // 任务名字
    private String missionName;
    // 金额(元)
    private BigDecimal missionAward;
    // 任务类型
    private Integer missionType;
    // 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer missionStatus;
    // 任务权重, 值较大者排在前面
    private Integer missionOrder;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 修改时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReNewcomerMission() {
    }

    /**
     * full constructor
     */
    public ReNewcomerMission(String missionName, BigDecimal missionAward, Integer missionType, Integer missionStatus, Integer missionOrder, String createTime, String updateTime) {
        this.missionName = missionName;
        this.missionAward = missionAward;
        this.missionType = missionType;
        this.missionStatus = missionStatus;
        this.missionOrder = missionOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * 任务ID
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务ID
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 任务名字
     */
    public String getMissionName() {
        return this.missionName;
    }

    /**
     * 任务名字
     */
    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    /**
     * 金额(元)
     */
    public BigDecimal getMissionAward() {
        return this.missionAward;
    }

    /**
     * 金额(元)
     */
    public void setMissionAward(BigDecimal missionAward) {
        this.missionAward = missionAward;
    }

    /**
     * 任务类型
     */
    public Integer getMissionType() {
        return this.missionType;
    }

    /**
     * 任务类型
     */
    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    /**
     * 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getMissionStatus() {
        return this.missionStatus;
    }

    /**
     * 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    /**
     * 任务权重, 值较大者排在前面
     */
    public Integer getMissionOrder() {
        return this.missionOrder;
    }

    /**
     * 任务权重, 值较大者排在前面
     */
    public void setMissionOrder(Integer missionOrder) {
        this.missionOrder = missionOrder;
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
     * 修改时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 修改时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}