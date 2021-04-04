package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public interface AuthorService {

    void create(Author author);

    Set<Author> readAll();

    Author readById(int id);

    Author readByLastName(String lastName);

    Set<Author> readByBook(Book book);

    void update(Author author);

    void deleteById(int id);
}
