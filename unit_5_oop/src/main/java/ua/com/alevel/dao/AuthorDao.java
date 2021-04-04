package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;
import java.util.Set;

public interface AuthorDao {

    void clear();

    void add(Author author);

    Set<Author> findAll();

    Author findById(int id);

    Author findByLastName(String lastName);

    Set<Author> findByBook(Book book);

    void update(Author author);

    void deleteById(int id);
}