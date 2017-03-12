package com.hongbao.api.model.vo;

/**
 * Created by Jan on 16/6/29.
 */
public class JuheMsgBack {


    // 返回码
    private int error_code;
    // 返回说明
    private String reason;
    // 返回结果集
    private JuheMsgBackResult result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JuheMsgBackResult getResult() {
        return result;
    }

    public void setResult(JuheMsgBackResult result) {
        this.result = result;
    }
}
