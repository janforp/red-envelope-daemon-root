package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReMissionRequireDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReMissionRequire;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-06
 */
@Repository
public class ReMissionRequireDAOImpl extends BaseSqlSessionDaoSupport
        implements ReMissionRequireDAO {
    public int deleteByPrimaryKey(Long requireId) {
        ReMissionRequire _key = new ReMissionRequire();
        _key.setRequireId(requireId);
        return getSqlSession().delete("re_mission_require.deleteByPrimaryKey", _key);
    }

    public void insert(ReMissionRequire record) {
        getSqlSession().insert("re_mission_require.insert", record);
    }

    public void insertSelective(ReMissionRequire record) {
        getSqlSession().insert("re_mission_require.insertSelective", record);
    }

    public void insertBatch(List<ReMissionRequire> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_mission_require.insertBatch", records);
    }

    public ReMissionRequire selectByPrimaryKey(Long requireId) {
        ReMissionRequire _key = new ReMissionRequire();
        _key.setRequireId(requireId);
        return getSqlSession().selectOne("re_mission_require.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReMissionRequire record) {
        return getSqlSession().update("re_mission_require.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReMissionRequire record) {
        return getSqlSession().update("re_mission_require.updateByPrimaryKey", record);
    }
}