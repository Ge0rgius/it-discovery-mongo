package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends BaseEntity {

    private Book book;

    private Customer customer;

    private int amount;

}
