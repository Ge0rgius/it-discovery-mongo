package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "hits")
public class Hit extends BaseEntity {

    private String ip;

    private String browser;

    /**
     * Details on how(where) user hit this link
     */
    private String origin;

    private String bookId;

    private List<HitAttribute> attributes;
}
