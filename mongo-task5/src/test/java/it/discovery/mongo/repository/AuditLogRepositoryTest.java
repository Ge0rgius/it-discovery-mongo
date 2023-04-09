package it.discovery.mongo.repository;

import it.discovery.mongo.BaseMongoTest;
import it.discovery.mongo.model.AuditLog;
import it.discovery.mongo.model.Book;
import it.discovery.mongo.model.Translation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuditLogRepositoryTest extends BaseMongoTest {

    @Autowired
    AuditLogRepository auditLogRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void findByClzAndEntityId_saveBook_auditLogPresent() {
        Book book = new Book();
        book.setTranslations(List.of(new Translation("JPA", "en")));
        bookRepository.save(book);

        List<AuditLog> logs = auditLogRepository.findByClzAndEntityId(Book.class.getSimpleName(), book.getId());
        assertEquals(1, logs.size());
        assertEquals(book.getId(), logs.get(0).getEntityId());
        assertNotNull(logs.get(0).getEntity());
    }
}