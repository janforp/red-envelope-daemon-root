package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReWelfareDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReWelfare;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-12
 */
@Repository
public class ReWelfareDAOImpl extends BaseSqlSessionDaoSupport
        implements ReWelfareDAO {
    public int deleteByPrimaryKey(Long welfareId) {
        ReWelfare _key = new ReWelfare();
        _key.setWelfareId(welfareId);
        return getSqlSession().delete("re_welfare.deleteByPrimaryKey", _key);
    }

    public void insert(ReWelfare record) {
        getSqlSession().insert("re_welfare.insert", record);
    }

    public void insertSelective(ReWelfare record) {
        getSqlSession().insert("re_welfare.insertSelective", record);
    }

    public void insertBatch(List<ReWelfare> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_welfare.insertBatch", records);
    }

    public ReWelfare selectByPrimaryKey(Long welfareId) {
        ReWelfare _key = new ReWelfare();
        _key.setWelfareId(welfareId);
        return getSqlSession().selectOne("re_welfare.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWelfare record) {
        return getSqlSession().update("re_welfare.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWelfare record) {
        return getSqlSession().update("re_welfare.updateByPrimaryKey", record);
    }

    /**
     * 精选列表
     * @param platform
     * @return
     */
    public List<ReWelfare> selectSelectionListByPlatform(int platform) {
        return getSqlSession().selectList("re_welfare.selectSelectionListByPlatform", platform);
    }

    /**
     * 福利
     *
     * @param platform
     * @param welfareId
     * @param welfareType
     * @return
     */
    public List<ReWelfare> selectListByPlatform(int platform, Long welfareId, Integer welfareType) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("platform", platform);
        map.put("welfareId", welfareId);
        map.put("welfareType", welfareType);
        return getSqlSession().selectList("re_welfare.selectListByPlatform", map);
    }

    /**
     * 从mysql中获取福利id列表
     * @param platform      平台
     * @param welfareType   类型
     * @return
     */
    @Override
    public List<Long> selectWelfareIdListOrderByUpdateTimeDesc(int platform, Integer welfareType) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("platform", platform);
        map.put("welfareType", welfareType);
        return getSqlSession().selectList("re_welfare.selectWelfareIdListOrderByUpdateTimeDesc", map);
    }

}