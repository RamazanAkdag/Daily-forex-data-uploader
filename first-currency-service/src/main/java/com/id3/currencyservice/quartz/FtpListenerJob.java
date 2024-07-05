package com.id3.currencyservice.quartz;

import com.id3.currencyservice.quartz.service.IFtpListenerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FtpListenerJob implements Job {
    @Autowired
    private IFtpListenerService ftpListenerService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("ftp listening job started");
        try {
            ftpListenerService.startListening();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
