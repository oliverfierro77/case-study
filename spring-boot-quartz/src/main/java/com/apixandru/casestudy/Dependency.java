package com.apixandru.casestudy;

import org.springframework.stereotype.Component;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 23, 2016
 */
@Component
public class Dependency {

    public void takeYourTime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
