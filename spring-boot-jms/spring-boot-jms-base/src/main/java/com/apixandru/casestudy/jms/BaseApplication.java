package com.apixandru.casestudy.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author Alexandru-Constantin Bledea
 * @since Aug 18, 2016
 */
@EnableJms
@SpringBootApplication
public class BaseApplication implements CommandLineRunner {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.send(this::createMessage);
    }

    public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("Hello, sir!");
    }

    @Bean
    JmsTemplate jmsTemplate(@Autowired Destination defaultDestination, @Autowired ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(defaultDestination);
        return jmsTemplate;
    }

}
