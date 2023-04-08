package it.discovery.nosql.repository;

import it.discovery.mongo.model.Book;
import it.discovery.mongo.model.Person;
import it.discovery.mongo.model.Publisher;
import it.discovery.mongo.model.Review;
import it.discovery.mongo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void findWithReviews_returnsSingleBook() {
        Person author = new Person();
        author.setName("Gavin King");
        Publisher publisher = new Publisher();
        publisher.setName("Packt");

        Book book1 = new Book();
        book1.setNameEn("JPA");
        book1.setAuthor(author);
        book1.setPublisher(publisher);

        Book book2 = new Book();
        book2.setNameEn("Hibernate");
        book2.setAuthor(author);
        book2.setPublisher(publisher);

        Review review = new Review();
        review.setComment("good");
        review.setRate(10);
        book2.addReview(review);
        //bookRepository.saveAll(List.of(book1, book2));

        List<Book> books = bookRepository.findWithReviews();
        assertEquals(1, books.size());
        assertEquals("Hibernate", books.get(0).getNameEn());
    }

}
