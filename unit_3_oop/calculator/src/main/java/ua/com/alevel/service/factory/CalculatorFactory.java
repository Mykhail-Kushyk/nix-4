package ua.com.alevel.service.factory;

import org.reflections.Reflections;
import ua.com.alevel.service.CalculatorService;

import java.util.Set;

public class CalculatorFactory {

    private static CalculatorFactory instance;
    private Reflections reflections;
    private Set<Class<? extends CalculatorService>> calculatorServices;

    private CalculatorFactory() {
        reflections = new Reflections("ua.com.alevel");
        calculatorServices = reflections.getSubTypesOf(CalculatorService.class);
    }

    public static CalculatorFactory getInstance() {
        if (instance == null) {
            instance = new CalculatorFactory();
        }
        return instance;
    }
    public CalculatorService getCalculatorService() {
        for (Class<? extends CalculatorService> calculatorService : calculatorServices) {
            if (!calculatorService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calculatorService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Error");
                }
            }
        }
        throw new RuntimeException("Error");
    }
}