package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract person (writer or consumer).
 * Pattern polymorphic
 *
 * @author admin
 */
@Getter
@Setter
public abstract class Person extends BaseEntity {
    private String name;

    private Contact contact;
}
