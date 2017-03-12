package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReDiscoverBannerDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReDiscoverBanner;

import com.hongbao.api.model.dto.BannerInfo;
import com.hongbao.api.model.dto.ReBannerInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-10-09
 */
@Repository
public class ReDiscoverBannerDAOImpl extends BaseSqlSessionDaoSupport
        implements ReDiscoverBannerDAO {
    public int deleteByPrimaryKey(Integer bannerId) {
        ReDiscoverBanner _key = new ReDiscoverBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("re_discover_banner.deleteByPrimaryKey", _key);
    }

    public void insert(ReDiscoverBanner record) {
        getSqlSession().insert("re_discover_banner.insert", record);
    }

    public void insertSelective(ReDiscoverBanner record) {
        getSqlSession().insert("re_discover_banner.insertSelective", record);
    }

    public void insertBatch(List<ReDiscoverBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_discover_banner.insertBatch", records);
    }

    public ReDiscoverBanner selectByPrimaryKey(Integer bannerId) {
        ReDiscoverBanner _key = new ReDiscoverBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("re_discover_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDiscoverBanner record) {
        return getSqlSession().update("re_discover_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDiscoverBanner record) {
        return getSqlSession().update("re_discover_banner.updateByPrimaryKey", record);
    }

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
    public List<ReBannerInfo> selectDiscoverBannerByPlatform(int platform, String version, String packageName, String channelName, boolean flag) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("platform", platform);
        map.put("version", version);
        map.put("packageName", packageName);
        map.put("channelName", channelName);

        if(flag) { // 正常
            return getSqlSession().selectList("re_discover_banner.selectNormalDiscoverBanner", map);
        }else { // 审核中
            return getSqlSession().selectList("re_discover_banner.selectVerifyingDiscoverBanner", map);
        }
    }

    /**
     * 查询开启的banner列表
     * @return
     */
    public List<BannerInfo> selectAllOpen() {
        return getSqlSession().selectList("re_discover_banner.selectAllOpen");
    }


}