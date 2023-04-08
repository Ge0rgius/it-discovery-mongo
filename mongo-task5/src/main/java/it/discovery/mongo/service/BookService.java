package it.discovery.mongo.service;

import it.discovery.mongo.model.Book;
import it.discovery.mongo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

//    public List<Book> findByName(String name) {
//        return bookRepository.findByName(name);
//    }

    public void saveBook(Book book) {
        bookRepository.save(book);
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
