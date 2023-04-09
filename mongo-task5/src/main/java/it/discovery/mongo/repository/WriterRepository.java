package it.discovery.mongo.repository;

import it.discovery.mongo.model.Person;
import it.discovery.mongo.model.Writer;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WriterRepository extends MongoRepository<Writer, String> {

    /**
     * Returns all the persons sorted by name
     *
     * @return
     */
    //TODO add discriminator
    List<Person> findByOrderByNameAsc();

    @Override
    default List<Writer> findAll() {
        Writer probe = new Writer();
        return findAll(Example.of(probe));
    }
}
