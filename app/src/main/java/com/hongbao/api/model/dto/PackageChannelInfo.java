package com.hongbao.api.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2017/1/3.
 */
public class PackageChannelInfo implements Serializable {

    // 版本号
    private Integer versionCode;
    // 状态,如 0-审核中, 1-已通过,2-未通过
    private Integer status;

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
