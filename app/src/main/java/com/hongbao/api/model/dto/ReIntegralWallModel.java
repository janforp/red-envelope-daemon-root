package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/10/26.
 */
public class ReIntegralWallModel implements Serializable {

    // 序号
    private int orderId;
    // 标题
    private String title;
    // 描述
    private String desc;
    // 数据列表
    private List<ReIntegralWallInfo3> list;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ReIntegralWallInfo3> getList() {
        return list;
    }

    public void setList(List<ReIntegralWallInfo3> list) {
        this.list = list;
    }
}
