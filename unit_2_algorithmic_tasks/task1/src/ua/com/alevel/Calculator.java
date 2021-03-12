package ua.com.alevel;

import java.util.ArrayList;

public class Calculator {

    public static int sumNumbers(ArrayList<Integer> numbers) {
        int result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }
}