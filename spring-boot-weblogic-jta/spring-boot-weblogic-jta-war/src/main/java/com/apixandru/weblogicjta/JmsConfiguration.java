package com.apixandru.weblogicjta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

@Configuration
class JmsConfiguration {

    @Bean
    JmsTemplate configureTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setDefaultDestinationName("topic1");
        template.setPubSubDomain(true);
        template.setMessageConverter(new Converter());
        return template;
    }

    @Bean
    DefaultMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("topic1");
        container.setPubSubDomain(true);
        container.setMessageListener(new Listener());
        return container;
    }

    private static class Listener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            try {
                System.out.println("Received: " + ((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Converter implements MessageConverter {

        @Override
        public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
            return session.createTextMessage(((SampleEntity) o).getRandomStuff());
        }

        @Override
        public Object fromMessage(Message message) throws JMSException, MessageConversionException {
            throw new UnsupportedOperationException();
        }

    }

}
