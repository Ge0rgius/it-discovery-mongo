package it.discovery.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private final static int MAX_HITS = 100;

    private List<Translation> translations;

    private Complexity complexity;

    private String authorId;

    //private String publisherId;

    @DBRef(lazy = true)
    private Publisher publisher;

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

    private String contentId;

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void addHit(Hit hit) {
        if (recentHits == null) {
            recentHits = new ArrayList<>();
        }
        if (recentHits.size() > MAX_HITS) {
            //FIXME what is better ArrayList or LinkedList
            recentHits.remove(0);
        }
        recentHits.add(hit);
    }
}
