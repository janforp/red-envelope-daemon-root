package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReLoadHtmlDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReLoadHtml;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-11-22
 */
@Repository
public class ReLoadHtmlDAOImpl extends BaseSqlSessionDaoSupport
        implements ReLoadHtmlDAO {
    public int deleteByPrimaryKey(Long htmlId) {
        ReLoadHtml _key = new ReLoadHtml();
        _key.setHtmlId(htmlId);
        return getSqlSession().delete("re_load_html.deleteByPrimaryKey", _key);
    }

    public void insert(ReLoadHtml record) {
        getSqlSession().insert("re_load_html.insert", record);
    }

    public void insertSelective(ReLoadHtml record) {
        getSqlSession().insert("re_load_html.insertSelective", record);
    }

    public void insertBatch(List<ReLoadHtml> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_load_html.insertBatch", records);
    }

    public ReLoadHtml selectByPrimaryKey(Long htmlId) {
        ReLoadHtml _key = new ReLoadHtml();
        _key.setHtmlId(htmlId);
        return getSqlSession().selectOne("re_load_html.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReLoadHtml record) {
        return getSqlSession().update("re_load_html.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReLoadHtml record) {
        return getSqlSession().update("re_load_html.updateByPrimaryKey", record);
    }

    /**
     * 查询网页链接列表
     *
     * @param deviceId
     * @return
     */
    public List<ReLoadHtml> selectHtmlUtl(String deviceId) {
        return getSqlSession().selectList("re_load_html.selectHtmlUtl", deviceId);
    }

    /**
     * 查询网页点击
     *
     * @param htmlId
     * @return
     */
    public ReLoadHtml selectLockByHtmlId(Long htmlId) {
        return getSqlSession().selectOne("re_load_html.selectLockByHtmlId", htmlId);
    }

}