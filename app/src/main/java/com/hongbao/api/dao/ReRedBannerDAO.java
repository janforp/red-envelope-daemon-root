package com.hongbao.api.dao;

import com.hongbao.api.model.ReRedBanner;
import com.hongbao.api.model.dto.ReBannerInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
public interface ReRedBannerDAO {
    int deleteByPrimaryKey(Long bannerId);

    void insert(ReRedBanner record);

    void insertSelective(ReRedBanner record);

    void insertBatch(List<ReRedBanner> records);

    ReRedBanner selectByPrimaryKey(Long bannerId);

    int updateByPrimaryKeySelective(ReRedBanner record);

    int updateByPrimaryKey(ReRedBanner record);

    /**
     * 红包池banner
     *
     * @param platform
     * @param versioncode
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    List<ReBannerInfo> bannerList(int platform, int versioncode, String packageName, String channelName, boolean flag);

}