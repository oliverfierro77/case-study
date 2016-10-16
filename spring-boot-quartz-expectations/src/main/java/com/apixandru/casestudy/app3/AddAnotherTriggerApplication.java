package com.apixandru.casestudy.app3;

import com.apixandru.casestudy.components.BaseSchedulerApplication;
import com.apixandru.casestudy.components.Components;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author Alexandru-Constantin Bledea
 * @since Oct 16, 2016
 */
@SpringBootApplication(scanBasePackages = "com.apixandru.casestudy.app3")
public class AddAnotherTriggerApplication extends BaseSchedulerApplication {

    @Bean
    JobDetailFactoryBean jobFactoryBean() {
        return Components.createFirstJob();
    }

    @Bean
    SimpleTriggerFactoryBean triggerFactoryBean(JobDetailFactoryBean jobFactoryBean) {
        return Components.createFirstTrigger(jobFactoryBean);
    }

    @Bean
    SimpleTriggerFactoryBean triggerFactoryBean2(JobDetailFactoryBean jobFactoryBean) {
        return Components.createSecondTrigger(jobFactoryBean);
    }

}
