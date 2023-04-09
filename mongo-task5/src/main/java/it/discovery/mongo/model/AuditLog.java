package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "audit")
@CompoundIndex(def = "{clz: 1, entityId: 1}")
public class AuditLog extends BaseEntity {

    private String clz;

    private String entityId;

    private BaseEntity entity;
}
