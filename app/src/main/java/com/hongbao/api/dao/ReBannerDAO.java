package com.hongbao.api.dao;

import com.hongbao.api.model.ReBanner;
import com.hongbao.api.model.dto.BannerInfo;
import com.hongbao.api.model.dto.ReBannerInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-12
 */
public interface ReBannerDAO {
    int deleteByPrimaryKey(Integer bannerId);

    void insert(ReBanner record);

    void insertSelective(ReBanner record);

    void insertBatch(List<ReBanner> records);

    ReBanner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(ReBanner record);

    int updateByPrimaryKey(ReBanner record);

    /**
     * 查询banner列表
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    List<ReBannerInfo> selectBannerByPlatform(int platform, String version, String packageName, String channelName, boolean flag);


    /**
     * 查询开启的banner列表
     *
     * @return
     */
    List<BannerInfo> selectAllOpen();


}