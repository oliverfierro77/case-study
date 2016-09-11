package com.apixandru.casestudy;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 09, 2016
 */
@Configuration
class JmsDevConfiguration {

    @Value("${demoapp-dev.jms.activemq.url}")
    private String customUrl;

    @Bean
    ConnectionFactory customConnection() {
        return new ActiveMQConnectionFactory(customUrl);
    }

}
