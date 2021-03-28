package ua.com.alevel.uniquenumbers;

import java.util.HashSet;

public class CalculatorUniqueNumbers {

    public int calculateTotalOfUniqueNumbers(int[] inputArray) {
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int i = 0; i < inputArray.length; i++) {
            uniqueNumbers.add(inputArray[i]);
        }
        return uniqueNumbers.size();
    }
}