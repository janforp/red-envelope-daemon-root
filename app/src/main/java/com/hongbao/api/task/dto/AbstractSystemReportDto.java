package com.hongbao.api.task.dto;

import com.hongbao.api.config.Config;
import com.hongbao.api.util.mail.support.MailInfo;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public abstract class AbstractSystemReportDto {

    protected final static String projectName;

    static {
        String realProjectName = "红包";
        if (Config.isDevModel()) {
            projectName = "【开发环境】" + realProjectName;
        } else {
            projectName = realProjectName;
        }
    }

    protected final static String dateFmt = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 调用了此方法才会去调用getSubject / getContent / isVeryImportant
     */
    public abstract MailInfo prepareTemplateEmail();

    /**
     * 邮件这个报告是否是非常紧急
     */
    public abstract boolean veryImportant();

    public abstract Throwable getException();

    public static String readTemplate(String templateResourcePath) {
        String template = null;
        InputStream in = null;
        try {
            in =
                    AbstractSystemReportDto.class.getClassLoader()
                            .getResourceAsStream(templateResourcePath);
            if (in != null) {
                StringBuilder buf = new StringBuilder();
                byte data[] = new byte[1024];
                int len = -1;
                while ((len = in.read(data)) != -1) {
                    buf.append(new String(data, 0, len));
                }
                template = buf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return template;
    }
}