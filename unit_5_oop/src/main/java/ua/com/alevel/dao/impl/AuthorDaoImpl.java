package ua.com.alevel.dao.impl;

import org.apache.log4j.Logger;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.HashSet;
import java.util.Set;

public class AuthorDaoImpl implements AuthorDao {

    private static AuthorDaoImpl instance;
    private Set<Author> authors;
    private final Logger logger = Logger.getLogger(AuthorDaoImpl.class);

    private AuthorDaoImpl() {
        authors = new HashSet<>();
    }

    @Override
    public void clear() {
        logger.info("Clear authors");
        authors = new HashSet<>();
    }

    public static AuthorDaoImpl getInstance() {
        if (instance == null) {
            instance = new AuthorDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(Author author) {
        logger.info("Start creating author");
        authors.add(author);
        logger.info("Finish creating author");
    }

    @Override
    public Set<Author> findAll() {
        logger.info("FindAll method author dao");
        return authors;
    }

    @Override
    public Author findById(int id) {
        logger.info("Finding author by id " + id);
        return authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public Author findByLastName(String lastName) {
        logger.info("Finding author by last name " + lastName);
        return authors.stream()
                .filter(author -> author.getLastName() == lastName)
                .findFirst()
                .get();
    }

    @Override
    public Set<Author> findByBook(Book book) {
        logger.info("Finding author by book " + book.getName() + ", id - " + book.getId());
        Set<Author> result = new HashSet<>();
        for (Author author: authors) {
            if (author.getBooksIds().contains(book.getId())) {
                result.add(author);
            }
        }
        return result;
    }

    @Override
    public void update(Author author) {
        logger.info("Start updating author " + author.getId());
        Author current = findById(author.getId());
        current.setLastName(author.getLastName());
        current.setFirstName(author.getFirstName());
        current.setBooksIds(author.getBooksIds());
        logger.info("Finish updating author " + author.getId());
    }

    @Override
    public void deleteById(int id) {
        logger.info("Start deleting author " + id);
        authors.remove(findById(id));
        logger.info("Finish deleting author " + id);
    }
}