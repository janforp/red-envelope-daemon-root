package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReCodeExchangeDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReCodeExchangeDetail;

import com.hongbao.api.model.ReRecommendMission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-16
 */
@Repository
public class ReCodeExchangeDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReCodeExchangeDetailDAO {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReCodeExchangeDetail _key = new ReCodeExchangeDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_code_exchange_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReCodeExchangeDetail record) {
        getSqlSession().insert("re_code_exchange_detail.insert", record);
    }

    public void insertSelective(ReCodeExchangeDetail record) {
        getSqlSession().insert("re_code_exchange_detail.insertSelective", record);
    }

    public void insertBatch(List<ReCodeExchangeDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_code_exchange_detail.insertBatch", records);
    }

    public ReCodeExchangeDetail selectByPrimaryKey(Long missionId, Integer userId) {
        ReCodeExchangeDetail _key = new ReCodeExchangeDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_code_exchange_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCodeExchangeDetail record) {
        return getSqlSession().update("re_code_exchange_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCodeExchangeDetail record) {
        return getSqlSession().update("re_code_exchange_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询1条用户已完成的关注任务
     *
     * @param userId
     * @return
     */
    public ReCodeExchangeDetail selectByUserId(Integer userId) {
        return getSqlSession().selectOne("re_code_exchange_detail.selectByUserId", userId);
    }

    /**
     * 查询用户已完成的关注任务
     *
     * @param userId
     * @return
     */
    public List<ReRecommendMission> selectFinishAttentionList(Integer userId) {
        return getSqlSession().selectList("re_code_exchange_detail.selectFinishAttentionList", userId);
    }

}