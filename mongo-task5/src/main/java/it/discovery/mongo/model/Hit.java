package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Hit extends BaseEntity {

    private String ip;

    private String browser;

    /**
     * Details on how(where) user hit this link
     */
    private String origin;

    private Book book;

    private List<HitAttribute> attributes;
}
