package com.hongbao.api.dao;

import com.hongbao.api.model.ReWelfareType;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-13
 */
public interface ReWelfareTypeDAO {
    int deleteByPrimaryKey(Integer typeId);

    void insert(ReWelfareType record);

    void insertSelective(ReWelfareType record);

    void insertBatch(List<ReWelfareType> records);

    ReWelfareType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(ReWelfareType record);

    int updateByPrimaryKey(ReWelfareType record);

    /**
     * 查询分类
     *
     * @param platform
     * @return
     */
    List<ReWelfareType> selectByPlatform(int platform);
    /**
     * 获取福利的所有类型的列表
     * @return
     */
    List<Integer> getWelfareTypeList();
}