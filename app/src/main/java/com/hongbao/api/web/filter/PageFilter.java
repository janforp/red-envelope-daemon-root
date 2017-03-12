package com.hongbao.api.web.filter;

import com.google.common.base.Stopwatch;
import com.hongbao.api.config.Config;
import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.task.sys.SystemReporterTask;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.RequestUtil;
import com.hongbao.api.web.logger.ControllerLogger;
import com.hongbao.api.web.logger.ControllerLoggerFactory;
import org.craigq.common.logger._LogMgrPackageAccesser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuqiang on 15-8-5.
 * <p/>
 * mapping : /c/page/*
 *
 * @author wuqiang
 */
@Component
public class PageFilter implements Filter {
    private static Logger selfLogger = LoggerFactory.getLogger(PageFilter.class);
    @Autowired
    private ControllerLoggerFactory loggerFactory;
    @Autowired
    private SystemReporterTask systemReporterTask;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean isDebug = Config.isDebug();
        ControllerLogger logger = loggerFactory.getLogger(request);
        try {
            String clientIp = RequestUtil.getClientIp(request);
            // 把Logger对象设置到ThreadLocal
            _LogMgrPackageAccesser.setThreadLogger(logger);
            if (isDebug) {
                Enumeration<String> headers = request.getHeaderNames();
                Map<String, String> requestHeader = new HashMap<String, String>();
                if (headers != null) {
                    while (headers.hasMoreElements()) {
                        String name = headers.nextElement();
                        String value = request.getHeader(name);
                        requestHeader.put(name, value);
                    }
                }
                logger.setRequestHeader(requestHeader);
            }
            // 设置客户端IP
            logger.setClientIp(clientIp);
            Enumeration<String> paramNames = request.getParameterNames();
            StringBuilder paramBuf = new StringBuilder(1024);
            if (paramNames != null) {
                boolean isFirst = true;
                while (paramNames.hasMoreElements()) {
                    String pn = paramNames.nextElement();
                    if (!isFirst) {
                        paramBuf.append("&");
                    }
                    String[] pvs = request.getParameterValues(pn);
                    if (pvs == null || pvs.length == 0) {
                        paramBuf.append(pn).append("=").append("");
                    } else if (pvs.length == 1) {
                        paramBuf.append(pn).append("=").append(URLEncoder.encode(pvs[0], RequestConsts.CHARSET));
                    } else {
                        for (int i = 0; i < pvs.length; i++) {
                            if (i != 0) {
                                paramBuf.append("&");
                            }
                            paramBuf.append(pn).append("=").append(URLEncoder.encode(pvs[i], RequestConsts.CHARSET));
                        }
                    }
                    isFirst = false;
                }
            }
            logger.setBusinessParameter(paramBuf.toString());
            // doFilter --> go
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Throwable e) {
            logger.setException(e);
            logger.error(e);
            String errorJson = null;
            if (isDebug) {
                errorJson = JsonUtil.buildExceptionJson(e);
            } else {
                errorJson = JsonConsts.ERROR_system_error;
            }
            CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
        } finally {
            if (selfLogger.isInfoEnabled()) {
                selfLogger.info(
                        new StringBuilder(logger.getRequestUri().length() + 100)
                                .append(logger.getRequestUri())
                                .append(" - total times with decrypt and encrypt : ")
                                .append((stopwatch.elapsed(TimeUnit.MILLISECONDS)))
                                .append("(ms)")
                                .toString());
            }
            logger.setEndTime(System.currentTimeMillis());
            if (logger.getException() != null) {
                CommonMethod.sendControllerExceptionReport(systemReporterTask, request, logger.getException(), logger);
            }
            logger.close(logger.getException() == null);
            // 清空ThreadLocal上的Logger
            _LogMgrPackageAccesser.setThreadLogger(null);
        }
    }

    @Override
    public void destroy() {

    }
}
