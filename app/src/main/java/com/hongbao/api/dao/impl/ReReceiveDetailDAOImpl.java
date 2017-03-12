package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReReceiveDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReReceiveDetail;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-18
 */
@Repository
public class ReReceiveDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReReceiveDetailDAO {
    public int deleteByPrimaryKey(Long detailId) {
        ReReceiveDetail _key = new ReReceiveDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_receive_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReReceiveDetail record) {
        getSqlSession().insert("re_receive_detail.insert", record);
    }

    public void insertSelective(ReReceiveDetail record) {
        getSqlSession().insert("re_receive_detail.insertSelective", record);
    }

    public void insertBatch(List<ReReceiveDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_receive_detail.insertBatch", records);
    }

    public ReReceiveDetail selectByPrimaryKey(Long detailId) {
        ReReceiveDetail _key = new ReReceiveDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_receive_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReReceiveDetail record) {
        return getSqlSession().update("re_receive_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReReceiveDetail record) {
        return getSqlSession().update("re_receive_detail.updateByPrimaryKey", record);
    }


    /**
     * 查询是否领取定时红包
     * @param userId
     * @param redId
     * @param dayTime
     * @return
     */
    public ReReceiveDetail selectIsReceiveFixedRed(int userId, int redId, String dayTime) {

        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("redId", redId);
        map.put("dayTime", dayTime);

        return getSqlSession().selectOne("re_receive_detail.selectIsReceiveFixedRed", map);

    }

    /**
     * 获取前3名
     * @param redId
     * @param today
     * @return
     */
    public List<ReReceiveDetail> getTop3Detail(Integer redId,String today){

        Map<String,Object> param = new HashMap<>(2);
        param.put("redId",redId);
        param.put("today",today);

        return getSqlSession().selectList("re_receive_detail.getTop3Detail",param);
    }




}