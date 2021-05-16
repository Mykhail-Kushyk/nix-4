package ua.com.alevel.ionio;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
//        File file = new File(System.getProperty(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
//        System.out.println(file.getAbsoluteFile());
    }
}