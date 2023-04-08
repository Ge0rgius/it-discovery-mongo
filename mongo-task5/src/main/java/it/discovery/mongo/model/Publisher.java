package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Book publisher
 */
@Getter
@Setter
public class Publisher extends BaseEntity {
    private String name;

    private List<BookInfo> books;

    private Contact contact;
}
