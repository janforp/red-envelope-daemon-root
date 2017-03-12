package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-15
 */
public class ReWelfareType implements java.io.Serializable {

    // Fields

    // id，自增长
    private Integer typeId;
    // 名称
    private String typeName;
    // 子类型,用逗号分隔开，如:爱奇艺,优酷
    private String subTypeName;
    // 图片
    private String typeImg;
    // 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
    private Integer typeStatus;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReWelfareType() {
    }

    /**
     * full constructor
     */
    public ReWelfareType(String typeName, String subTypeName, String typeImg, Integer typeStatus, String createTime, String updateTime) {
        this.typeName = typeName;
        this.subTypeName = subTypeName;
        this.typeImg = typeImg;
        this.typeStatus = typeStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Integer getTypeId() {
        return this.typeId;
    }

    /**
     * id，自增长
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 名称
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * 名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 子类型,用逗号分隔开，如:爱奇艺,优酷
     */
    public String getSubTypeName() {
        return this.subTypeName;
    }

    /**
     * 子类型,用逗号分隔开，如:爱奇艺,优酷
     */
    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    /**
     * 图片
     */
    public String getTypeImg() {
        return this.typeImg;
    }

    /**
     * 图片
     */
    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    /**
     * 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
     */
    public Integer getTypeStatus() {
        return this.typeStatus;
    }

    /**
     * 状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭
     */
    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
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
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}