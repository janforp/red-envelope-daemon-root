package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReCustomerExtendDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReCustomerExtend;

import com.hongbao.api.model.dto.ReExtendInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-28
 */
@Repository
public class ReCustomerExtendDAOImpl extends BaseSqlSessionDaoSupport
        implements ReCustomerExtendDAO {
    public int deleteByPrimaryKey(Integer id) {
        ReCustomerExtend _key = new ReCustomerExtend();
        _key.setId(id);
        return getSqlSession().delete("re_customer_extend.deleteByPrimaryKey", _key);
    }

    public void insert(ReCustomerExtend record) {
        getSqlSession().insert("re_customer_extend.insert", record);
    }

    public void insertSelective(ReCustomerExtend record) {
        getSqlSession().insert("re_customer_extend.insertSelective", record);
    }

    public void insertBatch(List<ReCustomerExtend> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer_extend.insertBatch", records);
    }

    public ReCustomerExtend selectByPrimaryKey(Integer id) {
        ReCustomerExtend _key = new ReCustomerExtend();
        _key.setId(id);
        return getSqlSession().selectOne("re_customer_extend.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomerExtend record) {
        return getSqlSession().update("re_customer_extend.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomerExtend record) {
        return getSqlSession().update("re_customer_extend.updateByPrimaryKey", record);
    }

    /**
     * 获取现金红包
     * @param platform
     * @return
     */
    public List<ReExtendInfo> selectExtendByPlatform(int platform) {
        if(platform == 0) { // ios
            return getSqlSession().selectList("re_customer_extend.selectIosExtend");
        }else { // andriod
            return getSqlSession().selectList("re_customer_extend.selectAndriodExtend");
        }
    }

    /**
     * 获取首页现金红包
     * @param platform
     * @param pageSize
     * @return
     */
    public List<ReExtendInfo> selectIndexExtendByPlatform(int platform, int pageSize) {
        if(platform == 0) { // ios
            return getSqlSession().selectList("re_customer_extend.selectIndexIosExtend", pageSize);
        }else { // andriod
            return getSqlSession().selectList("re_customer_extend.selectIndexAndriodExtend", pageSize);
        }
    }


}