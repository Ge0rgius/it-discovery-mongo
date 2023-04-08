package it.discovery.mongo;

import it.discovery.mongo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
@EnableMongoAuditing
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Bean
    BookService bookService(MongoOperations mongoOperations) {
        return new BookService(mongoOperations);
    }
}
