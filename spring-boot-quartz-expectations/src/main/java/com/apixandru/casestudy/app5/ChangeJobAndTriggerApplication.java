package com.apixandru.casestudy.app5;

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
@SpringBootApplication(scanBasePackages = "com.apixandru.casestudy.app5")
public class ChangeJobAndTriggerApplication extends BaseSchedulerApplication {

    @Bean
    JobDetailFactoryBean jobFactoryBean() {
        return Components.createSecondJob();
    }

    @Bean
    SimpleTriggerFactoryBean triggerFactoryBean(JobDetailFactoryBean jobFactoryBean) {
        return Components.createSecondTrigger(jobFactoryBean);
    }

}
