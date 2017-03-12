package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserRedDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserRedDetail;

import com.hongbao.api.model.dto.RedDetailInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-23
 */
@Repository
public class ReUserRedDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserRedDetailDAO {
    public int deleteByPrimaryKey(Long detailId) {
        ReUserRedDetail _key = new ReUserRedDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_user_red_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserRedDetail record) {
        getSqlSession().insert("re_user_red_detail.insert", record);
    }

    public void insertSelective(ReUserRedDetail record) {
        getSqlSession().insert("re_user_red_detail.insertSelective", record);
    }

    public void insertBatch(List<ReUserRedDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_red_detail.insertBatch", records);
    }

    public ReUserRedDetail selectByPrimaryKey(Long detailId) {
        ReUserRedDetail _key = new ReUserRedDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_user_red_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserRedDetail record) {
        return getSqlSession().update("re_user_red_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserRedDetail record) {
        return getSqlSession().update("re_user_red_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询用户是否领取红包
     *
     * @param redId
     * @param userId
     * @return
     */
    public ReUserRedDetail selectByRedIdAndUserId(Long redId, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("redId", redId);
        map.put("userId", userId);
        return getSqlSession().selectOne("re_user_red_detail.selectByRedIdAndUserId", map);
    }

    /**
     * 查询领取红包列表
     *
     * @param redId
     * @return
     */
    public List<RedDetailInfo> selectByRedId(Long redId) {
        return getSqlSession().selectList("re_user_red_detail.selectByRedId", redId);
    }

}