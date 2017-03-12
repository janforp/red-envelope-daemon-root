package com.hongbao.api.web.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wuqiang on 15-8-3.
 *
 * @author wuqiang
 */
@Component
public class PageControllerInterceptor implements MethodInterceptor {
    private static Logger selfLogger = LoggerFactory.getLogger(PageControllerInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return methodInvocation.proceed();
    }
}
