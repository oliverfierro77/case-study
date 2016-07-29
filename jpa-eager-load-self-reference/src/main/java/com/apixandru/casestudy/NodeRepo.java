package com.apixandru.casestudy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexandru-Constantin Bledea
 * @since Jul 29, 2016
 */
@Repository
public interface NodeRepo extends JpaRepository<Node, Long> {
}
