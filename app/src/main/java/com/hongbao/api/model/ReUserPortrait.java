package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-19
 */
public class ReUserPortrait implements java.io.Serializable {

    // Fields

    // id
    private Integer id;
    // 头像链接
    private String userPortrait;

    // Constructors

    /**
     * default constructor
     */
    public ReUserPortrait() {
    }

    /**
     * full constructor
     */
    public ReUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 头像链接
     */
    public String getUserPortrait() {
        return this.userPortrait;
    }

    /**
     * 头像链接
     */
    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

}