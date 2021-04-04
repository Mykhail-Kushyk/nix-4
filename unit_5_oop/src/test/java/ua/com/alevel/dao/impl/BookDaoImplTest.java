package ua.com.alevel.dao.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoImplTest {

    private BookDao dao = BookDaoImpl.getInstance();

    @BeforeEach
    void setUp() {
        Book newBook = new Book();
        newBook.setId(1);
        newBook.setName("TestName");
        Set<Integer> ids = new HashSet<>();
        ids.add(5);
        newBook.setAuthorsIds(ids);
        dao.add(newBook);
    }

    @AfterEach
    void tearDown() {
        dao.clear();
    }

    @Test
    void add() {
        Book expected = new Book();
        expected.setId(2);
        expected.setName("TestName2");
        dao.add(expected);
        assertEquals(expected, dao.findById(2));
    }

    @Test
    void findAll() {
        Book newBook = new Book();
        newBook.setId(1);
        newBook.setName("TestName");
        Set<Integer> ids = new HashSet<>();
        ids.add(5);
        newBook.setAuthorsIds(ids);
        Set<Book> expected = new HashSet<>();
        expected.add(newBook);
        assertEquals(expected, dao.findAll());
    }

    @Test
    void findById() {
        Book expected = new Book();
        expected.setId(1);
        expected.setName("TestName");
        Set<Integer> ids = new HashSet<>();
        ids.add(5);
        expected.setAuthorsIds(ids);
        assertEquals(expected, dao.findById(1));
    }

    @Test
    void findByName() {
        Book expected = new Book();
        expected.setId(1);
        expected.setName("TestName");
        Set<Integer> ids = new HashSet<>();
        ids.add(5);
        expected.setAuthorsIds(ids);
        assertEquals(expected, dao.findByName("TestName"));
    }

    @Test
    void findByAuthor() {
        Set<Book> expected = new HashSet<>();
        Book newBook = new Book();
        newBook.setId(1);
        newBook.setName("TestName");
        Set<Integer> ids = new HashSet<>();
        ids.add(5);
        newBook.setAuthorsIds(ids);
        expected.add(newBook);
        Author author = new Author();
        author.setId(5);
        assertEquals(expected, dao.findByAuthor(author));
    }

    @Test
    void update() {
        Book expected = new Book();
        expected.setId(1);
        expected.setName("TestName2");
        dao.update(expected);
        assertEquals(expected, dao.findById(1));
    }

    @Test
    void deleteById() {
        Book newBook = new Book();
        newBook.setId(2);
        newBook.setName("TestName2");
        dao.add(newBook);
        dao.deleteById(1);
        Set<Book> expected = new HashSet<>();
        expected.add(newBook);
        assertEquals(expected, dao.findAll());
    }
}