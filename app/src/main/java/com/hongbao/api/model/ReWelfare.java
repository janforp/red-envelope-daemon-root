package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-15
 */
public class ReWelfare implements java.io.Serializable {

    // Fields

    // 福利id，自增长
    private Long welfareId;
    // 福利标题
    private String welfareTitle;
    // 福利图标
    private String welfareIcon;
    // 奖励,如:100元红包券,10元观影券,购物券等
    private String welfareReward;
    // 分类: 0-未分类
    private Integer welfareType;
    // 子类型,用逗号分隔开，如:爱奇艺,优酷
    private String subTypeName;
    // 是否精选: 0-否,1-是
    private Integer isSelection;
    // 商家图标
    private String merchantIcon;
    // 商家名称
    private String merchantName;
    // 参加人数
    private Integer participantsNum;
    // 福利banner
    private String welfareBanner;
    // 福利详情
    private String welfareDetail;
    // 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
    private Integer welfareStatus;
    // 按钮名称
    private String buttonName;
    // 按钮链接
    private String buttonUrl;
    // 结束时间,如:2016-08-18 12:53:30
    private String endTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 权重0-100, 越大越靠前
    private Integer welfareWeight;

    // Constructors

    /**
     * default constructor
     */
    public ReWelfare() {
    }

    /**
     * full constructor
     */
    public ReWelfare(String welfareTitle, String welfareIcon, String welfareReward, Integer welfareType, String subTypeName, Integer isSelection, String merchantIcon, String merchantName, Integer participantsNum, String welfareBanner, String welfareDetail, Integer welfareStatus, String buttonName, String buttonUrl, String endTime, String createTime, String updateTime, Integer welfareWeight) {
        this.welfareTitle = welfareTitle;
        this.welfareIcon = welfareIcon;
        this.welfareReward = welfareReward;
        this.welfareType = welfareType;
        this.subTypeName = subTypeName;
        this.isSelection = isSelection;
        this.merchantIcon = merchantIcon;
        this.merchantName = merchantName;
        this.participantsNum = participantsNum;
        this.welfareBanner = welfareBanner;
        this.welfareDetail = welfareDetail;
        this.welfareStatus = welfareStatus;
        this.buttonName = buttonName;
        this.buttonUrl = buttonUrl;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.welfareWeight = welfareWeight;
    }

    // Property accessors

    /**
     * 福利id，自增长
     */
    public Long getWelfareId() {
        return this.welfareId;
    }

    /**
     * 福利id，自增长
     */
    public void setWelfareId(Long welfareId) {
        this.welfareId = welfareId;
    }

    /**
     * 福利标题
     */
    public String getWelfareTitle() {
        return this.welfareTitle;
    }

    /**
     * 福利标题
     */
    public void setWelfareTitle(String welfareTitle) {
        this.welfareTitle = welfareTitle;
    }

    /**
     * 福利图标
     */
    public String getWelfareIcon() {
        return this.welfareIcon;
    }

    /**
     * 福利图标
     */
    public void setWelfareIcon(String welfareIcon) {
        this.welfareIcon = welfareIcon;
    }

    /**
     * 奖励,如:100元红包券,10元观影券,购物券等
     */
    public String getWelfareReward() {
        return this.welfareReward;
    }

    /**
     * 奖励,如:100元红包券,10元观影券,购物券等
     */
    public void setWelfareReward(String welfareReward) {
        this.welfareReward = welfareReward;
    }

    /**
     * 分类: 0-未分类
     */
    public Integer getWelfareType() {
        return this.welfareType;
    }

    /**
     * 分类: 0-未分类
     */
    public void setWelfareType(Integer welfareType) {
        this.welfareType = welfareType;
    }

    /**
     * 子类型,用逗号分隔开，如:爱奇艺,优酷
     */
    public String getSubTypeName() {
        return this.subTypeName;
    }

    /**
     * 子类型,用逗号分隔开，如:爱奇艺,优酷
     */
    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    /**
     * 是否精选: 0-否,1-是
     */
    public Integer getIsSelection() {
        return this.isSelection;
    }

    /**
     * 是否精选: 0-否,1-是
     */
    public void setIsSelection(Integer isSelection) {
        this.isSelection = isSelection;
    }

    /**
     * 商家图标
     */
    public String getMerchantIcon() {
        return this.merchantIcon;
    }

    /**
     * 商家图标
     */
    public void setMerchantIcon(String merchantIcon) {
        this.merchantIcon = merchantIcon;
    }

    /**
     * 商家名称
     */
    public String getMerchantName() {
        return this.merchantName;
    }

    /**
     * 商家名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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
     * 福利banner
     */
    public String getWelfareBanner() {
        return this.welfareBanner;
    }

    /**
     * 福利banner
     */
    public void setWelfareBanner(String welfareBanner) {
        this.welfareBanner = welfareBanner;
    }

    /**
     * 福利详情
     */
    public String getWelfareDetail() {
        return this.welfareDetail;
    }

    /**
     * 福利详情
     */
    public void setWelfareDetail(String welfareDetail) {
        this.welfareDetail = welfareDetail;
    }

    /**
     * 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
     */
    public Integer getWelfareStatus() {
        return this.welfareStatus;
    }

    /**
     * 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
     */
    public void setWelfareStatus(Integer welfareStatus) {
        this.welfareStatus = welfareStatus;
    }

    /**
     * 按钮名称
     */
    public String getButtonName() {
        return this.buttonName;
    }

    /**
     * 按钮名称
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    /**
     * 按钮链接
     */
    public String getButtonUrl() {
        return this.buttonUrl;
    }

    /**
     * 按钮链接
     */
    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    /**
     * 结束时间,如:2016-08-18 12:53:30
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 结束时间,如:2016-08-18 12:53:30
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    /**
     * 权重0-100, 越大越靠前
     */
    public Integer getWelfareWeight() {
        return this.welfareWeight;
    }

    /**
     * 权重0-100, 越大越靠前
     */
    public void setWelfareWeight(Integer welfareWeight) {
        this.welfareWeight = welfareWeight;
    }

}