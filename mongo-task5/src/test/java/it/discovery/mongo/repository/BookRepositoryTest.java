package it.discovery.mongo.repository;

import it.discovery.mongo.BaseMongoTest;
import it.discovery.mongo.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryTest extends BaseMongoTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Test
    void findWithReviews_returnsSingleBook() {
        Writer author = new Writer();
        author.setName("Gavin King");
        Publisher publisher = new Publisher();
        publisher.setName("Packt");

        mongoOperations.save(publisher);

        Book book1 = new Book();
        book1.setTranslations(List.of(new Translation("JPA", "en")));
        book1.setAuthorId(author.getId());
        book1.setPublisher(publisher);

        Book book2 = new Book();
        book2.setTranslations(List.of(new Translation("Hibernate", "en")));
        book2.setAuthorId(author.getId());
        book2.setPublisher(publisher);

        Review review = new Review();
        review.setComment("good");
        review.setRate(10);
        book2.addReview(review);
        bookRepository.saveAll(List.of(book1, book2));

        List<Book> books = bookRepository.findWithReviews();
        assertEquals(1, books.size());
        assertEquals("Hibernate", books.get(0).getTranslations().get(0).name());
    }

    @Test
    void findByNameAndLocale_returnsSingleBook() {
        Book book1 = new Book();
        book1.setTranslations(List.of(new Translation("Mongo", "en")));

        bookRepository.save(book1);

        List<Book> books = bookRepository.findByName("Mongo", "en");
        assertEquals(1, books.size());
        assertEquals("Mongo", books.get(0).getTranslations().get(0).name());
    }

}
