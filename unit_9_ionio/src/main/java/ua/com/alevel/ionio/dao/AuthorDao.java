package ua.com.alevel.ionio.dao;

import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;

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