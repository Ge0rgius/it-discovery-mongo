package it.discovery.mongo.service;

import it.discovery.mongo.model.Book;
import it.discovery.mongo.model.Hit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final MongoOperations mongoOperations;

    public List<Book> findByName(String name) {
        return mongoOperations.find(new Query(
                Criteria.where("translations.name").is(name)), Book.class);
    }

    @Transactional
    public void saveBook(Book book) {
        mongoOperations.save(book);
    }

    @Transactional
    public void saveHit(Hit hit) {
        mongoOperations.save(hit);

        Book book = mongoOperations.findOne(new Query(Criteria.where("_id").is(hit.getBookId())), Book.class);
        if (book != null) {
            book.addHit(hit);
            mongoOperations.save(book);
        }
    }

    /**
     * Returns overall number of pages for all the books
     *
     * @return
     */
    public int findTotalPages() {
        //TODO implement
        return 0;
    }
}
