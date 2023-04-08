package it.discovery.mongo.model;

/**
 * Hit attribute, for example, request headers from the
 * browser.
 * Patter Invert relationship
 *
 * @author User
 */
public record HitAttribute(String name, String value) {

}
