package com.hongbao.api.model.vo;

import java.io.Serializable;

/**
 * Created by wuqiang on 15-9-11.
 *
 * @author wuqiang
 */
public class AliyunOSSFederationTokenVo implements Serializable {

    // 访问密钥标识
    private String tempAk;
    // 访问密钥
    private String tempSk;
    // 安全令牌
    private String securityToken;
    // 失效时间
    private String expiration;

    public String getTempAk() {
        return tempAk;
    }

    public void setTempAk(String tempAk) {
        this.tempAk = tempAk;
    }

    public String getTempSk() {
        return tempSk;
    }

    public void setTempSk(String tempSk) {
        this.tempSk = tempSk;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
