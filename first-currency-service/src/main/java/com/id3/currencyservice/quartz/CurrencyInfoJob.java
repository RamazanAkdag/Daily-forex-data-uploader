package com.id3.currencyservice.quartz;


import com.id3.currencyservice.quartz.service.ICurrencyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CurrencyInfoJob implements Job {
    @Autowired
    private ICurrencyInfoService currencyInfoService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
          log.info("Currency Info Job Started");

          var list = currencyInfoService.getCurrencyInfo();

          var file = currencyInfoService.writeCsvFromCurrencyInfo(list);

          currencyInfoService.sendCsvToFtpServer(file);

          log.info("Currency Info Job Ended");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
