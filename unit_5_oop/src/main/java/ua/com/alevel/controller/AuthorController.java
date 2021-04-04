package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class AuthorController {

    private final AuthorService authorService = AuthorServiceImpl.getInstance();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        System.out.println("0. Exit");
        System.out.println("1. Create author");
        System.out.println("2. Read all");
        System.out.println("3. Read by id");
        System.out.println("4. Read by last name");
        System.out.println("5. Read by book");
        System.out.println("6. Update");
        System.out.println("7. Delete by id");
        System.out.println("Please, choose option: ");
        String option;
        try {
            while ((option = reader.readLine()) != null) {
                switch (option) {
                    case "0": return;
                    case "1": create(); break;
                    case "2": readAll(); break;
                    case "3": readById(); break;
                    case "4": readByLastName();
                    case "5": readByBook(); break;
                    case "6": update(); break;
                    case "7": deleteById(); break;
                }
                System.out.println("Input option: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void create() throws IOException {
        Author author = scanAuthor();
        authorService.create(author);
    }

    private void readAll() {
        System.out.println("All books: " + authorService.readAll());
    }

    private void readById() throws IOException {
        System.out.println("Input id: ");
        Author author = authorService.readById(Integer.parseInt(reader.readLine()));
        System.out.println(author);
    }

    private void readByLastName() throws IOException {
        System.out.println("Input name: ");
        Author author = authorService.readByLastName(reader.readLine());
        System.out.println(author);
    }

    private void readByBook() throws IOException {
        System.out.println("Input books id: ");
        BookService bookService = new BookServiceImpl();
        Set<Author> authors = authorService.readByBook(bookService.readById(Integer.parseInt(reader.readLine())));
        System.out.println(authors);
    }

    private void update() throws IOException {
        Author author = scanAuthor();
        authorService.update(author);
    }

    private void deleteById() throws IOException {
        System.out.println("Input id: ");
        authorService.deleteById(Integer.parseInt(reader.readLine()));
    }

    private Author scanAuthor() throws IOException {
        Author author = new Author();
        System.out.println("Input data please");
        System.out.println("Id: ");
        author.setId(Integer.parseInt(reader.readLine()));
        System.out.println("First name: ");
        author.setFirstName(reader.readLine());
        System.out.println("Last name: ");
        author.setLastName(reader.readLine());
        Set<Integer> ids = new HashSet<>();
        System.out.println("Books id: ");
        ids.add(Integer.parseInt(reader.readLine()));
        System.out.println("Is there one more book? (Yes|No)");
        String answer = reader.readLine();
        while (answer.equals("Yes")) {
            System.out.println("Input more: ");
            ids.add(Integer.parseInt(reader.readLine()));
            System.out.println("Is there one more book? (Yes|No)");
            answer = reader.readLine();
        }
        author.setBooksIds(ids);
        return author;
    }
}