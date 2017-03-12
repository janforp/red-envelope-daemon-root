package com.hongbao.api.dao;

import com.hongbao.api.model.ReCodeRed;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-23
 */
public interface ReCodeRedDAO {
    int deleteByPrimaryKey(Integer codeId);

    void insert(ReCodeRed record);

    void insertSelective(ReCodeRed record);

    void insertBatch(List<ReCodeRed> records);

    ReCodeRed selectByPrimaryKey(Integer codeId);

    int updateByPrimaryKeySelective(ReCodeRed record);

    int updateByPrimaryKey(ReCodeRed record);

    /**
     * 获取验证码红包
     * @param platform
     * @return
     */
    List<ReCodeRed> selectCodeRedByPlatform(int platform);

    /**
     * 获取首页验证码红包
     * @param platform
     * @return
     */
    List<ReCodeRed> selectIndexCodeRedByPlatform(int platform);

}