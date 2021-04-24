package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Application {

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. String");
        System.out.println("2. Integer");
        System.out.print("Choose data type for creating ordered list: ");
        String option = "";
        try {
            option = reader.readLine();
            switch (option) {
                case "1":
                    createStringList(reader);
                    break;
                case "2":
                    createIntegerList(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStringList(BufferedReader reader) throws IOException {
        List<String> list = new OrderedList<>();
        boolean flag = true;
        while (true) {
            showMenu();
            String operation = reader.readLine();
            switch (operation) {
                case "0": {
                    System.exit(0);
                }
                case "1": {
                    System.out.print("Input string: ");
                    list.add(reader.readLine());
                    break;
                }
                case "2": {
                    System.out.print("Input string for removing: ");
                    list.remove(reader.readLine());
                    break;
                }
                case "3": {
                    System.out.print("Input index: ");
                    String element = list.get(Integer.parseInt(reader.readLine()));
                    System.out.println("Element: " + element);
                    break;
                }
                case "4": {
                    System.out.print("Input element: ");
                    boolean isExist = list.contains(reader.readLine());
                    if (isExist) {
                        System.out.println("Such element exists");
                    } else {
                        System.out.println("Such element does not exist");
                    }
                    break;
                }
                case "5": {
                    list.clear(); break;
                }
                case "6": {
                   if (!list.isEmpty()) {
                       for (String string: list) {
                           System.out.print(string + " | ");
                       }
                       System.out.println();
                   } else {
                       System.out.println("List is empty!");
                   }
                }
            }
        }
    }

    private void createIntegerList(BufferedReader reader) throws IOException {
        List<Integer> list = new OrderedList<>();
        boolean flag = true;
        while (true) {
            showMenu();
            String operation = reader.readLine();
            switch (operation) {
                case "0": {
                    System.exit(0);
                }
                case "1": {
                    System.out.print("Input integer number: ");
                    list.add(Integer.parseInt(reader.readLine()));
                    break;
                }
                case "2": {
                    System.out.print("Input integer number for removing: ");
                    list.remove(Integer.parseInt(reader.readLine()));
                    break;
                }
                case "3": {
                    System.out.print("Input index: ");
                    Integer element = list.get(Integer.parseInt(reader.readLine()));
                    System.out.println("Element: " + element);
                    break;
                }
                case "4": {
                    System.out.print("Input element: ");
                    boolean isExist = list.contains(Integer.parseInt(reader.readLine()));
                    if (isExist) {
                        System.out.println("Such element exists");
                    } else {
                        System.out.println("Such element does not exist");
                    }
                    break;
                }
                case "5": {
                    list.clear();
                    break;
                }
                case "6": {
                    if (!list.isEmpty()) {
                        for (Integer integer: list) {
                            System.out.print(integer + " | ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("List is empty!");
                    }
                 }
            }
        }
    }

    private void showMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Add element");
        System.out.println("2. Remove element by value");
        System.out.println("3. Get by index");
        System.out.println("4. Check if contains element");
        System.out.println("5. Clear list");
        System.out.println("6. Get all");
        System.out.print("Choose operation: ");
    }
}