package ua.com.alevel.financialsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}