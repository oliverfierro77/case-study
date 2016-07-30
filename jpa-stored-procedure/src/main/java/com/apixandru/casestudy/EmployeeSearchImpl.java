package com.apixandru.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Alexandru-Constantin Bledea
 * @since July 30, 2016
 */
@Repository
public class EmployeeSearchImpl implements EmployeeSearch {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeSearchImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmployeeView> findEmployeesByName(String name) {
        // this didn't work prior to hibernate 5
        return this.entityManager.createNamedStoredProcedureQuery("filter-employees")
                .setParameter("name", name)
                .getResultList();
    }

}
