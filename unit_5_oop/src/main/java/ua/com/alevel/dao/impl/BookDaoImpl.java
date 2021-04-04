package ua.com.alevel.dao.impl;

import org.apache.log4j.Logger;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.HashSet;
import java.util.Set;

public class BookDaoImpl implements BookDao {

    private static BookDaoImpl instance;
    private Set<Book> books;
    private final Logger logger = Logger.getLogger(BookDaoImpl.class);

    private BookDaoImpl() {
        this.books = new HashSet<>();
    }

    public static BookDaoImpl getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }

    @Override
    public void clear() {
        logger.info("Clear books");
        books = new HashSet<>();
    }

    @Override
    public void add(Book book) {
        logger.info("Start creating book " + book.getId());
        books.add(book);
        logger.info("Finish creating book " + book.getId());
    }

    @Override
    public Set<Book> findAll() {
        logger.info("Finding all books");
        return books;
    }

    @Override
    public Book findById(int id) {
        logger.info("Finding book by id - " + id);
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public Book findByName(String name) {
        logger.info("Finding book by name - " + name);
        return books.stream()
                .filter(book -> book.getName() == name)
                .findFirst()
                .get();
    }

    @Override
    public Set<Book> findByAuthor(Author author) {
        logger.info("Start finding book by author - " + author.getId());
        Set<Book> result = new HashSet<>();
        for (Book book: books) {
            if (book.getAuthorsIds().contains(author.getId())) {
                result.add(book);
            }
        }
        logger.info("Finish finding book by author - " + author.getId());
        return result;
    }

    @Override
    public void update(Book book) {
        logger.info("Start updating book by id " + book.getId());
        Book current = findById(book.getId());
        current.setName(book.getName());
        current.setAuthorsIds(book.getAuthorsIds());
        logger.info("Finish updating book by id " + book.getId());
    }

    @Override
    public void deleteById(int id) {
        logger.info("Deleting book by id " + id);
        books.remove(findById(id));
    }
}