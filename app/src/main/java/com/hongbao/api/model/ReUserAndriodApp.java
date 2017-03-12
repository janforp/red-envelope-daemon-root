package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-14
 */
public class ReUserAndriodApp implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 用户id
    private Integer userId;
    // app包名
    private String appPackage;
    // app名称
    private String appName;
    // 记录时间,如:2016-10-14 12:53:30
    private String recordTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserAndriodApp() {
    }

    /**
     * full constructor
     */
    public ReUserAndriodApp(Integer userId, String appPackage, String appName, String recordTime) {
        this.userId = userId;
        this.appPackage = appPackage;
        this.appName = appName;
        this.recordTime = recordTime;
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
     * 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * app包名
     */
    public String getAppPackage() {
        return this.appPackage;
    }

    /**
     * app包名
     */
    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    /**
     * app名称
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * app名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 记录时间,如:2016-10-14 12:53:30
     */
    public String getRecordTime() {
        return this.recordTime;
    }

    /**
     * 记录时间,如:2016-10-14 12:53:30
     */
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

}