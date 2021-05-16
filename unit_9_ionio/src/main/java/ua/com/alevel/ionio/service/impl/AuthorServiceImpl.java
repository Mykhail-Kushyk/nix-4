package ua.com.alevel.ionio.service.impl;

import ua.com.alevel.ionio.dao.impl.AuthorDaoCsvImpl;
import ua.com.alevel.ionio.dao.AuthorDao;
import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;
import ua.com.alevel.ionio.service.AuthorService;

import java.io.File;
import java.util.Set;

public class AuthorServiceImpl implements AuthorService {

    private static AuthorServiceImpl instance;
    private AuthorDao authorDao = new AuthorDaoCsvImpl();

    public static AuthorServiceImpl getInstance() {
        if (instance == null) {
            instance = new AuthorServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Author author) {
        authorDao.add(author);
    }

    @Override
    public Set<Author> readAll() {
        return authorDao.findAll();
    }

    @Override
    public Author readById(int id) {
        return authorDao.findById(id);
    }

    @Override
    public Author readByLastName(String lastName) {
        return authorDao.findByLastName(lastName);
    }

    @Override
    public Set<Author> readByBook(Book book) {
        return authorDao.findByBook(book);
    }

    @Override
    public void update(Author author) {
        authorDao.update(author);
    }

    @Override
    public void deleteById(int id) {
        authorDao.deleteById(id);
    }
}