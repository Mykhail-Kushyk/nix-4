package ua.com.alevel.ionio.controller;

import ua.com.alevel.ionio.service.impl.BookServiceImpl;
import ua.com.alevel.ionio.entity.Book;
import ua.com.alevel.ionio.service.AuthorService;
import ua.com.alevel.ionio.service.BookService;
import ua.com.alevel.ionio.service.impl.AuthorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BookController {

    private final BookService bookService = BookServiceImpl.getInstance();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        System.out.println("0. Exit");
        System.out.println("1. Create book");
        System.out.println("2. Read all");
        System.out.println("3. Read by id");
        System.out.println("4. Read by name");
        System.out.println("5. Read by author");
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
                    case "4": readByName();
                    case "5": readByAuthor(); break;
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
        Book book = scanBook();
        bookService.create(book);
    }

    private void readAll() {
        System.out.println("All books: " );
        Set<Book> books = bookService.readAll();
        for (Book book: books) {
            System.out.println(book.toString());
        }
    }

    private void readById() throws IOException {
        System.out.println("Input id: ");
        Book book = bookService.readById(Integer.parseInt(reader.readLine()));
        System.out.println(book.toString());
    }

    private void readByName() throws IOException {
        System.out.println("Input name: ");
        Book book = bookService.readByName(reader.readLine());
        System.out.println(book.toString());
    }

    private void readByAuthor() throws IOException {
        System.out.println("Input authors id: ");
        AuthorService authorService = new AuthorServiceImpl();
        Set<Book> books = bookService.readByAuthor(authorService.readById(Integer.parseInt(reader.readLine())));
        for (Book book: books) {
            System.out.println(book.toString());
        }
    }

    private void update() throws IOException {
        Book book = scanBook();
        bookService.update(book);
    }

    private void deleteById() throws IOException {
        System.out.println("Input id: ");
        bookService.deleteById(Integer.parseInt(reader.readLine()));
    }

    private Book scanBook() throws IOException {
        Book book = new Book();
        System.out.println("Input data please");
        System.out.println("Id: ");
        book.setId(Integer.parseInt(reader.readLine()));
        System.out.println("Name: ");
        book.setName(reader.readLine());
        Set<Integer> ids = new HashSet<>();
        System.out.println("Authors id: ");
        ids.add(Integer.parseInt(reader.readLine()));
        System.out.println("Is there one more author? (Yes|No)");
        String answer = reader.readLine();
        while (answer.equals("Yes")) {
            System.out.println("Input more: ");
            ids.add(Integer.parseInt(reader.readLine()));
            System.out.println("Is there one more author? (Yes|No)");
            answer = reader.readLine();
        }
        book.setAuthorsIds(ids);
        return book;
    }
}