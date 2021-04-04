package ua.com.alevel.dao.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDaoImplTest {

    private AuthorDao dao = AuthorDaoImpl.getInstance();

    @BeforeEach
    void setUp() {
        Author firstAuthor = new Author();
        firstAuthor.setFirstName("TestName1");
        firstAuthor.setLastName("TestLastName1");
        firstAuthor.setId(1);
        Set<Integer> ids = new HashSet<>();
        ids.add(56);
        firstAuthor.setBooksIds(ids);
        dao.add(firstAuthor);
    }

    @AfterEach
    void tearDown() {
        dao.clear();
    }

    @Test
    void add() {
        Author expected = new Author();
        expected.setFirstName("TestName2");
        expected.setLastName("TestLastName2");
        expected.setId(2);
        dao.add(expected);
        assertEquals(expected, dao.findById(2));
    }

    @Test
    void findAll() {
        Author author = new Author();
        author.setFirstName("TestName1");
        author.setLastName("TestLastName1");
        author.setId(1);
        Set<Integer> ids = new HashSet<>();
        ids.add(56);
        author.setBooksIds(ids);
        Set<Author> expected = new HashSet<>();
        expected.add(author);
        assertEquals(expected, dao.findAll());
    }

    @Test
    void findById() {
        Author expected = new Author();
        expected.setFirstName("TestName1");
        expected.setLastName("TestLastName1");
        expected.setId(1);
        Set<Integer> ids = new HashSet<>();
        ids.add(56);
        expected.setBooksIds(ids);
        assertEquals(expected, dao.findById(1));
    }

    @Test
    void findByLastName() {
        Author expected = new Author();
        expected.setFirstName("TestName2");
        expected.setLastName("ExpectedLastName");
        expected.setId(2);
        dao.add(expected);
        assertEquals(expected, dao.findByLastName("ExpectedLastName"));
    }

    @Test
    void findByBook() {
        Author secondAuthor = new Author();
        secondAuthor.setFirstName("TestName2");
        secondAuthor.setLastName("TestLastName2");
        secondAuthor.setId(2);
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        secondAuthor.setBooksIds(ids);
        dao.add(secondAuthor);
        Set<Author> expected = new HashSet<>();
        expected.add(secondAuthor);
        Book book = new Book();
        book.setId(1);
        assertEquals(expected, dao.findByBook(book));
    }

    @Test
    void update() {
        Author expected = new Author();
        expected.setFirstName("TestName2");
        expected.setLastName("TestLastName2");
        expected.setId(1);
        dao.update(expected);
        assertEquals(expected, dao.findById(1));
    }

    @Test
    void deleteById() {
        Author secondAuthor = new Author();
        secondAuthor.setFirstName("TestName2");
        secondAuthor.setLastName("TestLastName2");
        secondAuthor.setId(2);
        dao.add(secondAuthor);
        dao.deleteById(1);
        Set<Author> expected = new HashSet<>();
        expected.add(secondAuthor);
        assertEquals(expected, dao.findAll());
    }
}