package com.hongbao.api.util.mail;

import com.hongbao.api.util.mail.support.MailAuthenticator;
import com.hongbao.api.util.mail.support.MailConfig;
import com.hongbao.api.util.mail.support.MailInfo;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class MailSender {

    /**
     * 通过mailInfo这个邮件实体类，来发送一封普通文本的电子邮件
     */
    public static boolean sendTextMail(MailConfig config, MailInfo mail) {
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = buildTextMailMessage(config, mail);
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 对多个邮箱发送相同内容的邮件
     */
    public static boolean sendTextMail(List<String> addresses,
                                       MailConfig config, MailInfo mail) {
        try {
            Message mailMessage = buildTextMailMessage(config, mail);

            // 多个发送地址设定
            Address[] to = new InternetAddress[addresses.size()];
            int i = 0;
            for (String addresse : addresses) {
                to[i] = new InternetAddress(addresse);
                i++;
            }
            if (mailMessage != null) {
                // 发送邮件
                return sendMail2Many(to, mailMessage);
            }
        } catch (AddressException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * 对多个邮箱发送相同内容的邮件
     */
    public static boolean sendHtmlMail(List<String> addresses,
                                       MailConfig config, MailInfo mail) {
        try {
            Message mailMessage = buildHtmlMailMessage(config, mail);

            // 多个发送地址设定
            Address[] to = new InternetAddress[addresses.size()];
            int i = 0;
            for (String addresse : addresses) {
                to[i] = new InternetAddress(addresse);
                i++;
            }
            if (mailMessage != null) {
                // 发送邮件
                return sendMail2Many(to, mailMessage);
            }
        } catch (AddressException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 多地址邮件发送
     */
    private static boolean sendMail2Many(Address[] toAddresses,
                                         Message mailMessage) {
        try {
            // 发送邮件
            Transport.send(mailMessage, toAddresses);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建邮件内容
     */
    private static Message buildTextMailMessage(MailConfig config, MailInfo mail) {
        MailAuthenticator authenticator = null;
        Properties pro = config.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (config.isValidate()) {
            authenticator = new MailAuthenticator(config.getUserName(),
                    config.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mail.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            if (mail.getToAddress() != null
                    && (!"".equals(mail.getToAddress().trim()))) {
                // 创建邮件的接收者地址，并设置到邮件消息中
                Address to = new InternetAddress(mail.getToAddress());
                // Message.RecipientType.TO属性表示接收者的类型为TO
                mailMessage.setRecipient(Message.RecipientType.TO, to);
            }
            // 邮件优先级1为高,3为中,5为低，默认为中
            mailMessage.addHeader("X-Priority",
                    String.valueOf(mail.getPriority()));
            // 设置邮件消息的主题
            mailMessage.setSubject(mail.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mail.getContent();
            mailMessage.setText(mailContent);

            return mailMessage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建邮件内容
     */
    private static Message buildHtmlMailMessage(MailConfig config, MailInfo mail) {
        MailAuthenticator authenticator = null;
        Properties pro = config.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (config.isValidate()) {
            authenticator = new MailAuthenticator(config.getUserName(),
                    config.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mail.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            if (mail.getToAddress() != null
                    && (!"".equals(mail.getToAddress().trim()))) {
                // 创建邮件的接收者地址，并设置到邮件消息中
                Address to = new InternetAddress(mail.getToAddress());
                // Message.RecipientType.TO属性表示接收者的类型为TO
                mailMessage.setRecipient(Message.RecipientType.TO, to);
            }
            // 设置邮件消息的主题
            mailMessage.setSubject(mail.getSubject());
            // 邮件优先级1为高,3为中,5为低，默认为中
            mailMessage.addHeader("X-Priority",
                    String.valueOf(mail.getPriority()));
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mail.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            return mailMessage;
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 通过mailInfo这个邮件实体类，来发送一封HTML格式的电子邮件
     */
    public static boolean sendHtmlMail(MailConfig config, MailInfo mail) {
        try {
            // 发送邮件
            Message msg = buildHtmlMailMessage(config, mail);
            Transport.send(msg);
            return true;
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
