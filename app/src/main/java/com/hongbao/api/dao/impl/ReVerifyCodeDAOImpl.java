package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReVerifyCodeDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.enums.VerifyCodeStatus;
import com.hongbao.api.model.ReVerifyCode;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-11
 */
@Repository
public class ReVerifyCodeDAOImpl extends BaseSqlSessionDaoSupport
        implements ReVerifyCodeDAO {
    public int deleteByPrimaryKey(Long id) {
        ReVerifyCode _key = new ReVerifyCode();
        _key.setId(id);
        return getSqlSession().delete("re_verify_code.deleteByPrimaryKey", _key);
    }

    public void insert(ReVerifyCode record) {
        getSqlSession().insert("re_verify_code.insert", record);
    }

    public void insertSelective(ReVerifyCode record) {
        getSqlSession().insert("re_verify_code.insertSelective", record);
    }

    public void insertBatch(List<ReVerifyCode> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_verify_code.insertBatch", records);
    }

    public ReVerifyCode selectByPrimaryKey(Long id) {
        ReVerifyCode _key = new ReVerifyCode();
        _key.setId(id);
        return getSqlSession().selectOne("re_verify_code.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReVerifyCode record) {
        return getSqlSession().update("re_verify_code.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReVerifyCode record) {
        return getSqlSession().update("re_verify_code.updateByPrimaryKey", record);
    }


    public String selectLatestCodeByCellphoneAndNotVerifyWithinTime(String cellphone, long startTime) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("cellphone", cellphone);
        params.put("startTime", startTime);
        params.put("verifyStatus", VerifyCodeStatus.not_verify.val);
        return getSqlSession().selectOne("re_verify_code.selectLatestCodeByCellphoneAndNotVerifyWithinTime", params);
    }

    public List<ReVerifyCode> selectByCellphoneAndCodeNotVerifyWithinTime(String cellphone, long startTime) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("cellphone", cellphone);
        params.put("startTime", startTime);
        params.put("codeVerifyStatus", VerifyCodeStatus.not_verify.val);
        return getSqlSession().selectList("re_verify_code.selectByCellphoneAndCodeNotVerifyWithinTime", params);
    }

    public void updateCodeVerifyStatusByIds(List<Long> ids, int verifyStatus) {
        if (ids == null || ids.size() <= 0) {
            return;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>(3);
        paramMap.put("ids", ids);
        paramMap.put("codeVerifyStatus", verifyStatus);
        paramMap.put("codeVerifyTime", System.currentTimeMillis());
        getSqlSession().update("re_verify_code.updateCodeVerifyStatusByIds", paramMap);
    }

    public void updateCodeVerifyStatusById(Long id, int verifyStatus) {
        Map<String, Object> paramMap = new HashMap<String, Object>(3);
        paramMap.put("id", id);
        paramMap.put("codeVerifyStatus", verifyStatus);
        paramMap.put("codeVerifyTime", System.currentTimeMillis());
        getSqlSession().update("re_verify_code.updateCodeVerifyStatusById", paramMap);
    }

}