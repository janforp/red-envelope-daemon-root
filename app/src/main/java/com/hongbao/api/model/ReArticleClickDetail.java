package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
public class ReArticleClickDetail implements java.io.Serializable {

    // Fields

    // 文章id
    private Long articleId;
    // 转发用户id
    private Integer userId;
    // 读者的微信openid
    private String openId;
    // 点击月的的时间,如:2016-08-18 12:53:30
    private String clickTime;

    // Constructors

    /**
     * default constructor
     */
    public ReArticleClickDetail() {
    }

    /**
     * full constructor
     */
    public ReArticleClickDetail(Long articleId, Integer userId, String openId, String clickTime) {
        this.articleId = articleId;
        this.userId = userId;
        this.openId = openId;
        this.clickTime = clickTime;
    }

    // Property accessors

    /**
     * 文章id
     */
    public Long getArticleId() {
        return this.articleId;
    }

    /**
     * 文章id
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 转发用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 转发用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 读者的微信openid
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 读者的微信openid
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 点击月的的时间,如:2016-08-18 12:53:30
     */
    public String getClickTime() {
        return this.clickTime;
    }

    /**
     * 点击月的的时间,如:2016-08-18 12:53:30
     */
    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

}