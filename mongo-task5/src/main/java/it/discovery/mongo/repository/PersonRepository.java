package it.discovery.mongo.repository;

import it.discovery.mongo.model.Person;

import java.util.List;

public interface PersonRepository {

    /**
     * Returns all the persons sorted by name
     *
     * @return
     */
    List<Person> findByOrderByNameAsc();
}
