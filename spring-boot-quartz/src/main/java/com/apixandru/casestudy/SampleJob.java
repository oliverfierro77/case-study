package com.apixandru.casestudy;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 23, 2016
 */
@DisallowConcurrentExecution
public class SampleJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(SampleJob.class);

    private Dependency dependency;

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Starting job");
        dependency.takeYourTime();
        log.info("Job done");
    }

}
