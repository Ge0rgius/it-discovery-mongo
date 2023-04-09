package it.discovery.mongo.service;

import it.discovery.mongo.model.Book;
import it.discovery.mongo.model.Hit;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final MongoOperations mongoOperations;

    private final BinaryService binaryService;

    public List<Book> findByName(String name) {
        return mongoOperations.find(new Query(
                Criteria.where("translations.name").is(name)), Book.class);
    }

    //@Transactional
    public void saveBook(Book book, Path filePath) throws IOException {
        ObjectId objectId = binaryService.save(filePath, book.getAuthorId());
        book.setContentId(objectId.toHexString());

        //TODO remove file if save book operation fails
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
        GroupOperation groupOperation = Aggregation.group().sum("pages").as("totalPages");
        ProjectionOperation projectionOperation = Aggregation.project("totalPages").andExclude("_id");
        Aggregation aggregation = Aggregation.newAggregation(groupOperation, projectionOperation);

        AggregationResults<Document> books = mongoOperations.aggregate(aggregation, "books", Document.class);
        Document doc = books.getUniqueMappedResult();
        return Optional.ofNullable(doc.getInteger("totalPages")).orElse(0);
    }
}
