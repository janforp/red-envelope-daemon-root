package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserRedDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserRed;

import com.hongbao.api.model.dto.ReUserRedInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
@Repository
public class ReUserRedDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserRedDAO {
    public int deleteByPrimaryKey(Long redId) {
        ReUserRed _key = new ReUserRed();
        _key.setRedId(redId);
        return getSqlSession().delete("re_user_red.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserRed record) {
        getSqlSession().insert("re_user_red.insert", record);
    }

    public void insertSelective(ReUserRed record) {
        getSqlSession().insert("re_user_red.insertSelective", record);
    }

    public void insertBatch(List<ReUserRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_red.insertBatch", records);
    }

    public ReUserRed selectByPrimaryKey(Long redId) {
        ReUserRed _key = new ReUserRed();
        _key.setRedId(redId);
        return getSqlSession().selectOne("re_user_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserRed record) {
        return getSqlSession().update("re_user_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserRed record) {
        return getSqlSession().update("re_user_red.updateByPrimaryKey", record);
    }

    /**
     * 查询红包列表
     *
     * @param userId
     * @param redId
     * @return
     */
    public List<ReUserRedInfo> selectRedList(Integer userId, Long redId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("redId", redId);
        return getSqlSession().selectList("re_user_red.selectRedList", map);
    }

    /**
     * 查询红包
     *
     * @param redId
     * @return
     */
    public ReUserRed selectLockByRedId(Long redId) {
        return getSqlSession().selectOne("re_user_red.selectLockByRedId", redId);
    }

    /**
     * 查询红包详情
     *
     * @param userId
     * @param redId
     * @return
     */
    public ReUserRedInfo selectRedDetail(Integer userId, Long redId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("redId", redId);
        return getSqlSession().selectOne("re_user_red.selectRedDetail", map);
    }

}