package com.apixandru.casestudy.hibernateversioning;

import javax.persistence.PreUpdate;

/**
 * @author Alexandru-Constantin Bledea
 * @since March 07, 2017
 */
public class LastModifiedByListener {

    private static int i;

    @PreUpdate
    public void update(Book entity) {
        entity.setLastModifiedBy(String.valueOf(i++));
    }

}
