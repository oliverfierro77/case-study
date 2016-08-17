package com.apixandru.casestudy.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * @author Alexandru-Constantin Bledea
 * @since Aug 18, 2016
 */
@Configuration
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
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
