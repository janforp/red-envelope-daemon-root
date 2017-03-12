package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReScoreDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReScoreDetail;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-10
 */
@Repository
public class ReScoreDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReScoreDetailDAO {
    public int deleteByPrimaryKey(Integer scoreId) {
        ReScoreDetail _key = new ReScoreDetail();
        _key.setScoreId(scoreId);
        return getSqlSession().delete("re_score_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReScoreDetail record) {
        getSqlSession().insert("re_score_detail.insert", record);
    }

    public void insertSelective(ReScoreDetail record) {
        getSqlSession().insert("re_score_detail.insertSelective", record);
    }

    public void insertBatch(List<ReScoreDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_score_detail.insertBatch", records);
    }

    public ReScoreDetail selectByPrimaryKey(Integer scoreId) {
        ReScoreDetail _key = new ReScoreDetail();
        _key.setScoreId(scoreId);
        return getSqlSession().selectOne("re_score_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReScoreDetail record) {
        return getSqlSession().update("re_score_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReScoreDetail record) {
        return getSqlSession().update("re_score_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询金币明细
     * @param userId
     * @param scoreId
     * @return
     */
    public List<ReScoreDetail> selectScoreDetailList(Integer userId, Integer scoreId) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("scoreId", scoreId);
        return getSqlSession().selectList("re_score_detail.selectScoreDetailList", map);

    }

    /**
     * 查询今日收入
     *
     * @param userId
     * @param dayTime
     * @return
     */
    public Integer selectByUserIdAndDay(Integer userId, String dayTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectOne("re_score_detail.selectByUserIdAndDay", map);
    }

}