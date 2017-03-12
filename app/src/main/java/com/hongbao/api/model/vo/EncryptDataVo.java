package com.hongbao.api.model.vo;

import java.io.Serializable;

/**
 * Created by wuqiang on 15-8-7.
 *
 * @author wuqiang
 */
public class EncryptDataVo implements Serializable {
    // 注意：这个属性名一定要和RequestConsts.GLOBAL_ENCRYPT_POST_DATA_KEY一致
    private String data;

    public EncryptDataVo(String data) {
        if (data == null) {
            throw new NullPointerException("data");
        }
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
