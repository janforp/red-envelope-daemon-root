package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-06
 */
public class ReMissionRequire implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long requireId;
    // 名字
    private String requireName;
    // 值
    private String requireValue;

    // Constructors

    /**
     * default constructor
     */
    public ReMissionRequire() {
    }

    /**
     * full constructor
     */
    public ReMissionRequire(String requireName, String requireValue) {
        this.requireName = requireName;
        this.requireValue = requireValue;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getRequireId() {
        return this.requireId;
    }

    /**
     * id，自增长
     */
    public void setRequireId(Long requireId) {
        this.requireId = requireId;
    }

    /**
     * 名字
     */
    public String getRequireName() {
        return this.requireName;
    }

    /**
     * 名字
     */
    public void setRequireName(String requireName) {
        this.requireName = requireName;
    }

    /**
     * 值
     */
    public String getRequireValue() {
        return this.requireValue;
    }

    /**
     * 值
     */
    public void setRequireValue(String requireValue) {
        this.requireValue = requireValue;
    }

}