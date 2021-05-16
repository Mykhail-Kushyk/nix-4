package ua.com.alevel.dao.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.ionio.dao.AuthorDao;
import ua.com.alevel.ionio.dao.BookDao;
import ua.com.alevel.ionio.dao.impl.AuthorDaoCsvImpl;
import ua.com.alevel.ionio.dao.impl.BookDaoCsvImpl;
import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonDaoImplTest {

    private BookDao bookDao = new BookDaoCsvImpl("src/main/resources/data/books.csv");
    private AuthorDao authorDao = new AuthorDaoCsvImpl("src/main/resources/data/authors.csv");

    @BeforeEach
    void setUp() {
        Set<Integer> ids = new HashSet<>();
        ids.add(0);
        Book book = new Book(1, "TestName1", ids);
        bookDao.add(book);

        Author author = new Author(1, "TestName", "TestLastName", ids);
        authorDao.add(author);
    }

    @AfterEach
    void tearDown() {
        bookDao.clear();
        authorDao.clear();
    }

    @Test
    void commonTest() {
        Set<Integer> ids = new HashSet<>();
        ids.add(0);
        Book book = new Book(1, "TestName1", ids);
        Set<Book> expectedBooks = new HashSet<>();
        expectedBooks.add(book);

        Set<Author> expectedAuthors = new HashSet<>();
        expectedAuthors.add(new Author(1, "TestName", "TestLastName", ids));
        assertEquals(expectedAuthors, authorDao.findAll());

        Book book2 = new Book(2, "TestName2", ids);
        bookDao.add(book2);
        bookDao.deleteById(2);
        assertEquals(expectedBooks, bookDao.findAll());

        ids.add(1);
        Set<Book> expectedBooksByAuthor = new HashSet<>();
        book = new Book(2, "TestName2", ids);
        bookDao.add(book);
        expectedBooksByAuthor.add(book);
        assertEquals(expectedBooksByAuthor, bookDao.findByAuthor(authorDao.findById(1)));
    }
}