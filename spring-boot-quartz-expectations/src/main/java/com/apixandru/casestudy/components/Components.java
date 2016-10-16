package com.apixandru.casestudy.components;

import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author Alexandru-Constantin Bledea
 * @since Oct 16, 2016
 */
public class Components {

    private static JobDetailFactoryBean createJob(String name) {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();

        // (1) This is required strictly because the jobs are scheduled at a separate time from triggers
        bean.setDurability(true);

        bean.setName(name);
        bean.setJobClass(SampleJob.class);
        return bean;
    }

    private static SimpleTriggerFactoryBean createTrigger(JobDetailFactoryBean jobFactoryBean, String name) {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setName(name);
        bean.setRepeatInterval(20_000);
        bean.setJobDetail(jobFactoryBean.getObject());
        return bean;
    }

    public static JobDetailFactoryBean createFirstJob() {
        return createJob("First Job");
    }

    public static JobDetailFactoryBean createSecondJob() {
        return createJob("Second Job");
    }

    public static SimpleTriggerFactoryBean createFirstTrigger(JobDetailFactoryBean jobFactoryBean) {
        return createTrigger(jobFactoryBean, "First Trigger");
    }

    public static SimpleTriggerFactoryBean createSecondTrigger(JobDetailFactoryBean jobFactoryBean) {
        return createTrigger(jobFactoryBean, "Second Trigger");
    }

}
