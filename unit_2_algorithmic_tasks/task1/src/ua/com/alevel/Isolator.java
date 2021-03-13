package ua.com.alevel;

import java.util.ArrayList;

public class Isolator {

    public static ArrayList<Integer> isolateNumbers(String inputString) {
        String[] stringNumbers = inputString.replaceAll("[^0-9]", " ").split("\s+");
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < stringNumbers.length; i++) {
            if(!stringNumbers[i].isEmpty()) {
                numbers.add(Integer.parseInt(stringNumbers[i]));
            }
        }
        return numbers;
    }
}