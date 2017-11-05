package com.apixandru.weblogicjta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SampleEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String randomStuff;

    public Long getId() {
        return id;
    }

    public String getRandomStuff() {
        return randomStuff;
    }

    public void setRandomStuff(String randomStuff) {
        this.randomStuff = randomStuff;
    }

}
