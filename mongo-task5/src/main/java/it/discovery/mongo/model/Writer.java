package it.discovery.mongo.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "persons")
@TypeAlias("writer")
public class Writer extends Person {
    /**
     * Books that person has written
     */
    private List<BookInfo> books;
}
