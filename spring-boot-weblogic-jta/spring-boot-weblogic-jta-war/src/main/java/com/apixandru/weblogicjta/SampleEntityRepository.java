package com.apixandru.weblogicjta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

interface SampleEntityRepository extends JpaRepository<SampleEntity, Long> {

    @Transactional(propagation = MANDATORY)
    @Query("update SampleEntity set random_stuff=?1 where id=?2")
    @Modifying
    void updateName(String name, Long id);

    @Transactional(propagation = MANDATORY)
    @Query(nativeQuery = true, value = "update Sample_Entity set random_stuff=?1 where id=?2")
    @Modifying
    void nativeUpdateName(String name, Long id);

}

