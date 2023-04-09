package it.discovery.mongo.repository;

import it.discovery.mongo.BaseMongoTest;
import it.discovery.mongo.model.Customer;
import it.discovery.mongo.model.Writer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WriterRepositoryTest extends BaseMongoTest {

    @Autowired
    WriterRepository writerRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Test
    void findAll_returnOnlyWriters() {
        Writer writer = new Writer();
        writer.setName("Josh");

        writerRepository.save(writer);

        Customer customer = new Customer();
        customer.setLogin("admin");
        customer.setPassword("admin");

        mongoOperations.save(customer);

        List<Writer> writers = writerRepository.findAll();
        assertEquals(1, writers.size());
        assertEquals(writer.getName(), writers.get(0).getName());
    }

}