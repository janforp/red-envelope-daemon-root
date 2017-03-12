package com.hongbao.api.model.vo;

/**
 * Created by Summer on 2016/12/7.
 */
public class ArticleVo implements java.io.Serializable {

    // id
    private Long articleId;
    // 文章标题
    private String articleTitle;
    // 文章封面图
    private String articleIcon;
    // 文章链接
    private String articleUrl;
    // 阅读奖励
    private String readMoney;
    // 分享奖励
    private String shareMoney;
    // 状态 0-已结束 1-进行中
    private int articleStatus;
    // 累计收益
    private String totalMoney;

    public int getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(int articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

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

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getReadMoney() {
        return readMoney;
    }

    public void setReadMoney(String readMoney) {
        this.readMoney = readMoney;
    }

    public String getShareMoney() {
        return shareMoney;
    }

    public void setShareMoney(String shareMoney) {
        this.shareMoney = shareMoney;
    }
}