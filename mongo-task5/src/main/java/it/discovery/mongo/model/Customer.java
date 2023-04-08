package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "persons")
public class Customer extends Person {

    private String login;

    private String password;

}
