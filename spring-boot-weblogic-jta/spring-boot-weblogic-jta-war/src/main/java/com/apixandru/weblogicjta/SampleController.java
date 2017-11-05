package com.apixandru.weblogicjta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SampleController {

    @Autowired
    private UsesXaTransactionService service;

    @GetMapping("/jms-and-db")
    public String jmsAndDb() {
        return service.doJmsAndDb();
    }

    @GetMapping("/db")
    public String db() {
        return service.doDb();
    }

    @GetMapping("/jms")
    public String jms() {
        return service.doJms();
    }

}