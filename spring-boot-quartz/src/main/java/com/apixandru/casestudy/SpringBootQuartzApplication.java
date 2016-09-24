package com.apixandru.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.web.WebApplicationInitializer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
public class SpringBootQuartzApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @PersistenceContext
    private EntityManager dataSource;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuartzApplication.class, args);
    }

    @Bean
    JobDetailFactoryBean jobFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setDurability(true);
        bean.setName("Sampler");
        bean.setJobClass(SampleJob.class);
        return bean;
    }

    @Bean
    SimpleTriggerFactoryBean triggerFactoryBean(JobDetailFactoryBean jobFactoryBean) {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setName("Sampler Trigger");
        bean.setRepeatInterval(20_000);
        bean.setJobDetail(jobFactoryBean.getObject());
        return bean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean triggerFactoryBean, DataSource dataSource, Dependency dependency) {
        Properties props = new Properties();
        props.put("org.quartz.scheduler.instanceId", "AUTO");
        props.put("org.quartz.jobStore.isClustered", "true");

        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(triggerFactoryBean.getObject());
        bean.setSchedulerName("Demo Scheduler");
        bean.setSchedulerContextAsMap(Collections.singletonMap("dependency", dependency));
        bean.setOverwriteExistingJobs(true);
        bean.setDataSource(dataSource);
        bean.setQuartzProperties(props);

        return bean;
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootQuartzApplication.class);
    }

}
