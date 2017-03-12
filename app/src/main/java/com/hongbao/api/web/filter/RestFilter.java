package com.hongbao.api.web.filter;

import com.google.common.base.Stopwatch;
import com.hongbao.api.config.Config;
import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.enums.HbUserDevicePlatform;
import com.hongbao.api.enums.NetworkType;
import com.hongbao.api.exception.BusinessErrorMsgException;
import com.hongbao.api.exception.IllegalRequestException;
import com.hongbao.api.exception.UnableEncryptResponseException;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuqiang on 15-8-5.
 * <p/>
 * mapping : /c/rest/*
 *
 * @author wuqiang
 */
@Component
public class RestFilter implements Filter {
    private static Logger selfLogger = LoggerFactory.getLogger(RestFilter.class);
    private static Logger httpHeaderLogger = LoggerFactory.getLogger("httpHeaderLogger");
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
        String requestUri = request.getRequestURI();
        boolean isDebug = Config.isDebug();
        ControllerLogger logger = loggerFactory.getLogger(request);
        try {
            if (selfLogger.isInfoEnabled()) {
                selfLogger.info("requestUri:" + requestUri + " - request-hashCode:" + request.hashCode());
            }
            String clientIp = RequestUtil.getClientIp(request);
            // 把Logger对象设置到ThreadLocal
            _LogMgrPackageAccesser.setThreadLogger(logger);
            if (isDebug || httpHeaderLogger.isInfoEnabled()) {
                Enumeration<String> headers = request.getHeaderNames();
                Map<String, String> requestHeader = new HashMap<String, String>();
                if (headers != null) {
                    while (headers.hasMoreElements()) {
                        String name = headers.nextElement();
                        List<String> headerValueList = new ArrayList<>(3);
                        Enumeration<String> headerValues = request.getHeaders(name);
                        if (headerValues != null) {
                            while (headerValues.hasMoreElements()) {
                                headerValueList.add(headerValues.nextElement());
                            }
                        }
                        requestHeader.put(name, headerValuesToString(headerValueList));
                    }
                }
                logger.setRequestHeader(requestHeader);
                if (httpHeaderLogger.isInfoEnabled()) {
                    httpHeaderLogger.info(requestUri + " - requestHeader: \n" + requestHeader);
                }
            }
            // 设置客户端IP
            logger.setClientIp(clientIp);

            /*把request Header中的相关值设置到request属性范围-start*/
            {
                // 版本 如:1.0.0
                String version = request.getHeader(RequestConsts.HEADER_VERSION);
                request.setAttribute(RequestConsts.ATTR_VERSION, version);
            }

            {
                // 版本号
                String versionCode = request.getHeader(RequestConsts.HEADER_VERSION_CODE);
                request.setAttribute(RequestConsts.HEADER_VERSION_CODE, versionCode);
            }

            {
                // 渠道
                String channel = request.getHeader(RequestConsts.HEADER_CHANNEL_NAME);
                request.setAttribute(RequestConsts.HEADER_CHANNEL_NAME, channel);
            }

            {
                // 包名
                String packageName = request.getHeader(RequestConsts.HEADER_PACKAGE_NAME);
                request.setAttribute(RequestConsts.HEADER_PACKAGE_NAME, packageName);
            }

            {
                // 设备名称
                String deviceName = request.getHeader(RequestConsts.HEADER_DEVICE_NAME);
                request.setAttribute(RequestConsts.ATTR_DEVICE_NAME, deviceName);

            }

            {
                // 国际移动用户识别码imsi
                String imsi = request.getHeader(RequestConsts.HEADER_IMSI);
                request.setAttribute(RequestConsts.ATTR_IMSI, imsi);

            }

            {
                // 国际移动设备身份码imei
                String imei = request.getHeader(RequestConsts.HEADER_IMEI);
                request.setAttribute(RequestConsts.ATTR_IMEI, imei);

            }

            {
                // 网络类型
                NetworkType networkTypeEnum = CommonMethod.parseNetworkType(request.getHeader(RequestConsts.HEADER_NETWORK_TYPE));
                request.setAttribute(RequestConsts.ATTR_NETWORK_TYPE, networkTypeEnum);
            }

            {
                // sim卡序列号
                String sim = request.getHeader(RequestConsts.HEADER_SIM_NUM);
                request.setAttribute(RequestConsts.ATTR_SIM_NUM, sim);

            }

            {
                // 手机号
                String mobile = request.getHeader(RequestConsts.HEADER_MOBILE_NUM);
                request.setAttribute(RequestConsts.ATTR_MOBILE_NUM, mobile);
            }


            {
                // 平台
                Integer platform = null;
                try {
                    platform = Integer.parseInt(request.getHeader(RequestConsts.HEADER_PLATFORM).trim());
                } catch (Exception e) {
                }
                if (platform == null || (
                        (!HbUserDevicePlatform.android.val.equals(platform.intValue()))
                                && (!HbUserDevicePlatform.iOS.val.equals(platform.intValue())))
                        ) {
                    logger.setException(new IllegalRequestException("platform required."));
                    CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_platform_required);
                    return;
                }
                request.setAttribute(RequestConsts.ATTR_PLATFORM, platform);
            }

