package com.hongbao.api.util.mail.support;

import java.io.Serializable;
import java.util.Properties;

/**
 * 邮件发送实体类
 *
 * @author wu-qiang
 */
public class MailConfig implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static MailConfig defaultMailConfig = new MailConfig();
    /**
     * SMTP邮件发送服务器（IP或域名）
     */
    private String mailServerHost;//"smtp.exmail.qq.com";
    /**
     * SMTP端口
     */
    private String mailServerPort;// "25";
    /**
     * 登录邮件服务器的用户名
     */
    private String userName;
    /**
     * 登录邮件服务器的密码
     */
    private String password;
    /**
     * 邮件服务器是否需要身份验证
     */
    private boolean validate = true;

    /**
     * 构造javax.mail.Session对象时，所需要的一个基本参数
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    public MailConfig() {
        super();
    }

    public MailConfig(String mailServerHost, String mailServerPort,
                      String userName, String password, boolean validate) {
        super();
        this.mailServerHost = mailServerHost;
        this.mailServerPort = mailServerPort;
        this.userName = userName;
        this.password = password;
        this.validate = validate;
    }

    public MailConfig(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public static MailConfig getDefaultMailConfig() {
        return defaultMailConfig;
    }

}