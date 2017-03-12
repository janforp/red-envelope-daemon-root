package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Janita on 2016/12/27.
 */
public class XuanshangVo implements Serializable {
    //悬赏任务id
    private Long xuanshangId;
    //悬赏任务做任务的detail的id
    private Long id;
    // 用户头像
    private String portrait;
    // 用户昵称
    private String nickname;
    // 发布时间
    private String createTime;
    // 描述
    private String missionDesc;
    //总金额
    private BigDecimal totalMoney;
    // 如总悬赏20元....
    private String missionContent;
    //总数量
    private Integer totalNum;
    //提交任务的数
    private Integer submitNum;
    //审核通过数量
    private Integer passNum;
    // 状态内容
    private String statusContent;
    //我的任务详情页面不同状态的提示背景颜色不同
    // 状态; 0：待审核,1:已通过,2:未通过,3:来晚了，4:来晚了
    private Integer detailStatus;
    //提交的任务的状态：待审核／未通过／已通过／来晚了
    private String  statusText;
    //是否是自己发布的悬赏任务，1：自己发布的，0：别人发布的，没有登录的情况下都不是自己发布的（0）
    private Integer isSelf;
    //发布悬赏任务人的id
    private Integer userId;


    public Long getXuanshangId() {
        return xuanshangId;
    }

    public void setXuanshangId(Long xuanshangId) {
        this.xuanshangId = xuanshangId;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getMissionContent() {
        return missionContent;
    }

    public void setMissionContent(String missionContent) {
        this.missionContent = missionContent;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Integer getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Integer isSelf) {
        this.isSelf = isSelf;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
