package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 16/9/18.
 */
public class ReIndexSortInfo implements Serializable {

    // 名称
    private String sortName;
    // 数据列表
    private List<ReCodeRedInfo> list;

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

}
