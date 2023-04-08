package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Book in a library
 */
@Getter
@Setter
@Document(collection = "books")
public class Book extends BaseEntity {
    private List<Translation> translations;

    private Complexity complexity;

    private String authorId;

    private String publisherId;

    /**
     * Publishing year
     */
    private int year;

    /**
     * Total number of pages
     */
    private int pages;

    /**
     * Pattern Embedding
     */
    private List<Review> reviews;

    private List<Hit> recentHits;

    private String contents;

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }
}
