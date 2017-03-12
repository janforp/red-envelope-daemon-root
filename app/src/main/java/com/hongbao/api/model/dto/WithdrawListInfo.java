package com.hongbao.api.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 16/9/21.
 */
public class WithdrawListInfo implements Serializable {

    // 名称
    private String listName;
    // 数据列表
    private List<ReWithdrawSortInfo> listData;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<ReWithdrawSortInfo> getListData() {
        return listData;
    }

    public void setListData(List<ReWithdrawSortInfo> listData) {
        this.listData = listData;
    }
    
}
