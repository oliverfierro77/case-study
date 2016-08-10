package com.apixandru.casestudy;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Alexandru-Constantin Bledea
 * @since July 30, 2016
 */
@EnableJpaRepositories
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private EmployeeSearch employeeSearch;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // run the sample sql to create the procedure, table and data before running this

        employeeSearch.findEmployeesByName("")
                .forEach(System.out::println);
    }

    @Bean(destroyMethod = "")
    public SessionFactory sessionFactory(@Autowired HibernateEntityManagerFactory factory) {
        return factory.getSessionFactory();
    }

}
