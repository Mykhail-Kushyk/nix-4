package ua.com.alevel.dao.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CommonDaoImplTest {

    private BookDao bookDao = BookDaoImpl.getInstance();
    private AuthorDao authorDao = AuthorDaoImpl.getInstance();

    @BeforeEach
    void setUp() {
        Book book = new Book();
        book.setId(1);
        book.setName("TestName1");
        bookDao.add(book);
        Book newBook = new Book();
        newBook.setId(2);
        newBook.setName("TestName2");
        bookDao.add(newBook);
        bookDao.deleteById(1);
        newBook.setName("ChangedName");
        bookDao.update(newBook);

        Author author = new Author();
        author.setId(1);
        authorDao.add(author);
        Author newAuthor = new Author();
        newAuthor.setId(2);
        newAuthor.setFirstName("TestFirstName");
        newAuthor.setLastName("TestLastName");
        authorDao.add(newAuthor);
        authorDao.deleteById(1);
        newAuthor.setFirstName("ChangedFirstName");
        authorDao.update(newAuthor);
    }

    @AfterEach
    void tearDown() {
        bookDao.clear();
        authorDao.clear();
    }

    @Test
    void commonBookDaoTest() {
        Set<Book> expected = new HashSet<>();
        Book book = new Book();
        book.setId(2);
        book.setName("ChangedName");
        expected.add(book);
        assertEquals(expected, bookDao.findAll());
    }

    @Test
    void commonAuthorDaoTest() {
        Set<Author> expected = new HashSet<>();
        Author author = new Author();
        author.setId(2);
        author.setFirstName("ChangedFirstName");
        author.setLastName("TestLastName");
        expected.add(author);
        assertEquals(expected, authorDao.findAll());
    }
}
