package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReCodeRedDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReCodeRed;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-23
 */
@Repository
public class ReCodeRedDAOImpl extends BaseSqlSessionDaoSupport
        implements ReCodeRedDAO {
    public int deleteByPrimaryKey(Integer codeId) {
        ReCodeRed _key = new ReCodeRed();
        _key.setCodeId(codeId);
        return getSqlSession().delete("re_code_red.deleteByPrimaryKey", _key);
    }

    public void insert(ReCodeRed record) {
        getSqlSession().insert("re_code_red.insert", record);
    }

    public void insertSelective(ReCodeRed record) {
        getSqlSession().insert("re_code_red.insertSelective", record);
    }

    public void insertBatch(List<ReCodeRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_code_red.insertBatch", records);
    }

    public ReCodeRed selectByPrimaryKey(Integer codeId) {
        ReCodeRed _key = new ReCodeRed();
        _key.setCodeId(codeId);
        return getSqlSession().selectOne("re_code_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCodeRed record) {
        return getSqlSession().update("re_code_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCodeRed record) {
        return getSqlSession().update("re_code_red.updateByPrimaryKey", record);
    }

    /**
     * 获取验证码红包
     * @param platform
     * @return
     */
    public List<ReCodeRed> selectCodeRedByPlatform(int platform) {
        if(platform == 0) { // ios
            return getSqlSession().selectList("re_code_red.selectIosCodeRed");
        }else { // andriod
            return getSqlSession().selectList("re_code_red.selectAndriodCodeRed");
        }
    }

    /**
     * 获取首页验证码红包
     * @param platform
     * @return
     */
    public List<ReCodeRed> selectIndexCodeRedByPlatform(int platform) {
        if(platform == 0) { // ios
            return getSqlSession().selectList("re_code_red.selectIndexIosCodeRed");
        }else { // andriod
            return getSqlSession().selectList("re_code_red.selectIndexAndriodCodeRed");
        }
    }

}