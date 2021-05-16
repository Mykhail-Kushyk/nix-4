package ua.com.alevel.ionio.dao.impl;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.log4j.Logger;
import ua.com.alevel.ionio.dao.BookDao;
import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDaoCsvImpl implements BookDao {

    private final Logger logger = Logger.getLogger(BookDaoCsvImpl.class);
    private String csvFile;

    public BookDaoCsvImpl(String csvFile) {
        this.csvFile = csvFile;
        try {
            Writer writer = new FileWriter(csvFile, false);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BookDaoCsvImpl() {
        this.csvFile = "./src/main/resources/data/books.csv";
        try {
            Writer writer = new FileWriter(csvFile, false);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        try {
            Writer writer = new FileWriter(csvFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Clear books");
    }

    @Override
    public void add(Book book) {
        logger.info("Start creating book " + book.getId());
        try {
            Writer writer = new FileWriter(csvFile, true);
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(book);
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finish creating book " + book.getId());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Book> findAll() {
        logger.info("Finding all books");
        try {
            Reader reader = new BufferedReader(new FileReader(csvFile));
            CsvToBean<Book> csvReader = new CsvToBeanBuilder(reader)
                    .withType(Book.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return new HashSet<>(csvReader.parse());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    @Override
    public Book findById(int id) {
        logger.info("Finding book by id - " + id);
        ArrayList<Book> allBooks = new ArrayList<>(findAll());
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getId() == id) {
                return allBooks.get(i);
            }
        }
        throw new NoSuchElementException("Such element does not exist!");
    }

    @Override
    public Book findByName(String name) {
        logger.info("Finding book by name - " + name);
        return findAll().stream()
                .filter(book -> book.getName().equals(name))
                .findFirst()
                .get();
    }

    @Override
    public Set<Book> findByAuthor(Author author) {
        logger.info("Start finding book by author - " + author.getId());
        return findAll()
                .stream()
                .filter(book -> book.getAuthorsIds().contains(author.getId()))
                .collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(Book book) {
        logger.info("Start updating book by id " + book.getId());
        ArrayList<Book> allBooks = new ArrayList<>(findAll());
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getId() == book.getId()) {
                allBooks.remove(i);
                allBooks.add(book);
            }
        }
        try {
            Writer writer = new FileWriter(csvFile, false);
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(allBooks);
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finish updating book by id " + book.getId());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteById(int id) {
        logger.info("Deleting book by id " + id);
        ArrayList<Book> allBooks = new ArrayList<>(findAll());
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getId() == id) {
                allBooks.remove(i);
            }
        }
        try {
            Writer writer = new FileWriter(csvFile, false);
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(allBooks);
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finish deleting book " + id);
    }
}