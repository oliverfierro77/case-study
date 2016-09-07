package com.apixandru.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 09, 2016
 */
@RestController
public class JmsController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "/send/{message}", method = RequestMethod.GET)
    public void send(@PathVariable String message) {
        jmsTemplate.send(session -> session.createTextMessage(message));
    }

}
