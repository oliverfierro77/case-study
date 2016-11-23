package com.apixandru.casestudy;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Alexandru-Constantin Bledea
 * @since Nov 22, 2016
 */
@Component
public class ExampleServiceImpl implements ExampleService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Object executeBadInsert() {
        System.out.println(TransactionAspectSupport.currentTransactionStatus());
        return entityManager.createNativeQuery("select 1 from duals")
                .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Object executeOkQuery() {
        System.out.println(TransactionAspectSupport.currentTransactionStatus());
        return entityManager.createNativeQuery("select 1 from dual")
                .getSingleResult();
    }

}
