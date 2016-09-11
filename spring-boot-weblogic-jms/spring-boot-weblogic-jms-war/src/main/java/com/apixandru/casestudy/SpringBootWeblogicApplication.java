package com.apixandru.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;

// we don't want ActiveMQAutoConfiguration to configure the connection for us, with jndi or dev config
@SpringBootApplication(exclude = ActiveMQAutoConfiguration.class)
@RestController
public class SpringBootWeblogicApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWeblogicApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWeblogicApplication.class);
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName("jms.myQueue");
        return jmsTemplate;
    }

}
