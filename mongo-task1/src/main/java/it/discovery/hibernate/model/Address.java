package it.discovery.hibernate.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String country, String city, String street, String apt) {
}
