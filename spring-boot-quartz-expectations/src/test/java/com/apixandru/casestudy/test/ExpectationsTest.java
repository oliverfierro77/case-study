package com.apixandru.casestudy.test;

import com.apixandru.casestudy.app1.InitialApplication;
import com.apixandru.casestudy.app2.ChangedTriggerNameApplication;
import com.apixandru.casestudy.app3.AddAnotherTriggerApplication;
import com.apixandru.casestudy.app5.ChangeJobAndTriggerApplication;
import org.junit.Test;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Alexandru-Constantin Bledea
 * @since Oct 16, 2016
 */
public class ExpectationsTest extends AbstractDataSourceTest {

    private static final TriggerRow TRIGGER_FIRST_JOB_FIRST = new TriggerRow("First Trigger", "First Job");
    private static final TriggerRow TRIGGER_SECOND_JOB_FIRST = new TriggerRow("Second Trigger", "First Job");

    private static final TriggerRow TRIGGER_FIRST_JOB_SECOND = new TriggerRow("First Trigger", "Second Job");
    private static final TriggerRow TRIGGER_SECOND_JOB_SECOND = new TriggerRow("Second Trigger", "Second Job");

    private static final JobDetailRow JOB_DETAIL_FIRST = new JobDetailRow("First Job");
    private static final JobDetailRow JOB_DETAIL_SECOND = new JobDetailRow("Second Job");

    @Test
    public void afterChangedTriggerName() throws SQLException, IOException, SchedulerException {
        assertFirstJobWasSuccessful();

        execute(ChangedTriggerNameApplication.class);
        assertJobDetails(JOB_DETAIL_FIRST);
        assertTriggers(TRIGGER_SECOND_JOB_FIRST);
    }

    @Test
    public void afterAddedAnotherTrigger() throws SQLException, IOException, SchedulerException {
        assertFirstJobWasSuccessful();

        execute(AddAnotherTriggerApplication.class);
        assertJobDetails(JOB_DETAIL_FIRST);
        assertTriggers(TRIGGER_FIRST_JOB_FIRST, TRIGGER_SECOND_JOB_FIRST);
    }

    @Test
    public void afterChangedJobName() throws SQLException, IOException, SchedulerException {
        assertFirstJobWasSuccessful();

        execute(AddAnotherTriggerApplication.class);
        assertJobDetails(JOB_DETAIL_SECOND);
        assertTriggers(TRIGGER_FIRST_JOB_SECOND);
    }

    @Test
    public void afterChangedJobAndTriggerName() throws SQLException, IOException, SchedulerException {
        assertFirstJobWasSuccessful();

        execute(ChangeJobAndTriggerApplication.class);
        assertJobDetails(JOB_DETAIL_SECOND);
        assertTriggers(TRIGGER_SECOND_JOB_SECOND);
    }

    private void assertFirstJobWasSuccessful() throws SQLException, SchedulerException {
        execute(InitialApplication.class);
        assertJobDetails(JOB_DETAIL_FIRST);
        assertTriggers(TRIGGER_FIRST_JOB_FIRST);
    }

}

