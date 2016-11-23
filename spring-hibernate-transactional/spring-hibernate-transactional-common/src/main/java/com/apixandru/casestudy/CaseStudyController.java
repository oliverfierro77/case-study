package com.apixandru.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexandru-Constantin Bledea
 * @since Nov 23, 2016
 */
@RestController
public class CaseStudyController {

    @Autowired
    private ExampleService service;

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public String ok() {
        return String.valueOf(service.executeOkQuery());
    }

    @RequestMapping(value = "/not-ok", method = RequestMethod.GET)
    public String notOk() {
        return String.valueOf(service.executeBadInsert());
    }

    @RequestMapping(value = "/combo", method = RequestMethod.GET)
    public String combo() {
        try {
            return notOk();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ok();
        }
    }

}
