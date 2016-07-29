package com.apixandru.casestudy;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Alexandru-Constantin Bledea
 * @since Jul 29, 2016
 */
@Component
public class Salmonella {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Node> findAll() {
        return entityManager.createQuery("SELECT node FROM Node AS node LEFT JOIN FETCH node.children").getResultList();
    }

}
