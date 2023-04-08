package it.discovery.mongo.service;

import it.discovery.mongo.BaseMongoTest;
import it.discovery.mongo.model.Book;
import it.discovery.mongo.model.Translation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest extends BaseMongoTest {

    @Autowired
    BookService bookService;

    @Test
    void saveBook_findByName_success() {
        Book book = new Book();
        book.setTranslations(List.of(new Translation("JPA", "en")));
        bookService.saveBook(book);

        List<Book> books = bookService.findByName("JPA");
        assertEquals(1, books.size());
        assertEquals("JPA", books.get(0).getTranslations().get(0).name());
    }

}
