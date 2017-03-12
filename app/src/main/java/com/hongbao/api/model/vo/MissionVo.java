package com.hongbao.api.model.vo;

import com.hongbao.api.model.dto.MissionInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/12/10.
 * 精选
 */
public class MissionVo implements Serializable {

    // 标题
    private String title;
    // 数据
    private List<MissionInfo> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MissionInfo> getData() {
        return data;
    }

    public void setData(List<MissionInfo> data) {
        this.data = data;
    }

}
