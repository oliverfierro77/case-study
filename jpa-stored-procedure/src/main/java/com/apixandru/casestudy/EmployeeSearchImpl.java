package com.apixandru.casestudy;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Alexandru-Constantin Bledea
 * @since July 30, 2016
 */
@Repository
public class EmployeeSearchImpl implements EmployeeSearch {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<EmployeeView> findEmployeesByName(String name) {
        // this didn't work prior to hibernate 5
        return this.entityManager.createNamedStoredProcedureQuery("filter-employees")
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmployeeView> findEmployeesByName(String firstName, String lastName) {
        // nullable parameters didn't work prior to 5.1
        return this.entityManager.createNamedStoredProcedureQuery("search-employees")
                .setParameter("in_first_name", firstName)
                .setParameter("in_last_name", lastName)
                .getResultList();
    }

}
