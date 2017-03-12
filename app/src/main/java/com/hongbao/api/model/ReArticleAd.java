package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-06
 */
public class ReArticleAd implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long adId;
    // 分享文章任务的id
    private Long articleId;
    // 广告图
    private String adIcon;
    // 广告跳转链接
    private String adUrl;
    // 广告位置,0上面:1:中间2:底部,3:左边悬浮，4：右边悬浮，将来可能汇扩展
    private Integer adOrder;
    // 该广告是否显示，0:不显示，1:显示
    private Integer isDisplay;
    // 任务创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 修改时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReArticleAd() {
    }

    /**
     * full constructor
     */
    public ReArticleAd(Long articleId, String adIcon, String adUrl, Integer adOrder, Integer isDisplay, String createTime, String updateTime) {
        this.articleId = articleId;
        this.adIcon = adIcon;
        this.adUrl = adUrl;
        this.adOrder = adOrder;
        this.isDisplay = isDisplay;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getAdId() {
        return this.adId;
    }

    /**
     * id，自增长
     */
    public void setAdId(Long adId) {
        this.adId = adId;
    }

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
     * 广告图
     */
    public String getAdIcon() {
        return this.adIcon;
    }

    /**
     * 广告图
     */
    public void setAdIcon(String adIcon) {
        this.adIcon = adIcon;
    }

    /**
     * 广告跳转链接
     */
    public String getAdUrl() {
        return this.adUrl;
    }

    /**
     * 广告跳转链接
     */
    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    /**
     * 广告位置,0上面:1:中间2:底部,3:左边悬浮，4：右边悬浮，将来可能汇扩展
     */
    public Integer getAdOrder() {
        return this.adOrder;
    }

    /**
     * 广告位置,0上面:1:中间2:底部,3:左边悬浮，4：右边悬浮，将来可能汇扩展
     */
    public void setAdOrder(Integer adOrder) {
        this.adOrder = adOrder;
    }

    /**
     * 该广告是否显示，0:不显示，1:显示
     */
    public Integer getIsDisplay() {
        return this.isDisplay;
    }

    /**
     * 该广告是否显示，0:不显示，1:显示
     */
    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
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

}