package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/12/12.
 */
public class MissionNavigationVo implements Serializable {

    // 标题
    private String title;
    // 数据
    private List<NavigationVo> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NavigationVo> getData() {
        return data;
    }

    public void setData(List<NavigationVo> data) {
        this.data = data;
    }
}
