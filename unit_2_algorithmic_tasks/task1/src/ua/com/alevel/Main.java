package ua.com.alevel;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String string = "56 98dkvnd9dkj_3";
        ArrayList<Integer> list = Isolator.isolateNumbers(string);
        System.out.print("String: " + string + System.lineSeparator() + "Numbers from string: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "|");
        }
        System.out.println();
        int sumNumbers = Calculator.sumNumbers(list);
        System.out.println("Sum: " + sumNumbers);
    }
}