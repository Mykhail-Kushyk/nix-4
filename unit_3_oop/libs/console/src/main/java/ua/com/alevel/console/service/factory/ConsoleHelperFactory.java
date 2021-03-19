package ua.com.alevel.console.service.factory;

import org.reflections.Reflections;
import ua.com.alevel.console.service.ConsoleHelperService;

import java.util.Set;

public class ConsoleHelperFactory {

    private static ConsoleHelperFactory instance;
    private Reflections reflections;
    private Set<Class<? extends ConsoleHelperService>> services;

    private ConsoleHelperFactory() {
        reflections = new Reflections("ua.com.alevel");
        services = reflections.getSubTypesOf(ConsoleHelperService.class);
    }

    public static ConsoleHelperFactory getInstance() {
        if (instance == null) {
            instance = new ConsoleHelperFactory();
        }
        return instance;
    }
    public ConsoleHelperService getConsoleHelperService() {
        for (Class<? extends ConsoleHelperService> service : services) {
            if (!service.isAnnotationPresent(Deprecated.class)) {
                try {
                    return service.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Error");
                }
            }
        }
        throw new RuntimeException("Error");
    }
}