package com.apixandru.weblogicjta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;


@Component
class UsesXaTransactionServiceImpl implements UsesXaTransactionService {

    @Autowired
    private SampleEntityRepository repository;

    @Autowired
    private JmsService service;

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public String doJmsAndDb() {
        List<SampleEntity> all = repository.findAll();
        System.out.println("Processing " + all.size() + " entities in db and jms mode.");
        for (SampleEntity sampleEntity : all) {
            sampleEntity.setRandomStuff(sampleEntity.getRandomStuff() + ".");
            service.send(sampleEntity);
            repository.nativeUpdateName(sampleEntity.getRandomStuff(), sampleEntity.getId());
        }
        return "Processed " + all.size() + " entities in db and jms mode.";
    }

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public String doJms() {
        List<SampleEntity> all = repository.findAll();
        System.out.println("Processing " + all.size() + " entities in jms mode.");
        for (SampleEntity sampleEntity : all) {
            sampleEntity.setRandomStuff(sampleEntity.getRandomStuff() + ".");
            service.send(sampleEntity);
        }
        return "Processed " + all.size() + " entities in jms mode.";
    }

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public String doDb() {
        List<SampleEntity> all = repository.findAll();
        System.out.println("Processing " + all.size() + " entities in db mode.");
        for (SampleEntity sampleEntity : all) {
            sampleEntity.setRandomStuff(sampleEntity.getRandomStuff() + ".");
            repository.save(sampleEntity);
        }
        System.out.println("Done.");
        return "Processed " + all.size() + " entities in db mode.";
    }

}
