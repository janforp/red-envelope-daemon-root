package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReRedBannerDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReRedBanner;

import com.hongbao.api.model.dto.ReBannerInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
@Repository
public class ReRedBannerDAOImpl extends BaseSqlSessionDaoSupport
        implements ReRedBannerDAO {
    public int deleteByPrimaryKey(Long bannerId) {
        ReRedBanner _key = new ReRedBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("re_red_banner.deleteByPrimaryKey", _key);
    }

    public void insert(ReRedBanner record) {
        getSqlSession().insert("re_red_banner.insert", record);
    }

    public void insertSelective(ReRedBanner record) {
        getSqlSession().insert("re_red_banner.insertSelective", record);
    }

    public void insertBatch(List<ReRedBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_red_banner.insertBatch", records);
    }

    public ReRedBanner selectByPrimaryKey(Long bannerId) {
        ReRedBanner _key = new ReRedBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("re_red_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRedBanner record) {
        return getSqlSession().update("re_red_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRedBanner record) {
        return getSqlSession().update("re_red_banner.updateByPrimaryKey", record);
    }

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
    public List<ReBannerInfo> bannerList(int platform, int versioncode, String packageName, String channelName, boolean flag) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("platform", platform);
        map.put("versioncode", versioncode);
        map.put("packageName", packageName);
        map.put("channelName", channelName);
        if(flag) { // 正常
            return getSqlSession().selectList("re_red_banner.selectNormalBanner", map);
        }else { // 审核中
            return getSqlSession().selectList("re_red_banner.selectVerifyingBanner", map);
        }
    }
}