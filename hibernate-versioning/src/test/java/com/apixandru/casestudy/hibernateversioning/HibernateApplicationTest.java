package com.apixandru.casestudy.hibernateversioning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Alexandru-Constantin Bledea
 * @since March 02, 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateApplicationTest {

    @Autowired
    private BookRepository bookRepository;

    private Date latestModifiedDate;
    private Book book = new Book();

    @Test
    public void contextLoads() {
        setBookNameAndSave("Book 1");
        assertLastModifiedDateChanged();

        setBookNameAndSave("Book 2");
        assertLastModifiedDateChanged();

        setBookNameAndSave("Book 2");
        assertSameLastModifiedDate();
    }

    private void setBookNameAndSave(String name) {
        book.setName(name);
        book = bookRepository.save(book);
    }

    private void assertLastModifiedDateChanged() {
        Date lastModifiedDate = book.getLastModifiedDate();
        assertNotEquals(latestModifiedDate, lastModifiedDate);
        latestModifiedDate = lastModifiedDate;
    }

    private void assertSameLastModifiedDate() {
        Date lastModifiedDate = book.getLastModifiedDate();
        assertEquals(latestModifiedDate, lastModifiedDate);
        latestModifiedDate = lastModifiedDate;
    }

}
