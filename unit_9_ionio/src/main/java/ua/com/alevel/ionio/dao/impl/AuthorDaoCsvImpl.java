package ua.com.alevel.ionio.dao.impl;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.log4j.Logger;
import ua.com.alevel.ionio.dao.AuthorDao;
import ua.com.alevel.ionio.entity.Author;
import ua.com.alevel.ionio.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorDaoCsvImpl implements AuthorDao {

    private final Logger logger = Logger.getLogger(AuthorDaoCsvImpl.class);
    private String csvFile;

    public AuthorDaoCsvImpl(String csvFile) {
        this.csvFile = csvFile;
        try {
            Writer writer = new FileWriter(csvFile, false);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthorDaoCsvImpl() {
        this.csvFile = "./src/main/resources/data/authors.csv";
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
        logger.info("Clear authors");

    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(Author author) {
        logger.info("Start creating author");
        try {
            Writer writer = new FileWriter(csvFile, true);
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(author);
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finish creating author");
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Author> findAll() {
        logger.info("FindAll method author dao");
        try {
            Reader reader = new BufferedReader(new FileReader(csvFile));
            CsvToBean<Author> csvReader = new CsvToBeanBuilder(reader)
                    .withType(Author.class)
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
    public Author findById(int id) {
        logger.info("Finding author by id " + id);
        ArrayList<Author> allAuthors = new ArrayList<>(findAll());
        for (int i = 0; i < allAuthors.size(); i++) {
            if (allAuthors.get(i).getId() == id) {
                return allAuthors.get(i);
            }
        }
        throw new NoSuchElementException("Such element does not exist!");
    }

    @Override
    public Author findByLastName(String lastName) {
        logger.info("Finding author by last name " + lastName);
        ArrayList<Author> allAuthors = new ArrayList<>(findAll());
        for (int i = 0; i < allAuthors.size(); i++) {
            if (allAuthors.get(i).getLastName().equals(lastName)) {
                return allAuthors.get(i);
            }
        }
        throw new NoSuchElementException("Such element does not exist!");
    }

    @Override
    public Set<Author> findByBook(Book book) {
        logger.info("Finding author by book " + book.getName() + ", id - " + book.getId());
        return findAll()
                .stream()
                .filter(author -> author.getBooksIds().contains(book.getId()))
                .collect(Collectors.toSet());
    }

    @Override
    public void update(Author author) {
        logger.info("Start updating author " + author.getId());
        ArrayList<Author> allAuthors = new ArrayList<>(findAll());
        for (int i = 0; i < allAuthors.size(); i++) {
            if (allAuthors.get(i).getId() == author.getId()) {
                allAuthors.remove(i);
                allAuthors.add(author);
            }
        }
        try {
            Writer writer = new FileWriter(csvFile, false);
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(allAuthors);
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finish updating author " + author.getId());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteById(int id) {
        logger.info("Start deleting author " + id);
        ArrayList<Author> allAuthors = new ArrayList<>(findAll());
        for (int i = 0; i < allAuthors.size(); i++) {
            if (allAuthors.get(i).getId() == id) {
                allAuthors.remove(i);
            }
        }
        try {
            Writer writer = new FileWriter(csvFile, false);
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(allAuthors);
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finish deleting author " + id);
    }
}