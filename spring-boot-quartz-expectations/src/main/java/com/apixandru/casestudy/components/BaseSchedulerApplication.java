package com.apixandru.casestudy.components;

import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * @author Alexandru-Constantin Bledea
 * @since Oct 16, 2016
 */
public abstract class BaseSchedulerApplication {

    private static Properties extraQuartzProperties() {
        Properties props = new Properties();
        props.put("org.quartz.scheduler.instanceId", "AUTO");
        props.put("org.quartz.jobStore.isClustered", "true");
        return props;
    }

    @Bean
    SchedulerFactoryBean schedulerFactoryBean(List<SimpleTriggerFactoryBean> triggerFactoryBeans, DataSource dataSource) {
        SimpleTrigger[] triggers = triggerFactoryBeans.stream()
                .map(SimpleTriggerFactoryBean::getObject)
                .sorted(this::compare)
                .toArray(SimpleTrigger[]::new);

        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(triggers);
        bean.setSchedulerName("Demo Scheduler");
        bean.setOverwriteExistingJobs(true);
        bean.setDataSource(dataSource);
        bean.setQuartzProperties(extraQuartzProperties());

        return bean;
    }


    /**
     * adding this just to make sure that the error is always reproducible
     */
    private int compare(SimpleTrigger firstTrigger, SimpleTrigger secondTrigger) {
        return secondTrigger.toString().compareTo(firstTrigger.toString());
    }

}
