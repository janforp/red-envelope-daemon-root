package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
public class ReParameterConfigure implements java.io.Serializable {

    // Fields

    // 配置项
    private String configureId;
    // 配置说明
    private String configureDesc;
    // 配置内容1
    private String configureOne;
    // 配置内容2
    private String configureTwo;
    // 配置内容3
    private String configureThree;
    // 配置内容4
    private String configureFour;
    // 配置内容5
    private String configureFive;

    // Constructors

    /**
     * default constructor
     */
    public ReParameterConfigure() {
    }

    /**
     * full constructor
     */
    public ReParameterConfigure(String configureId, String configureDesc, String configureOne, String configureTwo, String configureThree, String configureFour, String configureFive) {
        this.configureId = configureId;
        this.configureDesc = configureDesc;
        this.configureOne = configureOne;
        this.configureTwo = configureTwo;
        this.configureThree = configureThree;
        this.configureFour = configureFour;
        this.configureFive = configureFive;
    }

    // Property accessors

    /**
     * 配置项
     */
    public String getConfigureId() {
        return this.configureId;
    }

    /**
     * 配置项
     */
    public void setConfigureId(String configureId) {
        this.configureId = configureId;
    }

    /**
     * 配置说明
     */
    public String getConfigureDesc() {
        return this.configureDesc;
    }

    /**
     * 配置说明
     */
    public void setConfigureDesc(String configureDesc) {
        this.configureDesc = configureDesc;
    }

    /**
     * 配置内容1
     */
    public String getConfigureOne() {
        return this.configureOne;
    }

    /**
     * 配置内容1
     */
    public void setConfigureOne(String configureOne) {
        this.configureOne = configureOne;
    }

    /**
     * 配置内容2
     */
    public String getConfigureTwo() {
        return this.configureTwo;
    }

    /**
     * 配置内容2
     */
    public void setConfigureTwo(String configureTwo) {
        this.configureTwo = configureTwo;
    }

    /**
     * 配置内容3
     */
    public String getConfigureThree() {
        return this.configureThree;
    }

    /**
     * 配置内容3
     */
    public void setConfigureThree(String configureThree) {
        this.configureThree = configureThree;
    }

    /**
     * 配置内容4
     */
    public String getConfigureFour() {
        return this.configureFour;
    }

    /**
     * 配置内容4
     */
    public void setConfigureFour(String configureFour) {
        this.configureFour = configureFour;
    }

    /**
     * 配置内容5
     */
    public String getConfigureFive() {
        return this.configureFive;
    }

    /**
     * 配置内容5
     */
    public void setConfigureFive(String configureFive) {
        this.configureFive = configureFive;
    }

}