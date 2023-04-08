package it.discovery.mongo;

import it.discovery.mongo.repository.BookRepository;
import it.discovery.mongo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Bean
    BookService bookService(BookRepository bookRepository) {
        return new BookService(bookRepository);
    }
}
