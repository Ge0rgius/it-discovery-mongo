package it.discovery.mongo.config;

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

    @Bean
    MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory dbFactory,
                                                    MongoOperations mongoOperations) {
        mongoOperations.getCollection("books")
                .createIndex(new Index()//.background()
                        .on("translations.$**", Sort.Direction.ASC).getIndexKeys());
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    protected String getDatabaseName() {
        return "warehouse";
    }

}
