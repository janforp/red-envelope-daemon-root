package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-07
 */
public class ReArticleRead implements java.io.Serializable {

    // Fields

    // 分享文章任务的id
    private Long articleId;
    // 用户id
    private Integer userId;
    // 阅读时间,如:2016-08-18 12:53:30
    private String readTime;

    // Constructors

    /**
     * default constructor
     */
    public ReArticleRead() {
    }

    /**
     * full constructor
     */
    public ReArticleRead(Long articleId, Integer userId, String readTime) {
        this.articleId = articleId;
        this.userId = userId;
        this.readTime = readTime;
    }

    // Property accessors

    /**
     * 分享文章任务的id
     */
    public Long getArticleId() {
        return this.articleId;
    }

    /**
     * 分享文章任务的id
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 阅读时间,如:2016-08-18 12:53:30
     */
    public String getReadTime() {
        return this.readTime;
    }

    /**
     * 阅读时间,如:2016-08-18 12:53:30
     */
    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

}