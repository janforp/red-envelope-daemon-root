package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.util.List;


/**
 * 我发布的任务详情时
 * 页面下显示的待我审核的列表
 */
public class UserToVerifyVo implements Serializable {

    //详情id
    private Long    id;
    //提交备注
    private String  missionText;
    //图片
    private List<String> imgList;
    //提交头像
    private String      portrait;
    //提交人昵称
    private String      nickname;
    //提交时间
    private String      submitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
