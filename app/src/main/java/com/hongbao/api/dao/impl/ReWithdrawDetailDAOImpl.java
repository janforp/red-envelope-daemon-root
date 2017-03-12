package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReWithdrawDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReWithdrawDetail;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
@Repository
public class ReWithdrawDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReWithdrawDetailDAO {
    public int deleteByPrimaryKey(Long withdrawId) {
        ReWithdrawDetail _key = new ReWithdrawDetail();
        _key.setWithdrawId(withdrawId);
        return getSqlSession().delete("re_withdraw_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReWithdrawDetail record) {
        getSqlSession().insert("re_withdraw_detail.insert", record);
    }

    public void insertSelective(ReWithdrawDetail record) {
        getSqlSession().insert("re_withdraw_detail.insertSelective", record);
    }

    public void insertBatch(List<ReWithdrawDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_withdraw_detail.insertBatch", records);
    }

    public ReWithdrawDetail selectByPrimaryKey(Long withdrawId) {
        ReWithdrawDetail _key = new ReWithdrawDetail();
        _key.setWithdrawId(withdrawId);
        return getSqlSession().selectOne("re_withdraw_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWithdrawDetail record) {
        return getSqlSession().update("re_withdraw_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWithdrawDetail record) {
        return getSqlSession().update("re_withdraw_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询已提现次数
     *
     * @param userId
     * @param monthTime
     * @return
     */
    public int selectTimesByUserId(int userId, String monthTime) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("monthTime", monthTime);
        return getSqlSession().selectOne("re_withdraw_detail.selectTimesByUserId", map);

    }

    /**
     * 查询提现记录
     *
     * @param userId
     * @param withdrawStatus
     * @param withdrawId
     * @return
     */
    public List<ReWithdrawDetail> selectWithdrawList(Integer userId, Integer withdrawStatus, Long withdrawId) {

        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("withdrawStatus", withdrawStatus);
        map.put("withdrawId", withdrawId);
        return getSqlSession().selectList("re_withdraw_detail.selectWithdrawList", map);

    }

}