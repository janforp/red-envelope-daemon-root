package com.hongbao.api.task.dto;

import com.hongbao.api.task.sys.SystemReporterTask;
import com.hongbao.api.util.mail.support.MailInfo;
import com.hongbao.api.util.support.ThrowablePrintStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by wuqiang on 15-8-6.
 */
public class RequestExceptionReport extends AbstractSystemReportDto {

    private static final String T_OCCUR_TIME = "#OCCUR_TIME#";
    private static final String C_EXCEPTION = "#EXCEPTION#";
    private static final String C_REQUEST_ID = "#REQUEST_ID#";
    private static final String C_REQUEST_URI = "#REQUEST_URI#";
    private static final String C_CLIENT_IP = "#CLIENT_IP#";
    private static final String C_REQUEST_HEADER = "#REQUEST_HEADER#";
    private static final String C_SYSTEM_PARAMETER = "#SYSTEM_PARAMETER#";
    private static final String C_DECRYPT_SYSTEM_PARAMETER = "#DECRYPT_SYSTEM_PARAMETER#";
    private static final String C_BUSINESS_PARAMETER = "#BUSINESS_PARAMETER#";
    private static final String C_DECRYPT_BUSINESS_PARAMETER = "#DECRYPT_BUSINESS_PARAMETER#";
    private static final String C_OCCUR_TIME = "#OCCUR_TIME#";
    private static final String C_SERVER_NAME = "#SERVER_NAME#";
    private static String
            templateTitle = projectName + " 请求发生异常 (" + T_OCCUR_TIME + ")";
    private static String templateContent;

    static {
        templateContent =
                AbstractSystemReportDto.readTemplate("template/RequestExceptionReport.txt");
    }

    // 所抛出的异常
    private Throwable exception;
    private String requestId;
    private String requestUri;
    private String clientIp;
    private Map<String, String> requestHeader;
    private String systemParameter;
    private String decryptSystemParameter;
    private String businessParameter;
    private String decryptBusinessParameter;
    private Date occurTime;

    @Override
    public MailInfo prepareTemplateEmail() {
        if (this.mailInfo == null) {
            String occurTimeStr = new SimpleDateFormat(dateFmt).format(occurTime);
            String subject =
                    RequestExceptionReport.templateTitle
                            .replace(T_OCCUR_TIME, occurTimeStr);
            String content = RequestExceptionReport.templateContent;
            ThrowablePrintStream throwablePrintStream = new ThrowablePrintStream();
            if (this.exception != null) {
                this.exception.printStackTrace(throwablePrintStream);
            }
            //
            String exceptionStr = String.valueOf(throwablePrintStream.getBuffer());
            exceptionStr = exceptionStr.replace("\t", "　　").replace("\n", "<br>");
            String _requestId = this.requestId == null ? "NULL" : this.requestId;
            content = content
                    .replace(C_EXCEPTION, exceptionStr)
                    .replace(C_REQUEST_ID, String.valueOf(_requestId))
                    .replace(C_REQUEST_URI, String.valueOf(this.requestUri))
                    .replace(C_CLIENT_IP, String.valueOf(this.clientIp))
                    .replace(C_REQUEST_HEADER, String.valueOf(this.requestHeader))
                    .replace(C_SYSTEM_PARAMETER, String.valueOf(this.systemParameter))
                    .replace(C_DECRYPT_SYSTEM_PARAMETER, String.valueOf(this.decryptSystemParameter))
                    .replace(C_BUSINESS_PARAMETER, String.valueOf(this.businessParameter))
                    .replace(C_DECRYPT_BUSINESS_PARAMETER, String.valueOf(this.decryptBusinessParameter))
                    .replace(C_OCCUR_TIME, occurTimeStr)
                    .replace(C_SERVER_NAME, SystemReporterTask.SERVER_NAME);
            this.mailInfo = new MailInfo();
            this.mailInfo.setSubject(subject);
            this.mailInfo.setContent(content);
            this.mailInfo.setPriority(
                    this.veryImportant() ? MailInfo.MAIL_PRIORITY_MAX : MailInfo.MAIL_PRIORITY_MEDIUM);
        }
        return this.mailInfo;
    }

    //email
    private boolean veryImportant = false;
    private MailInfo mailInfo;

    public RequestExceptionReport() {
        this.occurTime = new Date();
    }

    public RequestExceptionReport(String requestId, String requestUri,
                                  Throwable exception) {
        this.requestId = requestId;
        this.requestUri = requestUri;
        this.exception = exception;
        this.occurTime = new Date();
    }

    @Override
    public boolean veryImportant() {
        return this.veryImportant;
    }

    public void setVeryImportant(boolean veryImportant) {
        this.veryImportant = veryImportant;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public void setRequestHeader(Map<String, String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public void setSystemParameter(String systemParameter) {
        this.systemParameter = systemParameter;
    }

    public void setDecryptSystemParameter(String decryptSystemParameter) {
        this.decryptSystemParameter = decryptSystemParameter;
    }

    public void setBusinessParameter(String businessParameter) {
        this.businessParameter = businessParameter;
    }

    public void setDecryptBusinessParameter(String decryptBusinessParameter) {
        this.decryptBusinessParameter = decryptBusinessParameter;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }
}
