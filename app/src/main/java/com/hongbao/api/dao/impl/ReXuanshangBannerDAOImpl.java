package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReXuanshangBannerDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReXuanshangBanner;

import com.hongbao.api.model.dto.BannerInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-26
 */
@Repository
public class ReXuanshangBannerDAOImpl extends BaseSqlSessionDaoSupport
        implements ReXuanshangBannerDAO {
    public int deleteByPrimaryKey(Long bannerId) {
        ReXuanshangBanner _key = new ReXuanshangBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("re_xuanshang_banner.deleteByPrimaryKey", _key);
    }

    public void insert(ReXuanshangBanner record) {
        getSqlSession().insert("re_xuanshang_banner.insert", record);
    }

    public void insertSelective(ReXuanshangBanner record) {
        getSqlSession().insert("re_xuanshang_banner.insertSelective", record);
    }

    public void insertBatch(List<ReXuanshangBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_xuanshang_banner.insertBatch", records);
    }

    public ReXuanshangBanner selectByPrimaryKey(Long bannerId) {
        ReXuanshangBanner _key = new ReXuanshangBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("re_xuanshang_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReXuanshangBanner record) {
        return getSqlSession().update("re_xuanshang_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReXuanshangBanner record) {
        return getSqlSession().update("re_xuanshang_banner.updateByPrimaryKey", record);
    }


    /**
     * 获取banner列表
     * @param platform
     * @param versioncode
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    @Override
    public List<ReXuanshangBanner> getBanner(int platform, int versioncode, String packageName, String channelName, boolean flag) {

        Map<String, Object> map = new HashMap<>(4);
        map.put("platform", platform);
        map.put("versioncode", versioncode);
        map.put("packageName", packageName);
        map.put("channelName", channelName);
        if(flag) { // 正常
            return getSqlSession().selectList("re_xuanshang_banner.selectNormalBanner", map);
        }else { // 审核中
            return getSqlSession().selectList("re_xuanshang_banner.selectVerifyingBanner", map);
        }
    }


    /**
     * 查询开启的banner列表
     *
     * @return
     */
    public List<BannerInfo> selectAllOpen() {
        return getSqlSession().selectList("re_xuanshang_banner.selectAllOpen");
    }

}