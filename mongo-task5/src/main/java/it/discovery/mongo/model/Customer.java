package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends Person {

    private String login;

    private String password;

}