            {
                // screenWidth和screenHeight
                Integer screenWidth = null;
                try {
                    screenWidth = new Double(Double
                            .parseDouble(request.getHeader(RequestConsts.HEADER_SCREEN_WIDTH))).intValue();
                } catch (Exception e) {
                }
                Integer screenHeight = null;
                try {
                    screenHeight = new Double(Double
                            .parseDouble(request.getHeader(RequestConsts.HEADER_SCREEN_HEIGHT))).intValue();
                } catch (Exception e) {
                }
                if (screenWidth == null || screenWidth <= 0 || screenHeight == null
                        || screenHeight <= 0) {
                    logger.setException(
                            new IllegalRequestException("screen-width and screen-height required."));
                    CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_screen_info_required);
                    return;
                }
                request.setAttribute(RequestConsts.ATTR_SCREEN_HEIGHT, screenWidth);
                request.setAttribute(RequestConsts.ATTR_SCREEN_HEIGHT, screenHeight);
            }

            /*把request Header中的相关值设置到request属性范围-end*/
            // doFilter --> go
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (UnableEncryptResponseException e) {
            // 指明响应已采用明文方式
            servletRequest.setAttribute(RequestConsts.ATTR_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
            // 在响应头中声明，响应内容是原文
            response.setHeader(RequestConsts.RESPONSE_HEADER_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
            logger.setException(e);
            String errorJson = null;
            if (isDebug) {
                errorJson = JsonUtil.buildExceptionJson(e);
            } else {
                errorJson = JsonConsts.ERROR_unable_encrypt_response;
            }
            CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
        } catch (Throwable e) {
            // 原则上是不会进入此cache块，除非其他Filter有异常代码
            // 指明响应已采用明文方式
            servletRequest.setAttribute(RequestConsts.ATTR_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
            // 在响应头中声明，响应内容是原文
            response.setHeader(RequestConsts.RESPONSE_HEADER_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
            String errorJson = null;
            if (e.getCause() instanceof BusinessErrorMsgException) {
                e = e.getCause();
                errorJson = JsonUtil.buildErrorJson(e.getMessage());
            } else {
                logger.setException(e);
                logger.error(e);
                if (isDebug) {
                    errorJson = JsonUtil.buildExceptionJson(e.getMessage());
                } else {
                    errorJson = JsonConsts.ERROR_system_error;
                }
            }
            CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
        } finally {
            try {
                if (httpHeaderLogger.isInfoEnabled()) {
                    Collection<String> headers = response.getHeaderNames();
                    Map<String, String> responseHeader = new HashMap<String, String>();
                    if (headers != null) {
                        for (String name : headers) {
                            responseHeader.put(name, headerValuesToString(response.getHeaders(name)));
                        }
                    }
                    if (httpHeaderLogger.isInfoEnabled()) {
                        httpHeaderLogger.info(requestUri + " - responseHeader: \n" + responseHeader);
                    }
                }
            } catch (Exception e) {
            }
            if (selfLogger.isInfoEnabled()) {
                selfLogger.info(
                        new StringBuilder(logger.getRequestUri().length() + 100)
                                .append(logger.getRequestUri())
                                .append(" - total times with decrypt and encrypt : ").append((stopwatch.elapsed(
                                TimeUnit.MILLISECONDS))).append("(ms)")
                                .toString());
            }
            logger.setEndTime(System.currentTimeMillis());
            if (logger.getException() != null
                    && (!(logger.getException() instanceof IllegalRequestException))
                    ) {
                // 而且不是IllegalRequestException（非法请求）异常
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

    private static String headerValuesToString(Collection<String> values) {
        if (values == null || values.size() == 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder();
        int i = 0;
        for (String value : values) {
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(value);
            i++;
        }
        return buf.toString();
    }
}
