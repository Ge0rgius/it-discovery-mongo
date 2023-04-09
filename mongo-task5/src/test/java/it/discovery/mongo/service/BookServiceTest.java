package it.discovery.mongo.service;

import it.discovery.mongo.BaseMongoTest;
import it.discovery.mongo.model.Book;
import it.discovery.mongo.model.Hit;
import it.discovery.mongo.model.Translation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest extends BaseMongoTest {

    @Autowired
    BookService bookService;

    @Autowired
    MongoOperations mongoOperations;

    @Test
    void saveBook_findByName_success() {
        Book book = new Book();
        book.setTranslations(List.of(new Translation("JPA", "en")));
        bookService.saveBook(book);

        List<Book> books = bookService.findByName("JPA");
        assertEquals(1, books.size());
        assertEquals("JPA", books.get(0).getTranslations().get(0).name());
    }

    @Test
    //TODO use MongoDBContainer for this test
    void saveHit_hitAddedToRecentHits() {
        Book book = new Book();
        book.setTranslations(List.of(new Translation("JPA", "en")));
        bookService.saveBook(book);

        Hit hit = new Hit();
        hit.setBookId(book.getId());
        hit.setBrowser("Chrome");
        bookService.saveHit(hit);

        assertNotNull(hit.getId());
        Hit hit2 = mongoOperations.findOne(new Query(Criteria.where("_id").is(hit.getId())), Hit.class);
        assertNotNull(hit2);
        Book book2 = mongoOperations.findOne(new Query(Criteria.where("_id").is(book.getId())), Book.class);
        assertNotNull(book2);
        assertTrue(book2.getRecentHits().stream().anyMatch(current -> current.getId().equals(hit.getId())));
    }

    @Test
    void findTotalPages_success() {
        Book book = new Book();
        book.setTranslations(List.of(new Translation("JPA", "en")));
        book.setPages(100);
        bookService.saveBook(book);

        Book book1 = new Book();
        book1.setTranslations(List.of(new Translation("Mongo", "en")));
        book1.setPages(200);
        bookService.saveBook(book1);
        int totalPages = bookService.findTotalPages();
        assertEquals(300, totalPages);
    }
}
