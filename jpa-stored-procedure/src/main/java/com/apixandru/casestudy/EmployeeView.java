package com.apixandru.casestudy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Alexandru-Constantin Bledea
 * @since July 30, 2016
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "filter-employees",
                procedureName = "filter_employees",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "ref_cursor", type = void.class)
                },
                resultClasses = EmployeeView.class),
        @NamedStoredProcedureQuery(name = "search-employees",
                procedureName = "SEARCH_EMPLOYEES",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_last_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_first_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "ref_cursor", type = void.class)
                },
                resultClasses = EmployeeView.class)
})
@Entity
public class EmployeeView implements Serializable {

    @Id
    private Long id;

    private String lastName;
    private String firstName;
    private Date birthDate;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "EmployeeView{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
