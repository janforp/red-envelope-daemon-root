package com.hongbao.api.dao;

import com.hongbao.api.model.ReStartAd;
import com.hongbao.api.model.dto.ReStartAdInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-15
 */
public interface ReStartAdDAO {
    int deleteByPrimaryKey(Integer adId);

    void insert(ReStartAd record);

    void insertSelective(ReStartAd record);

    void insertBatch(List<ReStartAd> records);

    ReStartAd selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(ReStartAd record);

    int updateByPrimaryKey(ReStartAd record);

    /**
     * 查询ios启动页广告
     * @return
     */
    ReStartAdInfo selectIosAdRandom();

    /**
     * 查询andriod启动页广告
     * @return
     */
    ReStartAdInfo selectAndriodAdRandom();

}