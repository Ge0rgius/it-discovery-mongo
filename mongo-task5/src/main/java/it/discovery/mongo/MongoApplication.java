package it.discovery.mongo;

import it.discovery.mongo.config.AuditLogCallback;
import it.discovery.mongo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;

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

    @Bean
    AuditLogCallback auditLogCallback(ApplicationContext applicationContext) {
        return new AuditLogCallback(applicationContext);
    }

    @Bean
    MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory dbFactory,
                                                    MongoOperations mongoOperations) {
        mongoOperations.getCollection("books")
                .createIndex(new Index()//.background()
                        .on("translations.$**", Sort.Direction.ASC).getIndexKeys());
        return new MongoTransactionManager(dbFactory);
    }

}
