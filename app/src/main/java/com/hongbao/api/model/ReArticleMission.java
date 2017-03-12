package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
public class ReArticleMission implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long articleId;
    // 文章分类，0:自己爬的文章，1:客户的链接
    private Integer articleType;
    // 若该文章的类型是0，则此字段是后期点击跳转的广告链接，若该文章的类型是1，则就是文章链接
    private String articleUrl;
    // 是否直接跳转只广告页面，1：直接跳转，0：不跳转
    private Integer isDirectlyGoAd;
    // 文章标题
    private String articleTitle;
    // 文章内容
    private String articleContent;
    // 文章封面图
    private String articleIcon;
    // 文章显示时间,如:2016-08-18
    private String articleDisplayTime;
    // 文章作者信息
    private String articleAuthor;
    // app上阅读奖励，每个用户只有一次有效阅读
    private BigDecimal appReadMoney;
    // 单个有效好友点击获得的奖励
    private BigDecimal wxClickMoney;
    // 共需点击次数
    private Integer totalClickTimes;
    // 剩余点击次数
    private Integer leftClickTimes;
    // 任务开始时间,如:2016-08-18 12:53:30
    private String startMissionTime;
    // 任务结束时间,如:2016-08-18 12:53:30
    private String endMissionTime;
    // 任务创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 修改时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 该文章获得是否结束，0:已结束，1:进行中
    private Integer isEnd;
    // 该文章权重，排序时越大越靠前
    private Integer articleWeight;

    // Constructors

    /**
     * default constructor
     */
    public ReArticleMission() {
    }

    /**
     * full constructor
     */
    public ReArticleMission(Integer articleType, String articleUrl, Integer isDirectlyGoAd, String articleTitle, String articleContent, String articleIcon, String articleDisplayTime, String articleAuthor, BigDecimal appReadMoney, BigDecimal wxClickMoney, Integer totalClickTimes, Integer leftClickTimes, String startMissionTime, String endMissionTime, String createTime, String updateTime, Integer isEnd, Integer articleWeight) {
        this.articleType = articleType;
        this.articleUrl = articleUrl;
        this.isDirectlyGoAd = isDirectlyGoAd;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleIcon = articleIcon;
        this.articleDisplayTime = articleDisplayTime;
        this.articleAuthor = articleAuthor;
        this.appReadMoney = appReadMoney;
        this.wxClickMoney = wxClickMoney;
        this.totalClickTimes = totalClickTimes;
        this.leftClickTimes = leftClickTimes;
        this.startMissionTime = startMissionTime;
        this.endMissionTime = endMissionTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isEnd = isEnd;
        this.articleWeight = articleWeight;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getArticleId() {
        return this.articleId;
    }

    /**
     * id，自增长
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 文章分类，0:自己爬的文章，1:客户的链接
     */
    public Integer getArticleType() {
        return this.articleType;
    }

    /**
     * 文章分类，0:自己爬的文章，1:客户的链接
     */
    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    /**
     * 若该文章的类型是0，则此字段是后期点击跳转的广告链接，若该文章的类型是1，则就是文章链接
     */
    public String getArticleUrl() {
        return this.articleUrl;
    }

    /**
     * 若该文章的类型是0，则此字段是后期点击跳转的广告链接，若该文章的类型是1，则就是文章链接
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    /**
     * 是否直接跳转只广告页面，1：直接跳转，0：不跳转
     */
    public Integer getIsDirectlyGoAd() {
        return this.isDirectlyGoAd;
    }

    /**
     * 是否直接跳转只广告页面，1：直接跳转，0：不跳转
     */
    public void setIsDirectlyGoAd(Integer isDirectlyGoAd) {
        this.isDirectlyGoAd = isDirectlyGoAd;
    }

    /**
     * 文章标题
     */
    public String getArticleTitle() {
        return this.articleTitle;
    }

    /**
     * 文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * 文章内容
     */
    public String getArticleContent() {
        return this.articleContent;
    }

    /**
     * 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    /**
     * 文章封面图
     */
    public String getArticleIcon() {
        return this.articleIcon;
    }

    /**
     * 文章封面图
     */
    public void setArticleIcon(String articleIcon) {
        this.articleIcon = articleIcon;
    }

    /**
     * 文章显示时间,如:2016-08-18
     */
    public String getArticleDisplayTime() {
        return this.articleDisplayTime;
    }

    /**
     * 文章显示时间,如:2016-08-18
     */
    public void setArticleDisplayTime(String articleDisplayTime) {
        this.articleDisplayTime = articleDisplayTime;
    }

    /**
     * 文章作者信息
     */
    public String getArticleAuthor() {
        return this.articleAuthor;
    }

    /**
     * 文章作者信息
     */
    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    /**
     * app上阅读奖励，每个用户只有一次有效阅读
     */
    public BigDecimal getAppReadMoney() {
        return this.appReadMoney;
    }

    /**
     * app上阅读奖励，每个用户只有一次有效阅读
     */
    public void setAppReadMoney(BigDecimal appReadMoney) {
        this.appReadMoney = appReadMoney;
    }

    /**
     * 单个有效好友点击获得的奖励
     */
    public BigDecimal getWxClickMoney() {
        return this.wxClickMoney;
    }

    /**
     * 单个有效好友点击获得的奖励
     */
    public void setWxClickMoney(BigDecimal wxClickMoney) {
        this.wxClickMoney = wxClickMoney;
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
     * 任务开始时间,如:2016-08-18 12:53:30
     */
    public String getStartMissionTime() {
        return this.startMissionTime;
    }

    /**
     * 任务开始时间,如:2016-08-18 12:53:30
     */
    public void setStartMissionTime(String startMissionTime) {
        this.startMissionTime = startMissionTime;
    }

    /**
     * 任务结束时间,如:2016-08-18 12:53:30
     */
    public String getEndMissionTime() {
        return this.endMissionTime;
    }

    /**
     * 任务结束时间,如:2016-08-18 12:53:30
     */
    public void setEndMissionTime(String endMissionTime) {
        this.endMissionTime = endMissionTime;
    }

    /**
     * 任务创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 任务创建时间,如:2016-08-18 12:53:30
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

    /**
     * 该文章获得是否结束，0:已结束，1:进行中
     */
    public Integer getIsEnd() {
        return this.isEnd;
    }

    /**
     * 该文章获得是否结束，0:已结束，1:进行中
     */
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * 该文章权重，排序时越大越靠前
     */
    public Integer getArticleWeight() {
        return this.articleWeight;
    }

    /**
     * 该文章权重，排序时越大越靠前
     */
    public void setArticleWeight(Integer articleWeight) {
        this.articleWeight = articleWeight;
    }

}