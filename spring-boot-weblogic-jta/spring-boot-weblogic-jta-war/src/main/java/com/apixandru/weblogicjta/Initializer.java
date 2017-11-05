package com.apixandru.weblogicjta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private SampleEntityRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        SampleEntity entity = new SampleEntity();
        entity.setRandomStuff("Blablabla");
        repository.saveAndFlush(entity);
    }
}
