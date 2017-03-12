package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-11
 */
public class ReVerifyCode implements java.io.Serializable {

    // Fields

    private Long id;
    // 验证码值
    private String code;
    // 手机号
    private String cellphone;
    // 验证码生成时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long generateTime;
    // code的状态 0：未验证，1：验证通过，2：验证失败；（1小时内有效）
    private Integer codeVerifyStatus;
    // code验证时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long codeVerifyTime;

    // Constructors

    /**
     * default constructor
     */
    public ReVerifyCode() {
    }

    /**
     * full constructor
     */
    public ReVerifyCode(String code, String cellphone, Long generateTime, Integer codeVerifyStatus, Long codeVerifyTime) {
        this.code = code;
        this.cellphone = cellphone;
        this.generateTime = generateTime;
        this.codeVerifyStatus = codeVerifyStatus;
        this.codeVerifyTime = codeVerifyTime;
    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 验证码值
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 验证码值
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 手机号
     */
    public String getCellphone() {
        return this.cellphone;
    }

    /**
     * 手机号
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * 验证码生成时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getGenerateTime() {
        return this.generateTime;
    }

    /**
     * 验证码生成时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setGenerateTime(Long generateTime) {
        this.generateTime = generateTime;
    }

    /**
     * code的状态 0：未验证，1：验证通过，2：验证失败；（1小时内有效）
     */
    public Integer getCodeVerifyStatus() {
        return this.codeVerifyStatus;
    }

    /**
     * code的状态 0：未验证，1：验证通过，2：验证失败；（1小时内有效）
     */
    public void setCodeVerifyStatus(Integer codeVerifyStatus) {
        this.codeVerifyStatus = codeVerifyStatus;
    }

    /**
     * code验证时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getCodeVerifyTime() {
        return this.codeVerifyTime;
    }

    /**
     * code验证时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setCodeVerifyTime(Long codeVerifyTime) {
        this.codeVerifyTime = codeVerifyTime;
    }

}