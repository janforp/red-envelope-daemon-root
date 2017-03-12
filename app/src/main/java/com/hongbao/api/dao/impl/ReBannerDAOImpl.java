package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReBannerDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReBanner;

import com.hongbao.api.model.dto.BannerInfo;
import com.hongbao.api.model.dto.ReBannerInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-12
 */
@Repository
public class ReBannerDAOImpl extends BaseSqlSessionDaoSupport
        implements ReBannerDAO {
    public int deleteByPrimaryKey(Integer bannerId) {
        ReBanner _key = new ReBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("re_banner.deleteByPrimaryKey", _key);
    }

    public void insert(ReBanner record) {
        getSqlSession().insert("re_banner.insert", record);
    }

    public void insertSelective(ReBanner record) {
        getSqlSession().insert("re_banner.insertSelective", record);
    }

    public void insertBatch(List<ReBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_banner.insertBatch", records);
    }

    public ReBanner selectByPrimaryKey(Integer bannerId) {
        ReBanner _key = new ReBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("re_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReBanner record) {
        return getSqlSession().update("re_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReBanner record) {
        return getSqlSession().update("re_banner.updateByPrimaryKey", record);
    }

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
    public List<ReBannerInfo> selectBannerByPlatform(int platform, String version, String packageName, String channelName, boolean flag) {

        Map<String, Object> map = new HashMap<>(4);
        map.put("platform", platform);
        map.put("version", version);
        map.put("packageName", packageName);
        map.put("channelName", channelName);

        if(flag) { // 正常
            return getSqlSession().selectList("re_banner.selectNormalBanner", map);
        }else { // 审核中
            return getSqlSession().selectList("re_banner.selectVerifyingBanner", map);
        }

    }


    /**
     * 查询开启的banner列表
     *
     * @return
     */
    public List<BannerInfo> selectAllOpen() {
        return getSqlSession().selectList("re_banner.selectAllOpen");
    }


}