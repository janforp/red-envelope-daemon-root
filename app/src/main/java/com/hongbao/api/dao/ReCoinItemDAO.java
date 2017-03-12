package com.hongbao.api.dao;

import com.hongbao.api.model.ReCoinItem;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-09
 */
public interface ReCoinItemDAO {
    int deleteByPrimaryKey(Long itemId);

    void insert(ReCoinItem record);

    void insertSelective(ReCoinItem record);

    void insertBatch(List<ReCoinItem> records);

    ReCoinItem selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(ReCoinItem record);

    int updateByPrimaryKey(ReCoinItem record);

    /**
     * 查询首页商品
     * @return
     */
    List<ReCoinItem> selectIndexPage();

}