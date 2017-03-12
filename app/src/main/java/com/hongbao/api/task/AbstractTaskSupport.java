package com.hongbao.api.task;

import com.hongbao.api.task.dto.TaskExceptionReport;
import com.hongbao.api.task.sys.SystemReporterTask;
import org.craigq.quartz.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTaskSupport extends AbstractTask {

    @Autowired
    private SystemReporterTask systemReporterTask;

    protected final void sendExceptionAlert(Throwable e) {
        TaskExceptionReport report = new TaskExceptionReport(this.getRealClass().getName(), e);
        this.systemReporterTask.addReport(report);
    }
}
