package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Deprecated
public class Address {
    private String country;

    private String city;

    private String street;

    private String apt;
}
