package com.apixandru.casestudy.hibernateversioning;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

import static org.hibernate.annotations.GenerationTime.ALWAYS;

/**
 * @author Alexandru-Constantin Bledea
 * @since March 02, 2017
 */
@Entity
@OptimisticLocking
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Generated(ALWAYS)
    @Version
    private Date lastModifiedDate;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

}
