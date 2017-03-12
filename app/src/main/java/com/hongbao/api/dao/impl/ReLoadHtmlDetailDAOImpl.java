package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReLoadHtmlDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReLoadHtmlDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-22
 */
@Repository
public class ReLoadHtmlDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReLoadHtmlDetailDAO {
    public int deleteByPrimaryKey(Long detailId) {
        ReLoadHtmlDetail _key = new ReLoadHtmlDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_load_html_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReLoadHtmlDetail record) {
        getSqlSession().insert("re_load_html_detail.insert", record);
    }

    public void insertSelective(ReLoadHtmlDetail record) {
        getSqlSession().insert("re_load_html_detail.insertSelective", record);
    }

    public void insertBatch(List<ReLoadHtmlDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_load_html_detail.insertBatch", records);
    }

    public ReLoadHtmlDetail selectByPrimaryKey(Long detailId) {
        ReLoadHtmlDetail _key = new ReLoadHtmlDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_load_html_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReLoadHtmlDetail record) {
        return getSqlSession().update("re_load_html_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReLoadHtmlDetail record) {
        return getSqlSession().update("re_load_html_detail.updateByPrimaryKey", record);
    }
}