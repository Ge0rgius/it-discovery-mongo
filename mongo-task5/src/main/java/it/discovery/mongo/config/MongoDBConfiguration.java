package it.discovery.mongo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MongoDBConfiguration extends AbstractMongoClientConfiguration {

    @Autowired
    private MongoOperations mongoOperations;

    @Bean
    MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    protected String getDatabaseName() {
        return "warehouse";
    }

    @PostConstruct
    void setup() {
        mongoOperations.getCollection("books")
                .createIndex(new Index()//.background()
                        .on("translations.name", Sort.Direction.ASC)
                        .on("translations.language", Sort.Direction.ASC).getIndexKeys());
    }
}
