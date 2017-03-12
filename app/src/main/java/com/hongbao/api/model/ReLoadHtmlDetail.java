package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-22
 */
public class ReLoadHtmlDetail implements java.io.Serializable {

    // Fields

    // id
    private Long detailId;
    // 链接id
    private Long htmlId;
    // 设备Id
    private String deviceId;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public ReLoadHtmlDetail() {
    }

    /**
     * full constructor
     */
    public ReLoadHtmlDetail(Long htmlId, String deviceId, String createTime) {
        this.htmlId = htmlId;
        this.deviceId = deviceId;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getDetailId() {
        return this.detailId;
    }

    /**
     * id
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    /**
     * 链接id
     */
    public Long getHtmlId() {
        return this.htmlId;
    }

    /**
     * 链接id
     */
    public void setHtmlId(Long htmlId) {
        this.htmlId = htmlId;
    }

    /**
     * 设备Id
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 设备Id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

}