package com.hongbao.api.web.filter;

import com.hongbao.api.config.Config;
import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.enums.HbUserDevicePlatform;
import com.hongbao.api.enums.HbUserStatus;
import com.hongbao.api.exception.IllegalRequestException;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.cache.UserKeySecret;
import com.hongbao.api.service.RequestIdService;
import com.hongbao.api.service.user.HbUserRegisterLoginService;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.MD5Encryption;
import com.hongbao.api.web.logger.ControllerLogger;
import com.wujie.common.security.aes.AESEncryption;
import com.wujie.common.servlet.RequestWrapperFactory;
import org.craigq.common.logger.LogMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by wuqiang on 15-8-6.
 * <p/>
 * mapping : /c/rest/a/*
 * <p/>
 * 注意：
 * /c/rest/a/f/*：请求和响应，双向加密；(f代表full)
 * /c/rest/a/s/*：请求加密，响应明文；(s代表semi)
 *
 * @author wuqiang
 */
@Component
public class RestAesDecryptFilter implements Filter {

    private static Logger selfLogger = LoggerFactory.getLogger(RestAesDecryptFilter.class);

    @Autowired
    private RequestWrapperFactory requestWrapperFactory;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private HbUserRegisterLoginService hbUserRegisterLoginService;
    @Autowired
    private RequestIdService requestIdService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //过滤路径:/c/rest/a/*
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = request.getRequestURI().toString();
        String requestUrL = request.getRequestURL().toString();
        boolean isLogoutRequest = false; // 是否是退出登录的请求
        if (Config.getLogoutUri().equals(requestUri)) {
            isLogoutRequest = true;
        }
        ControllerLogger logger = (ControllerLogger) LogMgr.getLogger(this.getClass());
        String userKey = request.getHeader(RequestConsts.HEADER_USER_KEY);
        // 先声明，响应内容采用明文方式，本Filter中参数检查出错时，明文响应（如果进入RestAesFullEncryptFilter会进行覆盖设置）
        response.setHeader(RequestConsts.RESPONSE_HEADER_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
        if (userKey == null || userKey.trim().length() == 0) {
            logger.setException(new IllegalRequestException("userKey required."));
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_userKey_required);
            return;
        }
        UserKeySecret userKeySecret = userCacheService.getUserSecret(userKey);
        Integer userId = null;
        // 用户AES密钥
        String userSecret = null;
        if (userKeySecret == null || (userSecret = userKeySecret.getUserSecret()) == null || (userId = userKeySecret.getUserId()) == null) {
            String errorJson = JsonConsts.ERROR_401_current_user_not_exist;
            if (isLogoutRequest) {
                errorJson = JsonConsts.SUCCESS_default;
            }
            logger.setResult(errorJson);
            CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
            return;
        }
        // 用户状态
        Integer userStatus = null;
        UserInfo userInfo = userCacheService.getUser(userId);
        if (userInfo == null) {
            // 通过缓存方法无法查询到这个用户，则这个用户可能已经被屏蔽/删除了
            userStatus = hbUserRegisterLoginService.selectStatusByUserId(userId);
        } else {
            userStatus = userInfo.getUserStatus();
        }
        if (userStatus == null) {
            // 两次查询仍然找不到这个用户
            String errorJson = JsonConsts.ERROR_401_current_user_not_exist;
            if (isLogoutRequest) {
                errorJson = JsonConsts.SUCCESS_default;
            }
            logger.setResult(errorJson);
            CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
            return;
        }
        {
            // 判定用户状态
            if (!HbUserStatus.normal.val.equals(userStatus)) {
                // 非正常状态，由于目前只有一种非正常状态，则直接构建异常JSON
                String errorJson = JsonConsts.ERROR_401_user_permanent_blocked;
                if (isLogoutRequest) {
                    errorJson = JsonConsts.SUCCESS_default;
                }
                logger.setResult(errorJson);
                CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
                return;
            }
        }
        String charset = request.getCharacterEncoding();
        if (charset == null || charset.length() == 0) {
            charset = RequestConsts.CHARSET;
        }
        String systemParameter = request.getParameter(RequestConsts.GLOBAL_SYSTEM_PARAMETER_NAME);// 系统参数字符串密文
        logger.setSystemParameter(systemParameter);
        if (systemParameter == null || systemParameter.length() <= 0) {
            logger.setException(new IllegalRequestException("systemParameter required."));
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_systemParameter_required);
            return;
        }
        String decryptSystemParameter = null;// 系统参数字符串明文
        // 平台
        int platform = ((Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM)).intValue();
        try {
            // 解密协议参数字符串
            if (HbUserDevicePlatform.android.val.equals(platform)) {
                decryptSystemParameter = AESEncryption.decrypt(systemParameter, userSecret);
            } else {
                // HbUserDevicePlatform.iOS.val
                decryptSystemParameter = AESEncryption.decrypt_with_pkcs7(systemParameter, userSecret);
            }
        } catch (Exception e) {
            // 向客户端返回秘钥已更新（客户端和服务器端的秘钥不一致）
            String errorJson = JsonConsts.ERROR_401_user_password_change;
            if (isLogoutRequest) {
                errorJson = JsonConsts.SUCCESS_default;
            }
            CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
            logger.setResult(errorJson);
            logger.setException(e);
            logger.error("************************* 无法解密SystemParameter（RestAesDecryptFilter） *************************", e);
            return;
        }
        logger.setDecryptSystemParameter(decryptSystemParameter);
        if (decryptSystemParameter == null || decryptSystemParameter.trim().length() == 0) {
            logger.setException(new IllegalRequestException("decryptSystemParameter required."));
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_decryptSystemParameter_required);
            return;
        }
        Long timestamp = null;
        String deviceId = null;
        String reqId = null;
        {
            // 解析协议参数
            String[] systemParamsEntry = decryptSystemParameter.split("&");
            if (systemParamsEntry == null || systemParamsEntry.length == 0) {
                logger.setException(
                        new IllegalRequestException("parse decryptSystemParameter error."));
                CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_parse_decryptSystemParameter_error);
                return;
            }
            for (String paramEntry : systemParamsEntry) {
                String[] paramValue = paramEntry.split("=");
                if (paramValue != null && paramValue.length == 2) {
                    String name = paramValue[0];
                    String value = paramValue[1];
                    switch (name) {
                        case RequestConsts.SYSTEM_PARAM_TIMESTAMP:
                            try {
                                timestamp = Long.parseLong(value);
                            } catch (NumberFormatException e) {
                            }
                            break;
                        case RequestConsts.SYSTEM_PARAM_DEVICE_ID:
                            try {
                                deviceId = URLDecoder.decode(value, charset);
                            } catch (Exception e) {
                                logger.setException(e);
                            }
                            break;
                        case RequestConsts.SYSTEM_PARAM_REQ_ID:
                            try {
                                reqId = URLDecoder.decode(value, charset);
                            } catch (Exception e) {
                                logger.setException(e);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        {
            // 验证协议参数
            if (timestamp == null || deviceId == null || deviceId.trim().length() == 0 || reqId == null) {
                logger.setException(new IllegalRequestException("timestamp, deviceId required."));
                CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_timestamp_deviceId_reqId_required);
                return;
            }

            // 验证reqId
            String requestId;
            String reqVersion = (String) request.getAttribute(RequestConsts.ATTR_VERSION);// 版本
            if(CommonMethod.compareVersion(reqVersion, "5.0.0") >= 0) {
                requestId = MD5Encryption.encodeMD5(timestamp + deviceId + requestUri);
            }else {
                requestId = MD5Encryption.encodeMD5(timestamp + deviceId + requestUrL);
            }

            if(!requestId.equals(reqId)) {
                CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_reqId);
                return;
            }
            // 判断请求是否有效
            boolean isAlreadyRequest = requestIdService.getRequestId(requestId);
            if(isAlreadyRequest) {
                CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_reqId);
                return;
            }else { // 设置缓存时间 300秒
                requestIdService.setRequestId(requestId, 300L);
            }


//            // 校验时间戳
//            long now = System.currentTimeMillis();
//            long timeDeviation = Config.getSecurity().getTimeDeviation();
//            if (timeDeviation > 0 && Math.abs(now - timestamp) > timeDeviation) {
//                // 时间戳超过允许误差
//                String exceptionMsg = "timestamp illegal, timeDeviation:" + timeDeviation + ", now:" + now
//                        + ", timestamp in systemParameter:" + timestamp;
//                logger.setException(new IllegalRequestException(exceptionMsg));
//                String errorJson = JsonConsts.ERROR_timestamp;
//                logger.setResult(errorJson);
//                logger.error(exceptionMsg);
//                CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
//                return;
//            }

        }
        String businessParameter = request.getParameter(RequestConsts.GLOBAL_BUSINESS_PARAMETER_DATA_NAME);// 业务参数字符串密文
        logger.setBusinessParameter(businessParameter);
        String decryptBusinessParameter = null;// 业务参数字符串明文
        try {
            if (businessParameter != null && businessParameter.length() > 0) {
                if (HbUserDevicePlatform.android.val.equals(platform)) {
                    decryptBusinessParameter = AESEncryption.decrypt(businessParameter, userSecret);
                } else {
                    // HbUserDevicePlatform.iOS.val
                    decryptBusinessParameter = AESEncryption.decrypt_with_pkcs7(businessParameter, userSecret);
                }
            } else {
                decryptBusinessParameter = "";
            }
        } catch (Exception e) {
            // 向客户端返回秘钥已更新（客户端和服务器端的秘钥不一致）
            String errorJson = JsonConsts.ERROR_401_user_password_change;
            if (isLogoutRequest) {
                errorJson = JsonConsts.SUCCESS_default;
            }
            CommonMethod.sendErrorJsonResponse(request, response, 200, errorJson);
            logger.setResult(errorJson);
            logger.setException(e);
            logger.error("************************* 无法解密BusinessParameter（RestAesDecryptFilter） *************************", e);
            return;
        }
        if (decryptBusinessParameter == null) {
            decryptBusinessParameter = "";
        }
        logger.setDecryptBusinessParameter(decryptBusinessParameter);
        // 设置到Request的属性范围
        servletRequest.setAttribute(RequestConsts.ATTR_DEVICE_ID, deviceId);
        servletRequest.setAttribute(RequestConsts.ATTR_USER_ID, userId);
        servletRequest.setAttribute(RequestConsts.ATTR_USER_KEY, userKey);
        // requestBody有内容
        byte[] decryptBusinessParameterBytes = decryptBusinessParameter.getBytes(charset);
        request = requestWrapperFactory.getRequestWrapper(request, new ByteArrayInputStream(decryptBusinessParameterBytes), servletRequest.getInputStream());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
