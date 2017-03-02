package com.apixandru.casestudy.hibernateversioning;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandru-Constantin Bledea
 * @since March 02, 2017
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
