package it.discovery.mongo.config;

import it.discovery.mongo.model.AuditLog;
import it.discovery.mongo.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveCallback;

@RequiredArgsConstructor
public class AuditLogCallback implements AfterSaveCallback<BaseEntity> {

    private MongoOperations mongoOperations;

    private final ApplicationContext applicationContext;

    @Override
    public BaseEntity onAfterSave(BaseEntity entity, Document document, String collection) {
        if (mongoOperations == null) {
            mongoOperations = applicationContext.getBean(MongoOperations.class);
        }
        if (entity instanceof AuditLog) {
            return entity;
        }

        AuditLog auditLog = new AuditLog();
        auditLog.setClz(entity.getClass().getSimpleName());
        auditLog.setEntityId(entity.getId());
        auditLog.setEntity(entity);

        mongoOperations.save(auditLog);

        return entity;
    }
}
