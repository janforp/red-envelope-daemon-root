package com.hongbao.api.model.cache;

import java.io.Serializable;

/**
 * Created by wuqiang on 15-8-11.
 * <p/>
 * 用于缓存中保存用户的userKey/userSecret/userId这三个信息
 *
 * @author wuqiang
 */
public class UserKeySecret implements Serializable {

    private String userKey;
    private Integer userId;
    private String userSecret;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }
}
