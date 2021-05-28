package ua.com.alevel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties props = new Properties();
        String fileName = "app.properties";
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        AppProperties initializedProps = new AppProperties();
        try {
            if (inputStream != null) {
                props.load(inputStream);
            }
            Initializer initializer = new Initializer();
            initializedProps = initializer.initialize(props, initializedProps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("App name: " + initializedProps.getAppName());
        System.out.println("Max connections is " + initializedProps.getMaxConnections());
        System.out.println("Max variables is " + initializedProps.getMaxVariables());
        System.out.println("Timeout is " + initializedProps.getTimeout());
        System.out.println("Total of classes: " + initializedProps.getTotalClasses());
    }
}