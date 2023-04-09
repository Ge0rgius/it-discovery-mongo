package it.discovery.mongo.repository;

import it.discovery.mongo.model.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    List<AuditLog> findByClzAndEntityId(String clz, String entityId);
}
