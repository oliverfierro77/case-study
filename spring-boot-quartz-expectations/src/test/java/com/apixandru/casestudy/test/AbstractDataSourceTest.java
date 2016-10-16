package com.apixandru.casestudy.test;

import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.ObjectAssert;
import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.After;
import org.junit.Before;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alexandru-Constantin Bledea
 * @since Oct 16, 2016
 */
abstract class AbstractDataSourceTest {

    private final List<Connection> connections = new ArrayList<>();
    private DataSource dataSource;

    static void execute(Class<?> applicationClass) throws SchedulerException {
        try (ConfigurableApplicationContext application = SpringApplication.run(applicationClass)) {
            application.getBean(SchedulerFactoryBean.class).getScheduler().shutdown(true);
        }
    }

    @Before
    public void setup() throws IOException, SQLException {
        JDBCDataSource jdbcDataSource = new JDBCDataSource();
        jdbcDataSource.setURL("jdbc:hsqldb:file:target/spring/testdb");
        dataSource = jdbcDataSource;
        createTables();
    }

    @After
    public void teardown() throws IOException {
        connections.forEach(this::quietlyClose);
    }

    private void quietlyClose(Connection connection) {
        try {
            if (connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException, IOException {
        try (Connection connection = getConnection()) {
            byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/quartz.sql"));
            String string = new String(bytes);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(string);
            }
            connection.commit();
        }
    }

    private <T> List<T> getTables(String table, SqlConverter<T> converter) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM " + table, (rs, i) -> converter.convert(rs));
    }

    private Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connections.add(connection);
        return connection;
    }

    void assertJobDetails(JobDetailRow... jobDetailRows) throws SQLException {
        assertTables("qrtz_job_details", JobDetailRow::new)
                .containsOnly(jobDetailRows);
    }

    void assertTriggers(TriggerRow... triggerRows) throws SQLException {
        assertTables("qrtz_triggers", TriggerRow::new)
                .containsOnly(triggerRows);
    }

    private <T> AbstractListAssert<?, List<? extends T>, T, ObjectAssert<T>> assertTables(String tableName, SqlConverter<T> mapper) throws SQLException {
        return assertThat(getTables(tableName, mapper));
    }

    interface SqlConverter<T> {
        T convert(ResultSet resultSet) throws SQLException;
    }

}
