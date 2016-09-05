package com.apixandru.casestudy.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
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

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

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

    @Bean
    Destination defaultDestination() {
        return new ActiveMQQueue("TEMP_TOPIC");
    }

    private ActiveMQConnectionFactory configureActiveMQ() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return activeMQConnectionFactory;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(configureActiveMQ());
        cachingConnectionFactory.setSessionCacheSize(100);
        return cachingConnectionFactory;
    }

}
