package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReIndexSortDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReIndexSort;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-18
 */
@Repository
public class ReIndexSortDAOImpl extends BaseSqlSessionDaoSupport
        implements ReIndexSortDAO {
    public int deleteByPrimaryKey(Integer sortId) {
        ReIndexSort _key = new ReIndexSort();
        _key.setSortId(sortId);
        return getSqlSession().delete("re_index_sort.deleteByPrimaryKey", _key);
    }

    public void insert(ReIndexSort record) {
        getSqlSession().insert("re_index_sort.insert", record);
    }

    public void insertSelective(ReIndexSort record) {
        getSqlSession().insert("re_index_sort.insertSelective", record);
    }

    public void insertBatch(List<ReIndexSort> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_index_sort.insertBatch", records);
    }

    public ReIndexSort selectByPrimaryKey(Integer sortId) {
        ReIndexSort _key = new ReIndexSort();
        _key.setSortId(sortId);
        return getSqlSession().selectOne("re_index_sort.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIndexSort record) {
        return getSqlSession().update("re_index_sort.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIndexSort record) {
        return getSqlSession().update("re_index_sort.updateByPrimaryKey", record);
    }


    /**
     * 查询首页分类
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public List<ReIndexSort> selectByPlatform(int platform, String version, String packageName, String channelName) {

        Map<String, Object> map = new HashMap<>(5);
        map.put("platform", platform);
        map.put("version", version);
        map.put("packageName", packageName);
        map.put("channelName", channelName);
        map.put("verifyValue", channelName+"-"+packageName);
        return getSqlSession().selectList("re_index_sort.selectByPlatform", map);

    }

}