package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Pattern flattening
 */
public class Contact {
    private String phone;

    private String email;

    private String country;

    private String city;

    private String street;

    private String apt;
}
