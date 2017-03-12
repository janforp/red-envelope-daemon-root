package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-22
 */
public class ReLoadHtml implements java.io.Serializable {

    // Fields

    // id
    private Long htmlId;
    // 链接
    private String htmlUrl;
    // 状态, 0:关闭, 1:打开
    private Integer htmlStatus;
    // 总需次数
    private Integer totalNum;
    // 完成次数
    private Integer completeNum;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 修改时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 开始时间，如09:00:00
    private String startTime;
    // 结束时间，如19:00:00
    private String endTime;

    // Constructors

    /**
     * default constructor
     */
    public ReLoadHtml() {
    }

    /**
     * full constructor
     */
    public ReLoadHtml(String htmlUrl, Integer htmlStatus, Integer totalNum, Integer completeNum, String createTime, String updateTime, String startTime, String endTime) {
        this.htmlUrl = htmlUrl;
        this.htmlStatus = htmlStatus;
        this.totalNum = totalNum;
        this.completeNum = completeNum;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getHtmlId() {
        return this.htmlId;
    }

    /**
     * id
     */
    public void setHtmlId(Long htmlId) {
        this.htmlId = htmlId;
    }

    /**
     * 链接
     */
    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    /**
     * 链接
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * 状态, 0:关闭, 1:打开
     */
    public Integer getHtmlStatus() {
        return this.htmlStatus;
    }

    /**
     * 状态, 0:关闭, 1:打开
     */
    public void setHtmlStatus(Integer htmlStatus) {
        this.htmlStatus = htmlStatus;
    }

    /**
     * 总需次数
     */
    public Integer getTotalNum() {
        return this.totalNum;
    }

    /**
     * 总需次数
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 完成次数
     */
    public Integer getCompleteNum() {
        return this.completeNum;
    }

    /**
     * 完成次数
     */
    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
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

    /**
     * 开始时间，如09:00:00
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 开始时间，如09:00:00
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间，如19:00:00
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 结束时间，如19:00:00
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}