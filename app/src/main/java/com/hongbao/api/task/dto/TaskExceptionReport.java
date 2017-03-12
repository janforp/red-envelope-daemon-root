package com.hongbao.api.task.dto;

import com.hongbao.api.task.sys.SystemReporterTask;
import com.hongbao.api.util.mail.support.MailInfo;
import com.hongbao.api.util.support.ThrowablePrintStream;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wuqiang on 15-8-6.
 */
public class TaskExceptionReport extends AbstractSystemReportDto {

    private static final String T_OCCUR_TIME = "#OCCUR_TIME#";
    private static final String C_EXCEPTION = "#EXCEPTION#";
    private static final String C_TASK_NAME = "#TASK_NAME#";
    private static final String C_OCCUR_TIME = "#OCCUR_TIME#";
    private static final String C_SERVER_NAME = "#SERVER_NAME#";
    private static String
            templateTitle = projectName + " Task发生异常 (" + T_OCCUR_TIME + ")";
    private static String templateContent;

    static {
        templateContent =
                AbstractSystemReportDto.readTemplate("template/TaskExceptionReport.txt");
    }

    private String taskName;
    // 所抛出的异常
    private Throwable exception;
    private Date occurTime;

    @Override
    public MailInfo prepareTemplateEmail() {
        if (this.mailInfo == null) {
            String occurTimeStr = new SimpleDateFormat(dateFmt).format(occurTime);
            String subject =
                    TaskExceptionReport.templateTitle
                            .replace(T_OCCUR_TIME, occurTimeStr);
            String content = TaskExceptionReport.templateContent;
            ThrowablePrintStream throwablePrintStream = new ThrowablePrintStream();
            if (this.exception != null) {
                this.exception.printStackTrace(throwablePrintStream);
            }
            //
            String exceptionStr = String.valueOf(throwablePrintStream.getBuffer());
            exceptionStr = exceptionStr.replace("\t", "　　").replace("\n", "<br>");
            content = content
                    .replace(C_TASK_NAME, taskName)
                    .replace(C_EXCEPTION, exceptionStr)
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

    public TaskExceptionReport() {
    }

    public TaskExceptionReport(String taskName,
                               Throwable exception) {
        this.taskName = taskName;
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

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    @Override
    public Throwable getException() {
        return exception;
    }
}
