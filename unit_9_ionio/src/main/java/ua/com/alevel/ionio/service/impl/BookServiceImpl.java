package ua.com.alevel.ionio.service.impl;

import ua.com.alevel.ionio.dao.BookDao;
import ua.com.alevel.ionio.dao.impl.BookDaoCsvImpl;
import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;
import ua.com.alevel.ionio.service.BookService;

import java.util.Set;

public class BookServiceImpl implements BookService {

    private static BookServiceImpl instance;
    private BookDao bookDao = new BookDaoCsvImpl();

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Book book) {
        bookDao.add(book);
    }

    @Override
    public Set<Book> readAll() {
        return bookDao.findAll();
    }

    @Override
    public Book readById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public Book readByName(String name) {
        return bookDao.findByName(name);
    }

    @Override
    public Set<Book> readByAuthor(Author author) {
        return bookDao.findByAuthor(author);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteById(int id) {
        bookDao.deleteById(id);
    }
}