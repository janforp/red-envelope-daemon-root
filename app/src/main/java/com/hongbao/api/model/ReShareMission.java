package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-15
 */
public class ReShareMission implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long missionId;
    // 朋友圈每次点击得到的奖励
    private BigDecimal money;
    // 任务名字
    private String missionTitle;
    // 任务图标
    private String missionIcon;
    // 接口链接
    private String interfaceUrl;
    // 回调链接,如:广告链接
    private String callbackUrl;
    // 任务描述
    private String missionDesc;
    // 任务所需文字
    private String missionText;
    // 任务所需图标
    private String missionImg;
    // 示例图片
    private String exampleImg;
    // 共需点击次数
    private Integer totalClickTimes;
    // 剩余点击次数
    private Integer leftClickTimes;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 活动是否已经结束，0:已结束，1:进行中
    private Integer isEnd;
    // 任务开始时间
    private String startTime;

    // Constructors

    /**
     * default constructor
     */
    public ReShareMission() {
    }

    /**
     * full constructor
     */
    public ReShareMission(BigDecimal money, String missionTitle, String missionIcon, String interfaceUrl, String callbackUrl, String missionDesc, String missionText, String missionImg, String exampleImg, Integer totalClickTimes, Integer leftClickTimes, String createTime, Integer isEnd, String startTime) {
        this.money = money;
        this.missionTitle = missionTitle;
        this.missionIcon = missionIcon;
        this.interfaceUrl = interfaceUrl;
        this.callbackUrl = callbackUrl;
        this.missionDesc = missionDesc;
        this.missionText = missionText;
        this.missionImg = missionImg;
        this.exampleImg = exampleImg;
        this.totalClickTimes = totalClickTimes;
        this.leftClickTimes = leftClickTimes;
        this.createTime = createTime;
        this.isEnd = isEnd;
        this.startTime = startTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * id，自增长
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 朋友圈每次点击得到的奖励
     */
    public BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 朋友圈每次点击得到的奖励
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 任务名字
     */
    public String getMissionTitle() {
        return this.missionTitle;
    }

    /**
     * 任务名字
     */
    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    /**
     * 任务图标
     */
    public String getMissionIcon() {
        return this.missionIcon;
    }

    /**
     * 任务图标
     */
    public void setMissionIcon(String missionIcon) {
        this.missionIcon = missionIcon;
    }

    /**
     * 接口链接
     */
    public String getInterfaceUrl() {
        return this.interfaceUrl;
    }

    /**
     * 接口链接
     */
    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    /**
     * 回调链接,如:广告链接
     */
    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    /**
     * 回调链接,如:广告链接
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
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
     * 任务所需文字
     */
    public String getMissionText() {
        return this.missionText;
    }

    /**
     * 任务所需文字
     */
    public void setMissionText(String missionText) {
        this.missionText = missionText;
    }

    /**
     * 任务所需图标
     */
    public String getMissionImg() {
        return this.missionImg;
    }

    /**
     * 任务所需图标
     */
    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    /**
     * 示例图片
     */
    public String getExampleImg() {
        return this.exampleImg;
    }

    /**
     * 示例图片
     */
    public void setExampleImg(String exampleImg) {
        this.exampleImg = exampleImg;
    }

    /**
     * 共需点击次数
     */
    public Integer getTotalClickTimes() {
        return this.totalClickTimes;
    }

    /**
     * 共需点击次数
     */
    public void setTotalClickTimes(Integer totalClickTimes) {
        this.totalClickTimes = totalClickTimes;
    }

    /**
     * 剩余点击次数
     */
    public Integer getLeftClickTimes() {
        return this.leftClickTimes;
    }

    /**
     * 剩余点击次数
     */
    public void setLeftClickTimes(Integer leftClickTimes) {
        this.leftClickTimes = leftClickTimes;
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
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public Integer getIsEnd() {
        return this.isEnd;
    }

    /**
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * 任务开始时间
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 任务开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}