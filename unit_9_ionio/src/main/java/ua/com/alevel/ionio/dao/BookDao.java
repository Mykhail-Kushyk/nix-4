package ua.com.alevel.ionio.dao;

import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;

import java.util.Set;

public interface BookDao {

    void clear();

    void add(Book book);

    Set<Book> findAll();

    Book findById(int id);

    Book findByName(String name);

    Set<Book> findByAuthor(Author author);

    void update(Book book);

    void deleteById(int id);
}