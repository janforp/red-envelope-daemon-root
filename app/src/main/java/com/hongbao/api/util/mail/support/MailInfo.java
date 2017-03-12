package com.hongbao.api.util.mail.support;

import java.io.Serializable;

/**
 * 邮件发送实体类
 *
 * @author wu-qiang
 */
public class MailInfo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 紧急邮件
     */
    public static final int MAIL_PRIORITY_MAX = 1;
    /**
     * 普通邮件
     */
    public static final int MAIL_PRIORITY_MEDIUM = 3;
    /**
     * 优先级最低的邮件
     */
    public static final int MAIL_PRIORITY_MIN = 5;
    /**
     * 发送者的邮件地址
     */
    private String fromAddress = null;
    /**
     * 接收者的邮件地址
     */
    private String toAddress = null;
    /**
     * 邮件主题
     */
    private String subject = null;
    /**
     * 邮件内容（可以是HTML代码）
     */
    private String content = null;
    /**
     * 邮件优先级1为高,3为中,5为低，默认为中
     */
    private int priority = 3;

    public MailInfo() {
        super();
    }

    public MailInfo(String fromAddress, String toAddress, String subject,
                    String content) {
        super();
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
        this.content = content;
    }

    public MailInfo(String fromAddress, String toAddress, String subject,
                    String content, int priority) {
        super();
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
        this.content = content;
        this.setPriority(priority);
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 邮件优先级1为高,3为中,5为低，默认为中
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 邮件优先级1为高,3为中,5为低，默认为中
     */
    public void setPriority(int priority) {
        if (priority == MAIL_PRIORITY_MAX || priority == MAIL_PRIORITY_MEDIUM
            || priority == MAIL_PRIORITY_MIN) {
            this.priority = priority;
        }
    }

}