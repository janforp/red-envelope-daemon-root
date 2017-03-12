package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReFixedRedDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReFixedRed;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-18
 */
@Repository
public class ReFixedRedDAOImpl extends BaseSqlSessionDaoSupport
        implements ReFixedRedDAO {
    public int deleteByPrimaryKey(Integer fixedId) {
        ReFixedRed _key = new ReFixedRed();
        _key.setFixedId(fixedId);
        return getSqlSession().delete("re_fixed_red.deleteByPrimaryKey", _key);
    }

    public void insert(ReFixedRed record) {
        getSqlSession().insert("re_fixed_red.insert", record);
    }

    public void insertSelective(ReFixedRed record) {
        getSqlSession().insert("re_fixed_red.insertSelective", record);
    }

    public void insertBatch(List<ReFixedRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_fixed_red.insertBatch", records);
    }

    public ReFixedRed selectByPrimaryKey(Integer fixedId) {
        ReFixedRed _key = new ReFixedRed();
        _key.setFixedId(fixedId);
        return getSqlSession().selectOne("re_fixed_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReFixedRed record) {
        return getSqlSession().update("re_fixed_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReFixedRed record) {
        return getSqlSession().update("re_fixed_red.updateByPrimaryKey", record);
    }


    /**
     * 查询定时红包列表
     * @param platform
     * @return
     */
    public List<ReFixedRed> selectFixedRedByPlatform(int platform) {

        if(platform == 0) { // ios

            return getSqlSession().selectList("re_fixed_red.selectIosFixedRed");

        }else { // andriod

            return getSqlSession().selectList("re_fixed_red.selectAndriodFixedRed");

        }

    }

    /**
     * 查询所有定时红包
     * @return
     */
    public List<ReFixedRed> selectAll() {
        return getSqlSession().selectList("re_fixed_red.selectAll");
    }


    /**
     * 查询定时红包剩余
     *
     * @param fixedId
     * @return
     */
    public int selectRemainder(int fixedId) {
        return getSqlSession().selectOne("re_fixed_red.selectRemainder", fixedId);
    }


    /**
     * 查询定时红包
     * @param fixedId
     * @return
     */
    public ReFixedRed selectNumLockById(int fixedId){
        return getSqlSession().selectOne("re_fixed_red.selectNumLockById", fixedId);
    }


    /**
     * 更新剩余数量
     * @param fixedId
     * @param remainderNum
     * @return
     */
    public int updateRemainder(int fixedId, int remainderNum) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("fixedId", fixedId);
        map.put("remainderNum", remainderNum);
        return getSqlSession().update("re_fixed_red.updateRemainder", map);
    }

}