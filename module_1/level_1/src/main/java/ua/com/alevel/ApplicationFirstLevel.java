package ua.com.alevel;

import ua.com.alevel.chess.ChessValidator;
import ua.com.alevel.square.CalculatorSquare;
import ua.com.alevel.square.Point;
import ua.com.alevel.uniquenumbers.CalculatorUniqueNumbers;

public class ApplicationFirstLevel {

    public void run() {
        System.out.println();
        System.out.println("First task!");
        System.out.println("Input: 1, 2, 3, 3, 4, 5, 5, 6");
        CalculatorUniqueNumbers uniqueNumbers = new CalculatorUniqueNumbers();
        System.out.println("Total of unique numbers: " +
                uniqueNumbers.calculateTotalOfUniqueNumbers(new int[] {1, 2, 3, 3, 4, 5, 5, 6}));
        System.out.println();
        System.out.println("Second task!");
        System.out.println("Start X: 2");
        System.out.println("Start Y: 1");
        System.out.println("Finish X: 1");
        System.out.println("Finish Y: 5");
        ChessValidator validator = new ChessValidator();
        if (validator.isImpossibleToGoByHorse(2,1,1,5)) {
            System.out.println("You can go there!");
        } else {
            System.out.println("You can not go there!");
        }
        System.out.println();
        System.out.println("Third task!");
        System.out.println("Points: A(1;2), B(3;4), C(0,0)");
        CalculatorSquare calculatorSquare = new CalculatorSquare();
        System.out.println("Square: " + calculatorSquare.calculateTriangleSquare(new Point(1, 2),
                new Point(3, 4), new Point(0, 0)));
        System.out.println();
    }
}