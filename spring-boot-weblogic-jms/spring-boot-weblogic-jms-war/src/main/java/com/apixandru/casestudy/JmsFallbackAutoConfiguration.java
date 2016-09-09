package com.apixandru.casestudy;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 09, 2016
 */
@Configuration
@AutoConfigureAfter(JndiConnectionFactoryAutoConfiguration.class)
@ConditionalOnMissingBean(ConnectionFactory.class)
@ConditionalOnClass(ConnectionFactory.class)
public class JmsFallbackAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ConnectionFactory fallbackConfiguration() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        System.out.println("Created: " + activeMQConnectionFactory);
        return activeMQConnectionFactory;
    }

}
