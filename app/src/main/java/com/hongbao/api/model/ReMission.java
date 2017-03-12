package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-19
 */
public class ReMission implements java.io.Serializable {

    // Fields

    // 任务id
    private Integer missionId;
    // 任务名字
    private String missionName;
    // 任务图标
    private String missionImg;
    // 奖励,如:100元红包券,10元观影券,购物券等
    private String missionReward;
    // 目前获得奖励的人数
    private Integer gainRewardNum;
    // 任务广告图片
    private String missionAdImg;
    // 广告图片的链接,可以是网址,也可以是app下载地址
    private String missionUrl;
    // 是否为热门任务,0:否,1:是
    private Integer missionHot;
    // 任务分类
    private Integer missionSort;
    // 任务排序,值较小者排在较前
    private Integer missionOrder;
    // 参加人数
    private Integer participantsNum;
    // 开始时间
    private Integer startTime;
    // 结束时间
    private Integer endTime;
    // 任务状态,0:已结束,1:进行中
    private Integer missionStatus;
    // 任务步骤
    private String missionStep;
    // 任务规则
    private String missionRule;
    // 商家名字
    private String merchantName;
    // 奖励,如:100元红包券,10元观影券,购物券等
    private String missionExtraReward;
    // 商家描述
    private String merchantDetail;
    // 任务开关, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer showOrNot;

    // Constructors

    /**
     * default constructor
     */
    public ReMission() {
    }

    /**
     * full constructor
     */
    public ReMission(String missionName, String missionImg, String missionReward, Integer gainRewardNum, String missionAdImg, String missionUrl, Integer missionHot, Integer missionSort, Integer missionOrder, Integer participantsNum, Integer startTime, Integer endTime, Integer missionStatus, String missionStep, String missionRule, String merchantName, String missionExtraReward, String merchantDetail, Integer showOrNot) {
        this.missionName = missionName;
        this.missionImg = missionImg;
        this.missionReward = missionReward;
        this.gainRewardNum = gainRewardNum;
        this.missionAdImg = missionAdImg;
        this.missionUrl = missionUrl;
        this.missionHot = missionHot;
        this.missionSort = missionSort;
        this.missionOrder = missionOrder;
        this.participantsNum = participantsNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.missionStatus = missionStatus;
        this.missionStep = missionStep;
        this.missionRule = missionRule;
        this.merchantName = merchantName;
        this.missionExtraReward = missionExtraReward;
        this.merchantDetail = merchantDetail;
        this.showOrNot = showOrNot;
    }

    // Property accessors

    /**
     * 任务id
     */
    public Integer getMissionId() {
        return this.missionId;
    }

    /**
     * 任务id
     */
    public void setMissionId(Integer missionId) {
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
     * 任务图标
     */
    public String getMissionImg() {
        return this.missionImg;
    }

    /**
     * 任务图标
     */
    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    /**
     * 奖励,如:100元红包券,10元观影券,购物券等
     */
    public String getMissionReward() {
        return this.missionReward;
    }

    /**
     * 奖励,如:100元红包券,10元观影券,购物券等
     */
    public void setMissionReward(String missionReward) {
        this.missionReward = missionReward;
    }

    /**
     * 目前获得奖励的人数
     */
    public Integer getGainRewardNum() {
        return this.gainRewardNum;
    }

    /**
     * 目前获得奖励的人数
     */
    public void setGainRewardNum(Integer gainRewardNum) {
        this.gainRewardNum = gainRewardNum;
    }

    /**
     * 任务广告图片
     */
    public String getMissionAdImg() {
        return this.missionAdImg;
    }

    /**
     * 任务广告图片
     */
    public void setMissionAdImg(String missionAdImg) {
        this.missionAdImg = missionAdImg;
    }

    /**
     * 广告图片的链接,可以是网址,也可以是app下载地址
     */
    public String getMissionUrl() {
        return this.missionUrl;
    }

    /**
     * 广告图片的链接,可以是网址,也可以是app下载地址
     */
    public void setMissionUrl(String missionUrl) {
        this.missionUrl = missionUrl;
    }

    /**
     * 是否为热门任务,0:否,1:是
     */
    public Integer getMissionHot() {
        return this.missionHot;
    }

    /**
     * 是否为热门任务,0:否,1:是
     */
    public void setMissionHot(Integer missionHot) {
        this.missionHot = missionHot;
    }

    /**
     * 任务分类
     */
    public Integer getMissionSort() {
        return this.missionSort;
    }

    /**
     * 任务分类
     */
    public void setMissionSort(Integer missionSort) {
        this.missionSort = missionSort;
    }

    /**
     * 任务排序,值较小者排在较前
     */
    public Integer getMissionOrder() {
        return this.missionOrder;
    }

    /**
     * 任务排序,值较小者排在较前
     */
    public void setMissionOrder(Integer missionOrder) {
        this.missionOrder = missionOrder;
    }

    /**
     * 参加人数
     */
    public Integer getParticipantsNum() {
        return this.participantsNum;
    }

    /**
     * 参加人数
     */
    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    /**
     * 开始时间
     */
    public Integer getStartTime() {
        return this.startTime;
    }

    /**
     * 开始时间
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     */
    public Integer getEndTime() {
        return this.endTime;
    }

    /**
     * 结束时间
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * 任务状态,0:已结束,1:进行中
     */
    public Integer getMissionStatus() {
        return this.missionStatus;
    }

    /**
     * 任务状态,0:已结束,1:进行中
     */
    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    /**
     * 任务步骤
     */
    public String getMissionStep() {
        return this.missionStep;
    }

    /**
     * 任务步骤
     */
    public void setMissionStep(String missionStep) {
        this.missionStep = missionStep;
    }

    /**
     * 任务规则
     */
    public String getMissionRule() {
        return this.missionRule;
    }

    /**
     * 任务规则
     */
    public void setMissionRule(String missionRule) {
        this.missionRule = missionRule;
    }

    /**
     * 商家名字
     */
    public String getMerchantName() {
        return this.merchantName;
    }

    /**
     * 商家名字
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 奖励,如:100元红包券,10元观影券,购物券等
     */
    public String getMissionExtraReward() {
        return this.missionExtraReward;
    }

    /**
     * 奖励,如:100元红包券,10元观影券,购物券等
     */
    public void setMissionExtraReward(String missionExtraReward) {
        this.missionExtraReward = missionExtraReward;
    }

    /**
     * 商家描述
     */
    public String getMerchantDetail() {
        return this.merchantDetail;
    }

    /**
     * 商家描述
     */
    public void setMerchantDetail(String merchantDetail) {
        this.merchantDetail = merchantDetail;
    }

    /**
     * 任务开关, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getShowOrNot() {
        return this.showOrNot;
    }

    /**
     * 任务开关, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setShowOrNot(Integer showOrNot) {
        this.showOrNot = showOrNot;
    }

}