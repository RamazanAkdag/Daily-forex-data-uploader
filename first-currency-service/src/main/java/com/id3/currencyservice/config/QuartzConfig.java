package com.id3.currencyservice.config;


import com.id3.currencyservice.quartz.AutowiringSpringBeanJobFactory;
import com.id3.currencyservice.quartz.CurrencyInfoJob;
import com.id3.currencyservice.quartz.FtpListenerJob;
import org.quartz.*;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.quartz.*;

import java.util.Map;


@Configuration

public class QuartzConfig {
    @Value("${cron.expression}")
    private String cronExpression;
    @Bean(name = "currencyInfoJobJob")
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(CurrencyInfoJob.class);
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean(name = "ftpListenerJobJob")
    public JobDetailFactoryBean jobDetail2() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(FtpListenerJob.class);
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTrigger trigger(@Qualifier("ftpListenerJobJob") JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(0))
                .build();
    }

    @Bean
    public CronTriggerFactoryBean cronTrigger(@Qualifier("currencyInfoJobJob") JobDetail jobDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression); // Her gün saat 9:00'da
        return factoryBean;
    }



   /* @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobFactory(jobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);

        return schedulerFactory;
    }*/

    @Bean
    public SchedulerFactoryBean scheduler(Map<String, JobDetail> jobMap, Map<String, Trigger> triggers) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobFactory(springBeanJobFactory());
        JobDetail[] jobs = jobMap.values().toArray(new JobDetail[0]);
        Trigger[] tr = triggers.values().toArray(new Trigger[0]);
        schedulerFactory.setJobDetails(jobs);
        schedulerFactory.setTriggers(tr);

        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        return jobFactory;
    }




}
