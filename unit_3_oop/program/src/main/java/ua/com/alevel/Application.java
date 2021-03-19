package ua.com.alevel;

import ua.com.alevel.console.service.ConsoleHelperService;
import ua.com.alevel.console.service.factory.ConsoleHelperFactory;
import ua.com.alevel.service.CalculatorService;
import ua.com.alevel.service.factory.CalculatorFactory;

import java.math.BigInteger;

public class Application {

    private final ConsoleHelperService console = ConsoleHelperFactory.getInstance().getConsoleHelperService();
    private final CalculatorService calculator = CalculatorFactory.getInstance().getCalculatorService();

    public void run() {
        console.output("0 - to exit");
        console.output(System.lineSeparator());
        console.output("1 - to sum numbers (+)");
        console.output(System.lineSeparator());
        console.output("2 - to subtract numbers (-)");
        console.output(System.lineSeparator());
        console.output("3 - to multiply numbers (*)");
        console.output(System.lineSeparator());
        console.output("4 - to divide numbers (/)");
        console.output(System.lineSeparator());
        console.output("Please choose option to continue: ");
        String option = console.readString();
        console.output("Please input valid number: ");
        BigInteger firstComponent = console.readNumberAsString();
        console.output("Please input valid number: ");
        BigInteger secondComponent = console.readNumberAsString();
        BigInteger result;
        switch (option) {
            case "0": System.exit(0);
            case "1": result = calculator.sum(firstComponent, secondComponent); break;
            case "2": result = calculator.subtract(firstComponent, secondComponent); break;
            case "3": result = calculator.multiply(firstComponent, secondComponent); break;
            case "4": result = calculator.divide(firstComponent, secondComponent);
            default: throw new RuntimeException("Your input is not valid");
        }
        console.output("Result: ");
        console.output(result);
    }
}