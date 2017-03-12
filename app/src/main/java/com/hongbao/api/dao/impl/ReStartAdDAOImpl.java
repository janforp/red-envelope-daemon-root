package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReStartAdDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReStartAd;

import com.hongbao.api.model.dto.ReStartAdInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-15
 */
@Repository
public class ReStartAdDAOImpl extends BaseSqlSessionDaoSupport
        implements ReStartAdDAO {
    public int deleteByPrimaryKey(Integer adId) {
        ReStartAd _key = new ReStartAd();
        _key.setAdId(adId);
        return getSqlSession().delete("re_start_ad.deleteByPrimaryKey", _key);
    }

    public void insert(ReStartAd record) {
        getSqlSession().insert("re_start_ad.insert", record);
    }

    public void insertSelective(ReStartAd record) {
        getSqlSession().insert("re_start_ad.insertSelective", record);
    }

    public void insertBatch(List<ReStartAd> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_start_ad.insertBatch", records);
    }

    public ReStartAd selectByPrimaryKey(Integer adId) {
        ReStartAd _key = new ReStartAd();
        _key.setAdId(adId);
        return getSqlSession().selectOne("re_start_ad.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReStartAd record) {
        return getSqlSession().update("re_start_ad.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReStartAd record) {
        return getSqlSession().update("re_start_ad.updateByPrimaryKey", record);
    }

    /**
     * 查询ios启动页广告
     * @return
     */
    public ReStartAdInfo selectIosAdRandom() {
        return getSqlSession().selectOne("re_start_ad.selectIosAdRandom");
    }

    /**
     * 查询andriod启动页广告
     * @return
     */
    public ReStartAdInfo selectAndriodAdRandom() {
        return getSqlSession().selectOne("re_start_ad.selectAndriodAdRandom");
    }

}