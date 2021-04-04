package ua.com.alevel;

import ua.com.alevel.controller.AuthorController;
import ua.com.alevel.controller.BookController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private final AuthorController authorController = new AuthorController();
    private final BookController bookController = new BookController();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("0. Close program");
        System.out.println("1. Book controller");
        System.out.println("2. Author controller");
        System.out.println("Choose option please: ");
        String option;
        try {
            while ((option = reader.readLine()) != null) {
                switch (option) {
                    case "0" -> System.exit(0);
                    case "1" -> bookController.run();
                    case "2" -> authorController.run();
                }
                System.out.println("0. Close program");
                System.out.println("1. Book controller");
                System.out.println("2. Author controller");
                System.out.println("Choose option please: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}