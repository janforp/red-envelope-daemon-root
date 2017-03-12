package com.hongbao.api.listener;

import com.hongbao.api.config.Config;
import com.wujie.common.utils.support.fastjson.FastJsonHack;

import javax.servlet.ServletContextEvent;

/**
 * Created by wuqiang on 15-8-4.
 *
 * @author wuqiang
 */
public class HbServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FastJsonHack.hack();
        Config.init(); // 什么以不执行, 只是触发执行static{}
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
