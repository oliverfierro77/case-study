package com.apixandru.casestudy;

import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexandru-Constantin Bledea
 * @since July 30, 2016
 */
@Repository
public class EmployeeSearchImpl implements EmployeeSearch {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<EmployeeView> findEmployeesByName(String name) {
        // this didn't work prior to hibernate 5
        ProcedureCall call = sessionFactory.getCurrentSession()
                .getNamedProcedureCall("filter-employees");

        call.getParameterRegistration("name")
                .bindValue(name);

        return ((ResultSetOutput) call.getOutputs().getCurrent()).getResultList();
    }

}
