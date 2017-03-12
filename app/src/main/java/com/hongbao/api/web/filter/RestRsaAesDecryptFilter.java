package com.hongbao.api.web.filter;

import com.hongbao.api.config.Config;
import com.hongbao.api.consts.*;
import com.hongbao.api.enums.HbUserDevicePlatform;
import com.hongbao.api.exception.IllegalRequestException;
import com.hongbao.api.service.RequestIdService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.MD5Encryption;
import com.hongbao.api.web.logger.ControllerLogger;
import com.wujie.common.security.aes.AESEncryption;
import com.wujie.common.security.base64.Base64Decoder;
import com.wujie.common.security.rsa.RSAEncryption;
import com.wujie.common.security.rsa.RSAKeyPair;
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
 * mapping : /c/rest/r/*；请求和响应，双向加密（请求是RSA+AES，响应是AES）；(r代表rsa)
 * <p/>
 * 注意：
 * 请求：
 * queryString：采用的是RSA加密
 * requestBody：采用的是AES加密
 * 响应：采用的AES加密
 *
 * @author wuqiang
 */
@Component
public class RestRsaAesDecryptFilter implements Filter {

    private static Logger selfLogger = LoggerFactory.getLogger(RestRsaAesDecryptFilter.class);

    private static final String template_string_aes_key_param_and = "&" + RequestConsts.SYSTEM_PARAM_AES_KEY + "=";
    private static final String template_string_aes_key_param_and_regex = "&" + RequestConsts.SYSTEM_PARAM_AES_KEY + "=[^&]+";
    private static final String template_string_aes_key_param_and_replacement = "&" + RequestConsts.SYSTEM_PARAM_AES_KEY + "=******";
    private static final String template_string_aes_key_param_start = RequestConsts.SYSTEM_PARAM_AES_KEY + "=";
    private static final String template_string_aes_key_param_start_regex = "^" + RequestConsts.SYSTEM_PARAM_AES_KEY + "=[^&]+";
    private static final String template_string_aes_key_param_start_replacement = RequestConsts.SYSTEM_PARAM_AES_KEY + "=******";

    private static final String template_string_password_param_and = "&" + ParamConsts.password + "=";
    private static final String template_string_password_param_and_regex = "&" + ParamConsts.password + "=[^&]+";
    private static final String template_string_password_param_and_replacement = "&" + ParamConsts.password + "=******";
    private static final String template_string_password_param_start = ParamConsts.password + "=";
    private static final String template_string_password_param_start_regex = "^" + ParamConsts.password + "=[^&]+";
    private static final String template_string_password_param_start_replacement = ParamConsts.password + "=******";

    private static final String template_string_old_password_param_and = "&" + ParamConsts.old_password + "=";
    private static final String template_string_old_password_param_and_regex = "&" + ParamConsts.old_password + "=[^&]+";
    private static final String template_string_old_password_param_and_replacement = "&" + ParamConsts.old_password + "=******";
    private static final String template_string_old_password_param_start = ParamConsts.old_password + "=";
    private static final String template_string_old_password_param_start_regex = "^" + ParamConsts.old_password + "=[^&]+";
    private static final String template_string_old_password_param_start_replacement = ParamConsts.old_password + "=******";

