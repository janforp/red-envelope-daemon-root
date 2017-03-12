package com.hongbao.api.model.dto;

import java.math.BigDecimal;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
public class ReArticleInfo implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long articleId;
    // 文章标题
    private String articleTitle;
    // 文章封面图
    private String articleIcon;
    // app上阅读奖励，每个用户只有一次有效阅读
    private BigDecimal appReadMoney;
    // 单个有效好友点击获得的奖励
    private BigDecimal wxClickMoney;
    // 剩余点击次数
    private Integer leftClickTimes;
    // 任务开始时间,如:2016-08-18 12:53:30
    private String startMissionTime;
    // 任务结束时间,如:2016-08-18 12:53:30
    private String endMissionTime;
    // 该文章获得是否结束，0:已结束，1:进行中
    private Integer isEnd;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleIcon() {
        return articleIcon;
    }

    public void setArticleIcon(String articleIcon) {
        this.articleIcon = articleIcon;
    }

    public BigDecimal getAppReadMoney() {
        return appReadMoney;
    }

    public void setAppReadMoney(BigDecimal appReadMoney) {
        this.appReadMoney = appReadMoney;
    }

    public BigDecimal getWxClickMoney() {
        return wxClickMoney;
    }

    public void setWxClickMoney(BigDecimal wxClickMoney) {
        this.wxClickMoney = wxClickMoney;
    }

    public Integer getLeftClickTimes() {
        return leftClickTimes;
    }

    public void setLeftClickTimes(Integer leftClickTimes) {
        this.leftClickTimes = leftClickTimes;
    }

    public String getStartMissionTime() {
        return startMissionTime;
    }

    public void setStartMissionTime(String startMissionTime) {
        this.startMissionTime = startMissionTime;
    }

    public String getEndMissionTime() {
        return endMissionTime;
    }

    public void setEndMissionTime(String endMissionTime) {
        this.endMissionTime = endMissionTime;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

}