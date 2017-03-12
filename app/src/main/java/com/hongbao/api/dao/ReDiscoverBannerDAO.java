package com.hongbao.api.dao;

import com.hongbao.api.model.ReDiscoverBanner;
import com.hongbao.api.model.dto.BannerInfo;
import com.hongbao.api.model.dto.ReBannerInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-09
 */
public interface ReDiscoverBannerDAO {
    int deleteByPrimaryKey(Integer bannerId);

    void insert(ReDiscoverBanner record);

    void insertSelective(ReDiscoverBanner record);

    void insertBatch(List<ReDiscoverBanner> records);

    ReDiscoverBanner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(ReDiscoverBanner record);

    int updateByPrimaryKey(ReDiscoverBanner record);

    /**
     * 查询发现banner
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    List<ReBannerInfo> selectDiscoverBannerByPlatform(int platform, String version, String packageName, String channelName, boolean flag);

    /**
     * 查询开启的banner列表
     * @return
     */
    List<BannerInfo> selectAllOpen();

}