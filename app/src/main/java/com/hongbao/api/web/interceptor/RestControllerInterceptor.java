package com.hongbao.api.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.hongbao.api.config.Config;
import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.MsgConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.enums.HbUserDevicePlatform;
import com.hongbao.api.exception.BusinessErrorMsgException;
import com.hongbao.api.exception.UnableEncryptResponseException;
import com.hongbao.api.model.cache.UserKeySecret;
import com.hongbao.api.model.vo.EncryptDataVo;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.web.logger.ControllerLogger;
import com.wujie.common.security.aes.AESEncryption;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.TimeUnit;

/**
 * Created by wuqiang on 15-8-3.
 *
 * @author wuqiang
 */
@Component
public class RestControllerInterceptor implements MethodInterceptor {

    private static Logger selfLogger = LoggerFactory.getLogger(RestControllerInterceptor.class);

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        boolean isDebug = Config.isDebug();
        // 本次请求的响应应当采用的数据方案
        boolean shouldResponseEncryptByAes = RequestConsts.VALUE_DATA_TYPE_AES.equals(requestAttributes.getAttribute(RequestConsts.ATTR_RESPONSE_DATA_TYPE, RequestAttributes.SCOPE_REQUEST));
        // 本次请求采用的请求加密方案
        Object requestType = requestAttributes.getAttribute(RequestConsts.ATTR_REQUEST_TYPE, RequestAttributes.SCOPE_REQUEST);
        ILogger logger = LogMgr.getLogger();
        ControllerLogger requestLogger = null;
        if (logger instanceof ControllerLogger) {
            requestLogger = (ControllerLogger) logger;
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        long controllerMethodInvokeTime = 0;
        Object result = null;
        try {
            result = methodInvocation.proceed();
            controllerMethodInvokeTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        } catch (BusinessErrorMsgException businessErrorMsgException) {
            controllerMethodInvokeTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            // 业务错误消息异常，需要向客户端返回businessErrorMsgException.getMessage();
            String errorMsg = businessErrorMsgException.getMessage();
            if (errorMsg == null || errorMsg.length() == 0) {
                errorMsg = MsgConsts.SYSTEM_ERROR;
            }
            result = JsonUtil.buildErrorJson(errorMsg);
        } catch (org.springframework.web.util.NestedServletException e) {
            if (e.getCause() != null && (e.getCause() instanceof BusinessErrorMsgException)) {
                // 有时BusinessErrorMsgException可能会被NestedServletException封装起来抛出
                BusinessErrorMsgException businessErrorMsgException = (BusinessErrorMsgException) e.getCause();
                controllerMethodInvokeTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                // 业务错误消息异常，需要向客户端返回businessErrorMsgException.getMessage();
                String errorMsg = businessErrorMsgException.getMessage();
                if (errorMsg == null || errorMsg.length() == 0) {
                    errorMsg = MsgConsts.SYSTEM_ERROR;
                }
                result = JsonUtil.buildErrorJson(errorMsg);
            } else {
                // 与 catch (Throwable throwable) { 逻辑相同
                controllerMethodInvokeTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                if (requestLogger != null) {
                    requestLogger.setException(e);
                }
                if (isDebug) {
                    result = JsonUtil.buildExceptionJson(e);
                } else {
                    result = JsonUtil.buildExceptionJson(MsgConsts.SYSTEM_ERROR);
                }
                logger.error(e);
            }
        } catch (Throwable e) {
            controllerMethodInvokeTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            if (requestLogger != null) {
                requestLogger.setException(e);
            }
            if (isDebug) {
                result = JsonUtil.buildExceptionJson(e);
            } else {
                result = JsonUtil.buildExceptionJson(MsgConsts.SYSTEM_ERROR);
            }
            logger.error(e);
        }
        try {
            String thisClassName = methodInvocation.getThis().getClass().getSimpleName();
            String methodName = methodInvocation.getMethod().getName();
            if (requestLogger != null) {
                requestLogger.setControllerMethodInvokeTime(controllerMethodInvokeTime);
            }
            if (selfLogger.isInfoEnabled()) {
                selfLogger.info(
                        new StringBuilder(thisClassName.length() + methodName.length() + 60)
                                .append(thisClassName).append(".").append(methodName)
                                .append(" -  invoke times : ").append(controllerMethodInvokeTime).append("(ms)")
                                .toString());
            }
        } catch (Exception e) {
        }
        Object platformObj = requestAttributes.getAttribute(RequestConsts.ATTR_PLATFORM, RequestAttributes.SCOPE_REQUEST);
        if (platformObj == null || !(platformObj instanceof Integer)) {
            return JsonConsts.ERROR_404;
        }
        // 平台
        int platform = ((Integer) platformObj).intValue();
        if (shouldResponseEncryptByAes) {
            // 前置Filter要求采用AES响应加密操作
            try {
                String encryptResult = ""; // 响应密文
                if (RequestConsts.VALUE_REQUEST_TYPE_RSA_AES_TEMP_KEY.equals(requestType)) {
                    // VALUE_REQUEST_TYPE_RSA_AES_TEMP_KEY
                    // 请求类型是：RSA+AES加密请求（AES密钥是临时密钥）
                    Object attrClientTempKey = requestAttributes.getAttribute(RequestConsts.ATTR_CLIENT_TEMP_KEY, RequestAttributes.SCOPE_REQUEST);
                    if (requestLogger != null) {
                        // 登陆前的请求只打印密文
                        requestLogger.setResult("***ENCRYPTION***");
                    }
                    String clientTempKey = (String) attrClientTempKey;
                    String keyValidError = AESEncryption.validateKey(clientTempKey);
                    if (keyValidError != null) {
                        throw new UnableEncryptResponseException(keyValidError);
                    }
                    if (HbUserDevicePlatform.android.val.equals(platform)) {
                        encryptResult = AESEncryption.encrypt(String.valueOf(result).getBytes(RequestConsts.CHARSET), clientTempKey);
                    } else {
                        // AgUserDevicePlatform.iOS.val
                        encryptResult = AESEncryption.encrypt_with_pkcs7(String.valueOf(result).getBytes(RequestConsts.CHARSET), clientTempKey);
                    }
                } else {
                    // VALUE_REQUEST_TYPE_AES_USER_SECRET_FULL
                    // 请求类型是：使用userSecret作为密钥AES双向加密请求
                    Object attrUserKey = requestAttributes.getAttribute(RequestConsts.ATTR_USER_KEY, RequestAttributes.SCOPE_REQUEST);
                    if (requestLogger != null) {
                        requestLogger.setResult(result);
                    }
                    String userKey = (String) attrUserKey;
                    UserKeySecret userKeySecret = userCacheService.getUserSecret(userKey);
                    String userSecret = userKeySecret.getUserSecret();
                    String keyValidError = AESEncryption.validateKey(userSecret);
                    if (keyValidError != null) {
                        throw new UnableEncryptResponseException(keyValidError);
                    }
                    if (HbUserDevicePlatform.android.val.equals(platform)) {
                        encryptResult = AESEncryption.encrypt(String.valueOf(result).getBytes(RequestConsts.CHARSET), userSecret);
                    } else {
                        // HbUserDevicePlatform.iOS.val
                        encryptResult = AESEncryption.encrypt_with_pkcs7(String.valueOf(result).getBytes(RequestConsts.CHARSET), userSecret);
                    }
                }
                // 把返回的密文数据封装成一个JSON对象
                encryptResult = JSON.toJSONString(new EncryptDataVo(encryptResult));
                if (requestLogger != null) {
                    requestLogger.setEncryptResult(encryptResult);
                }
                result = encryptResult;
            } catch (UnableEncryptResponseException e) {
                throw e;
            } catch (Exception e) {
                throw new UnableEncryptResponseException(
                        "be required AES encryption, but failed to encrypt.", e);
            }
        } else {
            // 明文响应方案
            if (requestLogger != null) {
                requestLogger.setResult(result);
            }
        }
        return result;
    }
}
