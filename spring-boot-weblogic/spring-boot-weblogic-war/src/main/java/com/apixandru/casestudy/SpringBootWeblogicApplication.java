package com.apixandru.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@SpringBootApplication
@RestController
public class SpringBootWeblogicApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "weblogic-demo");
        SpringApplication.run(SpringBootWeblogicApplication.class, args);
    }


    @RequestMapping(value = "/current-date", method = RequestMethod.GET)
    public String sample() {
        return "current date: " + getCurrentDateFromDatabase();
    }

    private Date getCurrentDateFromDatabase() {
        return (Date) entityManager.createNativeQuery("select sysdate from dual")
                .getSingleResult();
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.properties("spring.config.name:weblogic-demo");
        return application.sources(SpringBootWeblogicApplication.class);
    }

}
