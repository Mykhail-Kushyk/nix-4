package ua.com.alevel.ionio.service;

import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;

import java.util.Set;

public interface BookService {

    void create(Book book);

    Set<Book> readAll();

    Book readById(int id);

    Book readByName(String name);

    Set<Book> readByAuthor(Author author);

    void update(Book book);

    void deleteById(int id);
}