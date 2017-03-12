package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAccountDetail;

import com.hongbao.api.model.dto.IndexRollInfo;
import com.hongbao.api.model.dto.RollInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-18
 */
@Repository
public class ReAccountDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAccountDetailDAO {
    public int deleteByPrimaryKey(Long detailId) {
        ReAccountDetail _key = new ReAccountDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_account_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReAccountDetail record) {
        getSqlSession().insert("re_account_detail.insert", record);
    }

    public void insertSelective(ReAccountDetail record) {
        getSqlSession().insert("re_account_detail.insertSelective", record);
    }

    public void insertBatch(List<ReAccountDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_account_detail.insertBatch", records);
    }

    public ReAccountDetail selectByPrimaryKey(Long detailId) {
        ReAccountDetail _key = new ReAccountDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_account_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAccountDetail record) {
        return getSqlSession().update("re_account_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAccountDetail record) {
        return getSqlSession().update("re_account_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询账户明细
     * @param userId
     * @param detailId
     * @return
     */
    public List<ReAccountDetail> selectAccountDetailList(Integer userId, Long detailId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("detailId", detailId);
        return getSqlSession().selectList("re_account_detail.selectAccountDetailList", map);
    }

    /**
     * 查询首页滚动信息
     * @return
     */
    public List<RollInfo> selectRollList() {
        return getSqlSession().selectList("re_account_detail.selectRollList");
    }

    /**
     * 查询今日收入
     *
     * @param userId
     * @param dayTime
     * @return
     */
    public BigDecimal selectByUserIdAndDay(Integer userId, String dayTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectOne("re_account_detail.selectByUserIdAndDay", map);
    }

    /**
     * 查询首页滚动信息
     * v4.0
     *
     * @return
     */
    public List<IndexRollInfo> selectIndexRollList() {
        return getSqlSession().selectList("re_account_detail.selectIndexRollList");
    }

    /**
     * 查询用户分享任务累计收入
     *
     * @param userId
     * @return
     */
    public BigDecimal selectShareByUserId(Integer userId) {
        return getSqlSession().selectOne("re_account_detail.selectShareByUserId", userId);
    }

}