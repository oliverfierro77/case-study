package com.apixandru.casestudy;

import java.util.List;

/**
 * @author Alexandru-Constantin Bledea
 * @since July 30, 2016
 */
public interface EmployeeSearch {

    List<EmployeeView> findEmployeesByName(String name);

}
