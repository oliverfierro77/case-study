package com.apixandru.casestudy.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Alexandru-Constantin Bledea
 * @since Oct 16, 2016
 */
public class TriggerRow {

    private final String name;
    private final String jobName;

    TriggerRow(String name, String jobName) {
        this.name = name;
        this.jobName = jobName;
    }

    TriggerRow(ResultSet resultSet) throws SQLException {
        this(resultSet.getString("TRIGGER_NAME"), resultSet.getString("JOB_NAME"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TriggerRow that = (TriggerRow) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(jobName, that.jobName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jobName);
    }

    @Override
    public String toString() {
        return "TriggerRow{" +
                "name='" + name + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }

}
