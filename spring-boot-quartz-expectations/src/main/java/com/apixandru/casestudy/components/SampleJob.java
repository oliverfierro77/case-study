package com.apixandru.casestudy.components;

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

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Triggered by {}", jobExecutionContext.getTrigger());
    }

}
