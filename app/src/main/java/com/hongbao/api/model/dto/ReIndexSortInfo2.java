package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/10/9.
 */
public class ReIndexSortInfo2 implements Serializable {

    // 分类Id
    private Integer sortId;
    // 名称
    private String sortName;
    // 图标
    private String sortImg;
    // 数据列表
    private List<ReCodeRedInfo> list;

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public List<ReCodeRedInfo> getList() {
        return list;
    }

    public void setList(List<ReCodeRedInfo> list) {
        this.list = list;
    }

    public String getSortImg() {
        return sortImg;
    }

    public void setSortImg(String sortImg) {
        this.sortImg = sortImg;
    }
}
