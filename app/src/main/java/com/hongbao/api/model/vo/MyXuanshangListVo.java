package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Janita on 2016/12/27.
 */
public class MyXuanshangListVo implements Serializable {

    // id，自增长,每个悬赏任务有很多人完成，该id就是详情id
    private Long id;
    // 提交悬赏任务用户id
    private Integer userId;
    // 提交备注
    private String missionText;
    // 提交的图片用分号隔开
    private String missionImg;
    // 提交的图片用分号隔开
    private List<String> imgList;
    // 状态; 0:已提交,待审核,1:已通过,2:未通过,3:来晚了
    private Integer detailStatus;
    // 状态; 0:已提交,待审核,1:已通过,2:未通过,3:来晚了
    private String  statusText;
    //头像
    private String  portrait;
    //昵称
    private String  nickname;
    // 未通过原因
    private String reason;
    //提交时间
    private String  submitTime;

    public String getMissionImg() {
        return missionImg;
    }

    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMissionText() {
        return missionText;
    }

    public void setMissionText(String missionText) {
        this.missionText = missionText;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
