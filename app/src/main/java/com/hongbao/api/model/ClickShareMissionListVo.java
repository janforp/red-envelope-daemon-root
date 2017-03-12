package com.hongbao.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/11/15.
 * app分享点击任务
 * app上列表数据
 */
public class ClickShareMissionListVo implements Serializable{
    // 分享任务ID
    private Long missionId;
    // 图片
    private String icon;
    // 标题
    private String title;
    // 分享链接被点击一次可获得0.50元
    private String money;
    // 进入详情的链接
    private String url;
    // 是否结束 0:结束,1:进行中
    private int isEnd;
    // 剩余次数
    private int leftTimes;
    // 总奖励
    private BigDecimal totalMoney;
    // 参与人数
    private int partNum;

    public int getLeftTimes() {
        return leftTimes;
    }

    public void setLeftTimes(int leftTimes) {
        this.leftTimes = leftTimes;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getPartNum() {
        return partNum;
    }

    public void setPartNum(int partNum) {
        this.partNum = partNum;
    }

    public int getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(int isEnd) {
        this.isEnd = isEnd;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
