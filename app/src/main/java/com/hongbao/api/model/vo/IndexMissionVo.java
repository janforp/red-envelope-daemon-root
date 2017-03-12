package com.hongbao.api.model.vo;

import com.hongbao.api.model.dto.Index5MissionInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2017/1/5.
 */
public class IndexMissionVo implements Serializable {

    // 标题
    private String title;
    // 数据
    private List<Index5MissionInfo> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Index5MissionInfo> getData() {
        return data;
    }

    public void setData(List<Index5MissionInfo> data) {
        this.data = data;
    }
}
