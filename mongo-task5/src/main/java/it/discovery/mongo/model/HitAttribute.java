package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Hit attribute, for example, request headers from the
 * browser
 * @author User
 *
 */
public class HitAttribute {

    private String name;

    private String value;

    private Hit hit;

}
