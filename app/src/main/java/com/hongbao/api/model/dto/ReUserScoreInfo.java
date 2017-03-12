package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/25.
 */
public class ReUserScoreInfo implements Serializable {

    // 今日
    private Integer todayScore;
    // 总共
    private Integer totalScore;

    public Integer getTodayScore() {
        return todayScore;
    }

    public void setTodayScore(Integer todayScore) {
        this.todayScore = todayScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
