package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/11/16.
 */
public class GreatInfo implements Serializable {

    // 图片
    private String img;
    // 标题
    private String title;
    // 标签
    private List<String> label;
    // 状态 0-(显示金额) 1-(进行中, 审核中) 2-(已完成, 未通过, 已结束)
    private int status;
    // 说明
    private String desc;
    // 链接
    private String url;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
