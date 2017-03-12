package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/11/15.
 */
public class TrialTaskModel implements Serializable {

    // 标题
    private String title;
    // 类型 0-无颜色 1-有颜色
    private int type;
    // 数据
    private List<TrialTaskInfo> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<TrialTaskInfo> getData() {
        return data;
    }

    public void setData(List<TrialTaskInfo> data) {
        this.data = data;
    }

}
