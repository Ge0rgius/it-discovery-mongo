package it.discovery.mongo.repository;

import it.discovery.mongo.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher, String> {

}
