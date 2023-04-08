package it.discovery.mongo.repository;

import it.discovery.mongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * Returns all the books with exact name and
     * specified locale
     *
     * @param name
     * @param language
     * @return
     */
    @Query("{ 'translations.name': ?0, 'translations.language': ?1}")
    List<Book> findByName(String name, String language);

    /**
     * Returns all the books with exact name irregardless of locale
     *
     * @param name
     * @return
     */
    List<Book> findByTranslations_Name(String name);

    /**
     * Returns all the books that has at least one review
     *
     * @return
     */
    @Query("{reviews: {$exists: true, $ne: [], $ne: null}}")
    List<Book> findWithReviews();

    /**
     * Returns all the books where number of pages is greater than pages parameter
     *
     * @param pages
     * @return
     */
    List<Book> findByPagesGreaterThan(int pages);

    /**
     * Returns all the books of the specified author
     * ignore them
     *
     * @param name
     * @return
     */
//    List<Book> findAllByAuthor_Name(String name);


}
