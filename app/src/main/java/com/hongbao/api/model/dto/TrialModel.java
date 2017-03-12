package com.hongbao.api.model.dto;

import com.hongbao.api.model.vo.TrialVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/12/3.
 */
public class TrialModel implements Serializable {

    // 标题
    private String trialTitle;
    // 类型 0-正在进行中 1-即将开始 2-已完成
    private int trialType;
    // 数据列表
    private List<TrialVo> trialData;

    public String getTrialTitle() {
        return trialTitle;
    }

    public void setTrialTitle(String trialTitle) {
        this.trialTitle = trialTitle;
    }

    public int getTrialType() {
        return trialType;
    }

    public void setTrialType(int trialType) {
        this.trialType = trialType;
    }

    public List<TrialVo> getTrialData() {
        return trialData;
    }

    public void setTrialData(List<TrialVo> trialData) {
        this.trialData = trialData;
    }
}
