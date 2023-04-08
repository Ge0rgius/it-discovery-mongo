package it.discovery.mongo.repository;

import it.discovery.mongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * Returns all the books with exact name and
     * specified locale
     *
     * @param name
     * @param locale
     * @return
     */
//    List<Book> findByName(String name, String locale);

    /**
     * Returns all the books with exact name irregardless of locale
     *
     * @param name
     * @return
     */
//    List<Book> findByName(String name);

    /**
     * Returns all the books that has at least one review
     *
     * @return
     */
//    List<Book> findWithReviews();

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