    @Autowired
    private RequestWrapperFactory requestWrapperFactory;
    @Autowired
    private RequestIdService requestIdService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long now = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrL = request.getRequestURL().toString();
        String requestUri = request.getRequestURI().toString();
        boolean isDebug = Config.isDebug();
        ControllerLogger logger = (ControllerLogger) LogMgr.getLogger(this.getClass());
        String charset = request.getCharacterEncoding();
//        String charset = RequestConsts.CHARSET;
        int platform = ((Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM)).intValue();// 平台
//        String version = (String) request.getAttribute(RequestConsts.ATTR_VERSION);// 版本
        //不同版本有不同的rsa，也可以统一使用一个
        String version = "1.0.0";
        RSAKeyPair keyPair = RsaKeyPairHolder.getKeyPair(version);
        if (keyPair == null) {
            logger.setException(new IllegalRequestException("version illegal."));
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_version_illegal);
            return;
        }
        String systemParameter = request.getParameter(RequestConsts.GLOBAL_SYSTEM_PARAMETER_NAME);// 系统参数字符串密文
        logger.setSystemParameter(systemParameter);
        if (systemParameter == null || systemParameter.length() <= 0) {
            logger.setException(
                    new IllegalRequestException("systemParameter required."));
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_systemParameter_required);
            return;
        }
        String decryptSystemParameter = null;// 系统参数字符串明文
        try {
            // 解密协议参数字符串
            // 相应版本的服务器RSA私钥
            byte[] decodeBase64SystemParameterBytes = Base64Decoder.decodeToBytes(systemParameter);
            byte[] decryptSystemParameterBytes = RSAEncryption.decryptByPrivateKey(decodeBase64SystemParameterBytes, keyPair);
            decryptSystemParameter = new String(decryptSystemParameterBytes, charset);
        } catch (Exception e) {
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_failed_to_decrypt_systemParameter);
            logger.setException(e);
            logger.error(e);
            return;
        }
        {
            String tmp = decryptSystemParameter;
            if (tmp.contains(template_string_aes_key_param_and)) {
                tmp = tmp.replaceAll(template_string_aes_key_param_and_regex, template_string_aes_key_param_and_replacement);
            } else if (tmp.startsWith(template_string_aes_key_param_start)) {
                tmp = tmp.replaceAll(template_string_aes_key_param_start_regex, template_string_aes_key_param_start_replacement);
            }
            logger.setDecryptSystemParameter(tmp);
        }
        if (decryptSystemParameter == null || decryptSystemParameter.trim().length() == 0) {
            logger.setException(new IllegalRequestException("decryptSystemParameter required."));
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_decryptSystemParameter_required);
            return;
        }
        Long timestamp = null;
        String deviceId = null;
        String clientTempAesKey = null;// 由客户端产生，只用于本次请求的响应加密
        //客户端上按照约定的规则生产的请求的id，服务端按照同样的规则生产，并比较
        String reqId = null;
        String userId = null;
        {
            // 解析系统参数
            String[] systemParamEntry = decryptSystemParameter.split("&");
            if (systemParamEntry == null || systemParamEntry.length == 0) {
                logger.setException(new IllegalRequestException("parse decryptSystemParameter error."));
                CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_parse_decryptSystemParameter_error);
                return;
            }
            for (String paramEntry : systemParamEntry) {
                String[] paramValue = paramEntry.split("=");
                if (paramValue != null && paramValue.length == 2) {
                    String name = paramValue[0];
                    String value = paramValue[1];
                    switch (name) {
                        case RequestConsts.SYSTEM_PARAM_TIMESTAMP:
                            try {
                                timestamp = Long.parseLong(value);
                            } catch (NumberFormatException e) {
                                logger.setException(e);
                            }
                            break;
                        case RequestConsts.SYSTEM_PARAM_DEVICE_ID:
                            try {
                                deviceId = URLDecoder.decode(value, charset);
                            } catch (Exception e) {
                                logger.setException(e);
                            }
                            break;
                        case RequestConsts.SYSTEM_PARAM_AES_KEY:
                            clientTempAesKey = value;
                            break;
                        case RequestConsts.SYSTEM_PARAM_REQ_ID:
                            try {
                                reqId = URLDecoder.decode(value, charset);
                            } catch (Exception e) {
                                logger.setException(e);
                            }
                            break;
                        case RequestConsts.SYSTEM_PARAM_USER_ID:
                            userId = value;
                        default:
                            break;
                    }
                }
            }
        }

        {
            // 验证系统参数
            if (timestamp == null || clientTempAesKey == null || deviceId == null || deviceId.trim().length() == 0 || reqId == null) {
                logger.setException(new IllegalRequestException("timestamp, deviceId, aesKey, reqId required."));
                CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_timestamp_deviceId_aesKey_reqId_required);
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

            // 客户端产生的AES秘钥是否合法
            String keyValidError = AESEncryption.validateKey(clientTempAesKey);
            if (keyValidError != null) {
                // AES秘钥不合法
                logger.setException(new IllegalRequestException(keyValidError));
                CommonMethod.sendErrorJsonResponse(request, response, 200,
                        JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, isDebug ? keyValidError : ""));
                return;
            }

        }
        String businessParameter = request.getParameter(RequestConsts.GLOBAL_BUSINESS_PARAMETER_DATA_NAME);// 业务参数字符串密文
        logger.setBusinessParameter(businessParameter);
        String decryptBusinessParameter = null;// 业务参数字符串明文
        try {
            if (businessParameter != null && businessParameter.length() > 0) {
                if (HbUserDevicePlatform.android.val.equals(platform)) {
                    decryptBusinessParameter = AESEncryption.decrypt(businessParameter, clientTempAesKey);
                } else {
                    // HbUserDevicePlatform.iOS.val
                    decryptBusinessParameter = AESEncryption.decrypt_with_pkcs7(businessParameter, clientTempAesKey);
                }
            } else {
                decryptBusinessParameter = "";
            }
        } catch (Exception e) {
            CommonMethod.sendErrorJsonResponse(request, response, 200, JsonConsts.ERROR_failed_to_decrypt_requestBody);
            logger.setException(e);
            logger.error(e);
            return;
        }
        if (decryptBusinessParameter != null) {
            String tmp = decryptBusinessParameter;
            // 把密码处理成星号，避免出现在logger中
            // 可能有密码
            if (tmp.contains(template_string_password_param_and)) {
                tmp = tmp.replaceAll(template_string_password_param_and_regex,
                        template_string_password_param_and_replacement);
            } else if (tmp.startsWith(template_string_password_param_start)) {
                tmp = tmp.replaceAll(template_string_password_param_start_regex,
                        template_string_password_param_start_replacement);
            }
            if (tmp.contains(template_string_old_password_param_and)) {
                tmp = tmp.replaceAll(template_string_old_password_param_and_regex,
                        template_string_old_password_param_and_replacement);
            } else if (tmp.startsWith(template_string_old_password_param_start)) {
                tmp = tmp.replaceAll(template_string_old_password_param_start_regex,
                        template_string_old_password_param_start_replacement);
            }
            logger.setDecryptBusinessParameter(tmp);
        }
        if (decryptBusinessParameter == null) {
            decryptBusinessParameter = "";
        }
        // 设置到Request的属性范围
        servletRequest.setAttribute(RequestConsts.ATTR_USER_ID, userId);
        servletRequest.setAttribute(RequestConsts.ATTR_DEVICE_ID, deviceId);
        servletRequest.setAttribute(RequestConsts.ATTR_CLIENT_TEMP_KEY, clientTempAesKey);
        // 请求类型是：RSA+AES加密请求（AES密钥是临时密钥）
        servletRequest.setAttribute(RequestConsts.ATTR_REQUEST_TYPE, RequestConsts.VALUE_REQUEST_TYPE_RSA_AES_TEMP_KEY);
        // 指明响应也应当采用Aes加密（用clientTempAesKey这个密钥加密）
        servletRequest.setAttribute(RequestConsts.ATTR_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_AES);
        // 在响应头中声明，响应内容采用Aes加密（用clientTempAesKey这个密钥加密）
        response.setHeader(RequestConsts.RESPONSE_HEADER_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_AES);

        // requestBody有内容
        byte[] decryptBusinessParameterBytes = decryptBusinessParameter.getBytes(charset);
        request = requestWrapperFactory.getRequestWrapper(request, new ByteArrayInputStream(decryptBusinessParameterBytes), servletRequest.getInputStream());

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
