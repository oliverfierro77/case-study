package com.apixandru.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.JndiDestinationResolver;

import javax.jms.ConnectionFactory;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 09, 2016
 */
@Configuration
public class JmsConfiguration {

    @Bean
    JmsTemplate jmsTemplate(@Autowired ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName("jms.myQueue");
        jmsTemplate.setDestinationResolver(new JndiDestinationResolver());
        return jmsTemplate;
    }


}
