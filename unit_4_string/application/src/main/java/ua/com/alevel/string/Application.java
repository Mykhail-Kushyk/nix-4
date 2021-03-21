package ua.com.alevel.string;

import ua.com.alevel.reversestring.ReverseStringUtil;

public class Application {

    public void run() {
        String input = "Hello world";
        System.out.println(input + " - " + ReverseStringUtil.reverse(input));
        System.out.println(input + ", worl - " +ReverseStringUtil.reverse(input, "worl"));
        System.out.println(input + ", 3, 7 - " +ReverseStringUtil.reverse(input, 3, 7));
    }
}