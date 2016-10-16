package com.apixandru.casestudy.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Alexandru-Constantin Bledea
 * @since Oct 16, 2016
 */
public class JobDetailRow {

    private final String jobName;

    JobDetailRow(ResultSet resultSet) throws SQLException {
        this(resultSet.getString("JOB_NAME"));
    }

    JobDetailRow(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobDetailRow that = (JobDetailRow) o;
        return Objects.equals(jobName, that.jobName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName);
    }

    @Override
    public String toString() {
        return "JobDetailRow{" +
                "jobName='" + jobName + '\'' +
                '}';
    }

}
