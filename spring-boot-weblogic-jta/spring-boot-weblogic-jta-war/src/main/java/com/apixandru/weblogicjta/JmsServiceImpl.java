package com.apixandru.weblogicjta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Component
class JmsServiceImpl implements JmsService {

    @Autowired
    private JmsTemplate template;

    @Override
    @Transactional(propagation = MANDATORY)
    public void send(SampleEntity entity) {
        template.convertAndSend(entity);
    }

}
