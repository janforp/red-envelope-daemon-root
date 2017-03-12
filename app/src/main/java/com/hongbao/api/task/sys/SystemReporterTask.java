package com.hongbao.api.task.sys;

import com.hongbao.api.config.Config;
import com.hongbao.api.task.dto.AbstractSystemReportDto;
import com.hongbao.api.util.ValidateUtil;
import com.hongbao.api.util.mail.MailSender;
import com.hongbao.api.util.mail.support.MailConfig;
import com.hongbao.api.util.mail.support.MailInfo;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.craigq.quartz.task.support.IDynamicTriggerTask;
import org.craigq.quartz.task.support.IQueueTask;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
//@Component  // 这个Task是通过配置文件手动注入的
@TaskCfg(cron = "*/10 * * * * ?", concurrent = true, threadCount = 3)
public class SystemReporterTask extends AbstractTask
        implements IDynamicTriggerTask, IQueueTask<AbstractSystemReportDto> {

    private List<MailConfig> reporterMailConfigs;
    private List<String> receiverMails;
    private static final Random random = new Random();

    public SystemReporterTask() {
    }

    public SystemReporterTask(String mailServerHost, Integer mailServerPort, boolean validate,
                              List<MailConfig> reporterMailConfigs, List<String> receiverMails) {
        super();
        if (reporterMailConfigs != null && reporterMailConfigs.size() > 0) {
            for (MailConfig reporterConfig : reporterMailConfigs) {
                if (reporterConfig.getMailServerHost() == null || reporterConfig.getMailServerHost().length() == 0) {
                    reporterConfig.setMailServerHost(mailServerHost);
                }
                if (reporterConfig.getMailServerPort() == null || reporterConfig.getMailServerPort().length() == 0) {
                    reporterConfig.setMailServerPort(String.valueOf(mailServerPort));
                }
            }
        }
        this.reporterMailConfigs = reporterMailConfigs;
        this.receiverMails = receiverMails;
    }

    private MailConfig getRandomReportMailConfig() {
        return reporterMailConfigs.get(random.nextInt(reporterMailConfigs.size()));
    }

    // 一次run方法执行，最多执行多少个
    private final int maxHandleCountInOneRound = 100;
    /**
     * 当前服务器的名称（标示）
     */
    public static final String SERVER_NAME = getServerIps();
    /**
     * 队列对象
     */
    private final static ConcurrentLinkedQueue<AbstractSystemReportDto>
            queue =
            new ConcurrentLinkedQueue<AbstractSystemReportDto>();


    private static String getServerIps() {
        Enumeration<NetworkInterface> netInterfaces = null;
        List<String> ips = new ArrayList<String>(4);
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ipss = ni.getInetAddresses();
                while (ipss.hasMoreElements()) {
                    String ip = ipss.nextElement().getHostAddress();
                    if ((!"127.0.0.1".equals(ip)) && (!ips.contains(ip)) && ValidateUtil
                            .match(ip, "\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                        ips.add(ip);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder buf = new StringBuilder(64);
        if (ips.size() <= 0) {
            buf.append("?");
        } else {
            for (int i = 0; i < ips.size(); i++) {
                if (i != 0) {
                    buf.append(",");
                }
                buf.append(ips.get(i));
            }
        }
        buf.append("");
        return buf.toString();
    }

    public void addReport(AbstractSystemReportDto report) {
        if (Config.isDevModel() || this.receiverMails == null || this.receiverMails.isEmpty()) {
            // 测试环境就不发送邮件
            LogMgr.getLogger().error(report.getException());
            return;
        }
        boolean isRunning = this.isRunning();
        if (!isRunning) {
            // Task已经暂停，拒绝操作
            throw new RuntimeException(this.getClass().getSimpleName() + " 已暂停，拒绝请求");
        }
        if (report != null) {
            queue.add(report);
        }
    }

    @Override
    public long getJobCountRemaining() {
        return queue.isEmpty() ? 0 : 2;
    }

    @Override
    public Collection<AbstractSystemReportDto> getQueue() {
        return null;
    }

    @Override
    public int getQueueSize() {
        return queue.size();
    }

    @Override
    public void run() {
        ILogger logger = LogMgr.getLogger();
        try {
            int handleCount = 0;
            while ((handleCount++ < maxHandleCountInOneRound) && (!queue.isEmpty())) {
                AbstractSystemReportDto dto = queue.poll();
                MailConfig reportConfig = getRandomReportMailConfig();
                if (reportConfig == null) {
                    continue;
                }
                if (dto != null && this.receiverMails != null && (!this.receiverMails.isEmpty())) {
                    MailInfo templateMail = dto.prepareTemplateEmail();
                    if (templateMail != null) {
                        for (String toAddress : this.receiverMails) {
                            reportConfig = getRandomReportMailConfig();
                            MailInfo mail = new MailInfo();
                            mail.setFromAddress(reportConfig.getUserName());
                            mail.setToAddress(toAddress);
                            mail.setPriority(templateMail.getPriority());
                            mail.setSubject(templateMail.getSubject());
                            mail.setContent(templateMail.getContent());
                            try {
                                MailSender.sendHtmlMail(reportConfig, mail);
                            } catch (Exception e) {
                                try {
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (Exception e1) {
                                    }
                                    reportConfig = getRandomReportMailConfig();
                                    mail.setFromAddress(reportConfig.getUserName());
                                    MailSender.sendHtmlMail(reportConfig, mail);
                                } catch (Exception e1) {
                                    logger.error("MailSender.sendHtmlMail", e1);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("SystemReporterTask.run", e);
        }
    }
}
