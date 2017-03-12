package com.hongbao.api.dao;

import com.hongbao.api.model.ReXuanshangBanner;
import com.hongbao.api.model.dto.BannerInfo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-26
 */
public interface ReXuanshangBannerDAO {
    int deleteByPrimaryKey(Long bannerId);

    void insert(ReXuanshangBanner record);

    void insertSelective(ReXuanshangBanner record);

    void insertBatch(List<ReXuanshangBanner> records);

    ReXuanshangBanner selectByPrimaryKey(Long bannerId);

    int updateByPrimaryKeySelective(ReXuanshangBanner record);

    int updateByPrimaryKey(ReXuanshangBanner record);

    /**
     * 获取banner列表
     * @param platform
     * @param versioncode
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    List<ReXuanshangBanner> getBanner(int platform, int versioncode, String packageName, String channelName, boolean flag);

    /**
     * 查询开启的banner列表
     *
     * @return
     */
    List<BannerInfo> selectAllOpen();

}