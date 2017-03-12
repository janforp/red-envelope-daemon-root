package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-13
 */
public class ReWhiteList implements java.io.Serializable {

    // Fields

    // 手机号
    private String mobile;

    // Constructors

    /**
     * default constructor
     */
    public ReWhiteList() {
    }

    /**
     * full constructor
     */
    public ReWhiteList(String mobile) {
        this.mobile = mobile;
    }

    // Property accessors

    /**
     * 手机号
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}