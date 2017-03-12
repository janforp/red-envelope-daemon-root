package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/24.
 */
public class WithdrawRecordInfo implements Serializable {

    // ID
    private Long withdrawId;
    // 明细
    private String detail;
    // 状态 0:审核中, 1:已完成, 2:未通过
    private int status;
    // 内容
    private String content;
    // 时间
    private String time;

    public Long getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
