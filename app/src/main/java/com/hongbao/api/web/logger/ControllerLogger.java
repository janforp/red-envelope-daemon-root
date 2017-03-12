package com.hongbao.api.web.logger;

import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogCycleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Rest请求，一次请求中使用的同一个Logger对象
 *
 * @author wu-qiang
 */
public class ControllerLogger implements ILogger {

    private static final long serialVersionUID = -3296737058653635152L;
    private LogCycleType logType = LogCycleType.REQUEST;
    protected Logger logger;
    private HttpServletRequest request;
    /**
     * 请求开始时间
     */
    private long startTime;
    private String requestId;
    private String requestUri;
    private String clientIp;
    private Map<String, String> requestHeader;
    private String systemParameter;
    private String decryptSystemParameter;
    private String businessParameter;
    private String decryptBusinessParameter;
    /**
     * 请求完成时间
     */
    private long endTime;
    /**
     * controller方法（业务方法）的执行耗时（单位：毫秒）
     */
    private Long controllerMethodInvokeTime;

    /**
     * 最后的操作是否发生异常
     */
    private Throwable exception;
    /**
     * 向客户端输出的返回值原文
     */
    private Object result;
    /**
     * 向客户端输出的返回值密文
     */
    private Object encryptResult;

    public ControllerLogger(HttpServletRequest request) {
        super();
        this.request = request;
        logger = LoggerFactory.getLogger(ControllerLogger.class);
        this.requestUri = request.getRequestURI();
        this.startTime = System.currentTimeMillis();
    }

    /**
     * 请求结束后，关闭操作
     */
    @Override
    public void close(boolean isSuccess) {
        if (isSuccess) {
            if (this.logger.isInfoEnabled()) {
                this.info("request-hashCode:" + request.hashCode());
                this.info("requestId:" + requestId);
                this.info("requestUri:" + requestUri);
                this.info("clientIp:" + clientIp);
                this.info("startTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                        .format(new Date(startTime)));
                this.info(
                        "endTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                                .format(new Date(endTime)));
                if (this.requestHeader == null) {
                    Enumeration<String> headers = this.request.getHeaderNames();
                    Map<String, String> _requestHeader = new HashMap<String, String>();
                    if (headers != null) {
                        String name = headers.nextElement();
                        List<String> headerValueList = new ArrayList<>(3);
                        Enumeration<String> headerValues = request.getHeaders(name);
                        if (headerValues != null) {
                            while (headerValues.hasMoreElements()) {
                                headerValueList.add(headerValues.nextElement());
                            }
                        }
                        _requestHeader.put(name, headerValuesToString(headerValueList));
                    }
                    this.requestHeader = _requestHeader;
                }
                this.info("requestHeader:" + requestHeader);
                this.info("systemParameter:" + systemParameter);
                this.info("decryptSystemParameter:" + decryptSystemParameter);
                this.info("businessParameter:" + (businessParameter != null ? businessParameter : ""));
                this.info("decryptBusinessParameter:" + (decryptBusinessParameter != null ? decryptBusinessParameter : ""));
                if (exception != null) {
                    this.error("exception:" + exception.toString());
                }
                this.info("result:" + result);
                if (encryptResult != null) {
                    this.info("encryptResult:" + encryptResult);
                }
            }
        } else {
            if (this.requestHeader == null) {
                Enumeration<String> headers = this.request.getHeaderNames();
                Map<String, String> _requestHeader = new HashMap<String, String>();
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
                        _requestHeader.put(name, headerValuesToString(headerValueList));
                    }
                }
                this.requestHeader = _requestHeader;
            }
            this.error("请求发生异常：");
            this.error("request-hashCode:" + request.hashCode());
            this.error("requestId:" + requestId);
            this.error("requestUri:" + requestUri);
            this.error("clientIp:" + clientIp);
            this.error("startTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .format(new Date(startTime)));
            this.error(
                    "endTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                            .format(new Date(endTime)));
            this.error("requestHeader:" + this.requestHeader);
            this.error("systemParameter:" + systemParameter);
            this.error("decryptSystemParameter:" + decryptSystemParameter);
            this.error("businessParameter:" + (businessParameter != null ? businessParameter : ""));
            this.error("decryptBusinessParameter:" + (decryptBusinessParameter != null ? decryptBusinessParameter : ""));
            if (exception != null) {
                this.error("exception:" + exception.toString());
            }
            this.error("result:" + result);
            if (encryptResult != null) {
                this.error("encryptResult:" + encryptResult);
            }
        }
    }

    public String getName() {
        return this.logger.getName();
    }

    @Override
    public ILogger info(Object msg) {
        logger.info(String.valueOf(msg));
        return this;
    }

    @Override
    public ILogger debug(Object msg) {
        logger.debug(String.valueOf(msg));
        return this;
    }

    @Override
    public ILogger warn(Object msg) {
        logger.warn(String.valueOf(msg));
        return this;
    }

    @Override
    public ILogger error(Object msg) {
        if (msg != null && msg instanceof Throwable) {
            this.error("", (Throwable) msg);
        } else {
            logger.error(String.valueOf(msg));
        }
        return this;
    }

    @Override
    public ILogger error(String msg, Throwable ex) {
        this.exception = ex;
        logger.error(msg, ex);
        return this;
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    @Override
    public void setLogType(LogCycleType logType) {
        this.logType = logType;
    }

    @Override
    public LogCycleType getLogType() {
        return this.logType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public Map<String, String> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(Map<String, String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getSystemParameter() {
        return systemParameter;
    }

    public void setSystemParameter(String systemParameter) {
        this.systemParameter = systemParameter;
    }

    public String getDecryptSystemParameter() {
        return decryptSystemParameter;
    }

    public void setDecryptSystemParameter(String decryptSystemParameter) {
        this.decryptSystemParameter = decryptSystemParameter;
    }

    public String getBusinessParameter() {
        return businessParameter;
    }

    public void setBusinessParameter(String businessParameter) {
        this.businessParameter = businessParameter;
    }

    public String getDecryptBusinessParameter() {
        return decryptBusinessParameter;
    }

    public void setDecryptBusinessParameter(String decryptBusinessParameter) {
        this.decryptBusinessParameter = decryptBusinessParameter;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getEncryptResult() {
        return encryptResult;
    }

    public void setEncryptResult(Object encryptResult) {
        this.encryptResult = encryptResult;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Long getControllerMethodInvokeTime() {
        return controllerMethodInvokeTime;
    }

    public void setControllerMethodInvokeTime(Long controllerMethodInvokeTime) {
        this.controllerMethodInvokeTime = controllerMethodInvokeTime;
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
