package ua.com.alevel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties props = new Properties();
        String fileName = "app.properties";
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                props.load(inputStream);
            }
            Factory factory = new Factory();
            AppProperties properties = factory.create(props, AppProperties.class);
            System.out.println("App name: " + properties.getAppName());
            System.out.println("Max connections is " + properties.getMaxConnections());
            System.out.println("Max variables is " + properties.getMaxVariables());
            System.out.println("Timeout is " + properties.getTimeout());
            System.out.println("Total of classes: " + properties.getTotalClasses());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}