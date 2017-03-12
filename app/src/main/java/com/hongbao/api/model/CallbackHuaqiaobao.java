package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-31
 */
public class CallbackHuaqiaobao implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 用户ID
    private Integer userId;
    // 手机
    private String mobile;
    // 状态; 0-app红包,1-微信红包
    private Integer callType;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public CallbackHuaqiaobao() {
    }

    /**
     * full constructor
     */
    public CallbackHuaqiaobao(Integer userId, String mobile, Integer callType, String createTime) {
        this.userId = userId;
        this.mobile = mobile;
        this.callType = callType;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 手机
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 状态; 0-app红包,1-微信红包
     */
    public Integer getCallType() {
        return this.callType;
    }

    /**
     * 状态; 0-app红包,1-微信红包
     */
    public void setCallType(Integer callType) {
        this.callType = callType;
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