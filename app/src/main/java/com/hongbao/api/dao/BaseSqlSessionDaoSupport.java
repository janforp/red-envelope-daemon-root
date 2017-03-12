package com.hongbao.api.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public abstract class BaseSqlSessionDaoSupport extends SqlSessionDaoSupport {
//    @Autowired
//    @Qualifier("sqlSessionTemplate")
//    private SqlSessionTemplate sqlSessionTemplate;
//
//    @PostConstruct
//    public void init() {
//        this.setSqlSessionTemplate(sqlSessionTemplate);
//    }

    @Autowired
    @Qualifier("sqlSessionTemplate")
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
}
