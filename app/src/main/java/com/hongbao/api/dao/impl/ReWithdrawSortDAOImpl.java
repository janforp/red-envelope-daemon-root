package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReWithdrawSortDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReWithdrawSort;

import com.hongbao.api.model.dto.ReWithdrawSortInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
@Repository
public class ReWithdrawSortDAOImpl extends BaseSqlSessionDaoSupport
        implements ReWithdrawSortDAO {
    public int deleteByPrimaryKey(Integer withdrawSortId) {
        ReWithdrawSort _key = new ReWithdrawSort();
        _key.setWithdrawId(withdrawSortId);
        return getSqlSession().delete("re_withdraw_sort.deleteByPrimaryKey", _key);
    }

    public void insert(ReWithdrawSort record) {
        getSqlSession().insert("re_withdraw_sort.insert", record);
    }

    public void insertSelective(ReWithdrawSort record) {
        getSqlSession().insert("re_withdraw_sort.insertSelective", record);
    }

    public void insertBatch(List<ReWithdrawSort> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_withdraw_sort.insertBatch", records);
    }

    public ReWithdrawSort selectByPrimaryKey(Integer withdrawSortId) {
        ReWithdrawSort _key = new ReWithdrawSort();
        _key.setWithdrawId(withdrawSortId);
        return getSqlSession().selectOne("re_withdraw_sort.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWithdrawSort record) {
        return getSqlSession().update("re_withdraw_sort.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWithdrawSort record) {
        return getSqlSession().update("re_withdraw_sort.updateByPrimaryKey", record);
    }

    /**
     * 查询提现列表
     *
     * @param platform
     * @return
     */
    public List<ReWithdrawSortInfo> selectWithdrawSortByPlatform(int platform) {

        if(platform == 0) { // ios
            return getSqlSession().selectList("re_withdraw_sort.selectIosWithdrawSort");
        }else { // andriod
            return getSqlSession().selectList("re_withdraw_sort.selectAndriodWithdrawSort");
        }

    }

}