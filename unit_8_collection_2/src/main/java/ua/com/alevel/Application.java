package ua.com.alevel;

import ua.com.alevel.set.MathSet;
import ua.com.alevel.set.impl.MathSetImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    MathSet<Integer> mathSet = new MathSetImpl<>();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("You have math set with integer numbers! ");
        try {
            while (true) {
                showMainMenu();
                String operation = reader.readLine();
                switch (operation) {
                    case "0": System.exit(0);
                    case "1":
                        System.out.print("Please input number: ");
                        mathSet.add(Integer.parseInt(reader.readLine())); break;
                    case "2":
                        System.out.print("Input index please: ");
                        int index = Integer.parseInt(reader.readLine());
                        System.out.println("Element: " + mathSet.get(index));
                        System.out.println(); break;
                    case "3":
                        System.out.println("Min element: " + mathSet.getMin());
                        System.out.println(); break;
                    case "4":
                        System.out.println("Max element: " + mathSet.getMax());
                        System.out.println(); break;
                    case "5":
                        System.out.println("Average: " + mathSet.getAverage());
                        System.out.println(); break;
                    case "6":
                        mathSet.sortAsc(); break;
                    case "7":
                        mathSet.sortDesc(); break;
                    case "8":
                        System.out.println("Median: " + mathSet.getMedian());
                        System.out.println(); break;
                    case "9":
                        mathSet.clear(); break;
                    case "10":
                        for (int i = 0; i < mathSet.size(); i++) {
                            System.out.print(mathSet.get(i) + " | ");
                        }
                        System.out.println();
                        System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMainMenu() {
        System.out.println("Main menu: ");
        System.out.println("0. Close");
        System.out.println("1. Add element");
        System.out.println("2. Get by index");
        System.out.println("3. Get min element");
        System.out.println("4. Get max element");
        System.out.println("5. Get average of all elements");
        System.out.println("6. Sort by ascending");
        System.out.println("7. Sort by descending");
        System.out.println("8. Get median");
        System.out.println("9. Delete all elements");
        System.out.println("10. Print all elements");
        System.out.print("Choose action: ");
    }
}
