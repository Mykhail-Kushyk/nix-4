package ua.com.alevel;

import ua.com.alevel.uniquenumbers.CalculatorUniqueNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        System.out.println();
        System.out.println("Enter 0 to exit!");
        System.out.print("Please enter level (1, 2 or 3) to check: ");
        String choice;
        try {
            while ((choice= reader.readLine()) != null) {
                switch (choice) {
                    case "0": System.exit(0);
                    case "1": new ApplicationFirstLevel().run(); break;
                    case "2": new ApplicationSecondLevel().run(); break;
                    case "3": ConwayGameOfLife.runGui();
                }
                System.out.println("Enter 0 to exit!");
                System.out.print("Please enter level (1, 2 or 3) to check: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}