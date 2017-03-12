package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReAndriodIntegralWallDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReAndriodIntegralWall;

import com.hongbao.api.model.dto.TrialInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-02
 */
@Repository
public class ReAndriodIntegralWallDAOImpl extends BaseSqlSessionDaoSupport
        implements ReAndriodIntegralWallDAO {
    public int deleteByPrimaryKey(Long wallId) {
        ReAndriodIntegralWall _key = new ReAndriodIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().delete("re_andriod_integral_wall.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndriodIntegralWall record) {
        getSqlSession().insert("re_andriod_integral_wall.insert", record);
    }

    public void insertSelective(ReAndriodIntegralWall record) {
        getSqlSession().insert("re_andriod_integral_wall.insertSelective", record);
    }

    public void insertBatch(List<ReAndriodIntegralWall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_andriod_integral_wall.insertBatch", records);
    }

    public ReAndriodIntegralWall selectByPrimaryKey(Long wallId) {
        ReAndriodIntegralWall _key = new ReAndriodIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().selectOne("re_andriod_integral_wall.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndriodIntegralWall record) {
        return getSqlSession().update("re_andriod_integral_wall.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndriodIntegralWall record) {
        return getSqlSession().update("re_andriod_integral_wall.updateByPrimaryKey", record);
    }


    /**
     * 查询任务
     *
     * @param wallId
     * @return
     */
    public TrialInfo selectByWallId(Long wallId) {
        return getSqlSession().selectOne("re_andriod_integral_wall.selectByWallId", wallId);
    }

    /**
     * 查询进行中的任务列表
     *
     * @param startTime
     * @param userId
     * @param sim
     * @return
     */
    public List<TrialInfo> selectAllByLeftAndTime(String startTime, Integer userId, int sim) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("startTime", startTime);
        map.put("userId", userId);
        map.put("sim", sim);
        return getSqlSession().selectList("re_andriod_integral_wall.selectAllByLeftAndTime", map);
    }

    /**
     * 查询任务明细
     *
     * @param wallId
     * @return
     */
    public ReAndriodIntegralWall selectLockByWallId(Long wallId, String nowTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("wallId", wallId);
        map.put("nowTime", nowTime);
        return getSqlSession().selectOne("re_andriod_integral_wall.selectLockByWallId", map);
    }

}