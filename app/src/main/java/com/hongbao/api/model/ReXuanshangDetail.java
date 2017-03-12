package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-11
 */
public class ReXuanshangDetail implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long id;
    // 悬赏任务id
    private Long xuanshangId;
    // 提交悬赏任务用户id
    private Integer userId;
    // 提交备注
    private String missionText;
    // 提交的图片用分号隔开
    private String missionImg;
    // 状态; 0:已提交,待审核,1:已通过,2:未通过,3:任务已经完成,该记录自动过期
    private Integer detailStatus;
    // 未通过原因
    private String reason;
    // 逻辑删除，0:未删除，1:已删除
    private Integer isDelete;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReXuanshangDetail() {
    }

    /**
     * full constructor
     */
    public ReXuanshangDetail(Long xuanshangId, Integer userId, String missionText, String missionImg, Integer detailStatus, String reason, Integer isDelete, String createTime, String updateTime) {
        this.xuanshangId = xuanshangId;
        this.userId = userId;
        this.missionText = missionText;
        this.missionImg = missionImg;
        this.detailStatus = detailStatus;
        this.reason = reason;
        this.isDelete = isDelete;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id，自增长
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 悬赏任务id
     */
    public Long getXuanshangId() {
        return this.xuanshangId;
    }

    /**
     * 悬赏任务id
     */
    public void setXuanshangId(Long xuanshangId) {
        this.xuanshangId = xuanshangId;
    }

    /**
     * 提交悬赏任务用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 提交悬赏任务用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 提交备注
     */
    public String getMissionText() {
        return this.missionText;
    }

    /**
     * 提交备注
     */
    public void setMissionText(String missionText) {
        this.missionText = missionText;
    }

    /**
     * 提交的图片用分号隔开
     */
    public String getMissionImg() {
        return this.missionImg;
    }

    /**
     * 提交的图片用分号隔开
     */
    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    /**
     * 状态; 0:已提交,待审核,1:已通过,2:未通过,3:任务已经完成,该记录自动过期
     */
    public Integer getDetailStatus() {
        return this.detailStatus;
    }

    /**
     * 状态; 0:已提交,待审核,1:已通过,2:未通过,3:任务已经完成,该记录自动过期
     */
    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    /**
     * 未通过原因
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * 未通过原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 逻辑删除，0:未删除，1:已删除
     */
    public Integer getIsDelete() {
        return this.isDelete;
    }

    /**
     * 逻辑删除，0:未删除，1:已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 创建时间
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}