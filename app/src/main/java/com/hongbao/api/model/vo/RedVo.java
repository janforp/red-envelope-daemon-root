package com.hongbao.api.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 2016/12/23.
 * 红包池页面上的一个一个的红包对象
 */
public class RedVo implements Serializable{

    // id
    private Long redId;
    // 用户头像
    private String portrait;
    // 用户昵称
    private String nickname;
    // 发布时间
    private String createTime;
    // 红包内容
    private String redContent;
    // 红包状态 (0-可抢 1-抢光 2-已抢过 3-已过期)
    private int redStatus;
    // 状态内容
    private String statusContent;
    // 是否自己发布 (0-不是, 1-是)
    private int isSelf;

    public Long getRedId() {
        return redId;
    }

    public void setRedId(Long redId) {
        this.redId = redId;
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

    public String getRedContent() {
        return redContent;
    }

    public void setRedContent(String redContent) {
        this.redContent = redContent;
    }

    public int getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(int redStatus) {
        this.redStatus = redStatus;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }

    public int getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(int isSelf) {
        this.isSelf = isSelf;
    }
}
