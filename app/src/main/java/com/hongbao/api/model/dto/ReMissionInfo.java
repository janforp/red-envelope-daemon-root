package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 16/9/19.
 */
public class ReMissionInfo implements Serializable {

    // 任务id
    private Integer missionId;
    // 商家名字
    private String merchantName;
    // 任务图标
    private String missionImg;
    // 奖励,如:100元红包券,10元观影券,购物券等
    private String missionReward;
    // 任务状态,0:已结束,1:进行中
    private Integer missionStatus;
    // 商家描述
    private String merchantDetail;

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMissionImg() {
        return missionImg;
    }

    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    public String getMissionReward() {
        return missionReward;
    }

    public void setMissionReward(String missionReward) {
        this.missionReward = missionReward;
    }

    public Integer getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    public String getMerchantDetail() {
        return merchantDetail;
    }

    public void setMerchantDetail(String merchantDetail) {
        this.merchantDetail = merchantDetail;
    }
}
